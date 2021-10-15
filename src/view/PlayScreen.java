package view;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;

import model.BasicEnemy;
import model.HardEnemy;
import model.Player;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

public class PlayScreen implements IObserver {

	private TCPLauncher tcp;
	private PApplet app;
	private PImage background, player1Img, player2Img;
	private PFont font;
	private int scorePlayer1, scorePlayer2, minutes, seconds;
	private int timeCounter, vulnerable;
	private String winner;
	private ArrayList<BasicEnemy> basicEnemies1, basicEnemies2;
	private ArrayList<HardEnemy> hardEnemies1, hardEnemies2;
	private Player player1, player2;
	private boolean gameover;
	private Gson gson;

	public PlayScreen(PApplet app) {
		this.app = app;

		// Comunicacion
		tcp = TCPLauncher.getInstance();
		tcp.setObserver(this);
		gson = new Gson();

		// Players
		player1 = new Player(206, 650, "no movement");
		player2 = new Player(973, 650, "no movement");

		// Elementos graficos
		background = app.loadImage("./data/Pantalla de juego-1.png");
		font = app.createFont("./data/Poppins-Bold.ttf", 24);
		player1Img = app.loadImage("./data/player1.png");
		player2Img = app.loadImage("./data/player2.png");

		// Lists
		basicEnemies1 = new ArrayList<>();
		basicEnemies2 = new ArrayList<>();
		hardEnemies1 = new ArrayList<>();
		hardEnemies2 = new ArrayList<>();

		// Variables
		gameover = false;
	}

	public void draw() {
		// Fondo
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);

		// Datos
		time();
		score();

		// Enemigos
		createEnemies();
		drawBasicEnemies();
		drawHardEnemies();
		deleteEnemies();
		impactHardEnemies();

		// Players
		players();
		impactPlayers();
		
		whenGameover();

		if (vulnerable > 0) {
			vulnerable--;
		}

	}

	private void time() {
		timeCounter++;
		if (timeCounter == 50) {
			seconds++;
			timeCounter = 0;
		}

		if (seconds == 60) {
			minutes++;
			seconds = 0;
		}

		app.fill(255);
		app.textFont(font);

		// Estructura para que el tiempo quede adecuado
		if (seconds < 10 && minutes < 10) {
			app.text("0" + minutes + ":" + "0" + seconds, 565, 47);
		} else if (seconds >= 10 && minutes < 10) {
			app.text("0" + minutes + ":" + seconds, 565, 47);
		}
	}

	private void score() {
		app.fill(255);
		app.textFont(font);
		app.text(scorePlayer1, 435, 47);
		app.text(scorePlayer2, 744, 47);
		
		if(scorePlayer1 < 0 ) {
			
			scorePlayer1 = 0;
			
		}
		
		if(scorePlayer2 < 0) {
			
			scorePlayer2 = 0;
			
		}
		
	}
	

	private void createEnemies() {
		// Random para las posiciones de los enemigos
		int xTemp1 = (int) app.random(60, 500);
		int xTemp2 = (int) app.random(650, 1140);
		int yTemp = -60;

		// Contador para crear enemigo cada cierto tiempo
		app.frameRate(60);

		// Agregar enemigos basicos
		if (app.frameCount % 200 == 0 && basicEnemies1.size() < 4) {
			basicEnemies1.add(new BasicEnemy(app, xTemp1, yTemp));
			basicEnemies2.add(new BasicEnemy(app, xTemp2, yTemp));
		}

		// Agregar enemigos que disparan
		if (app.frameCount % 500 == 0 && hardEnemies1.size() < 3) {
			hardEnemies1.add(new HardEnemy(app, xTemp1, yTemp));
			hardEnemies2.add(new HardEnemy(app, xTemp2, yTemp));
		}
	}

	private void drawBasicEnemies() {
		for (int i = 0; i < basicEnemies1.size(); i++) {
			basicEnemies1.get(i).draw();
			basicEnemies1.get(i).move();
		}

		for (int i = 0; i < basicEnemies2.size(); i++) {
			basicEnemies2.get(i).draw();
			basicEnemies2.get(i).move();
		}
	}

	private void drawHardEnemies() {
		for (int i = 0; i < hardEnemies1.size(); i++) {
			hardEnemies1.get(i).draw();
			hardEnemies1.get(i).move();
		}

		for (int i = 0; i < hardEnemies2.size(); i++) {
			hardEnemies2.get(i).draw();
			hardEnemies2.get(i).move();
		}

	}

	private void impactHardEnemies() {
		// cuando el enemigo le dispara al player 1
		for (int i = 0; i < hardEnemies1.size(); i++) {
			for (int j = 0; j < hardEnemies1.get(i).getBullets().size(); j++) {

				double distance = distEntreObj(player1.getX(), hardEnemies1.get(i).getBullets().get(j).getY(),
						player1.getY(), hardEnemies1.get(i).getBullets().get(j).getY());

				if (vulnerable == 0) {
					// resta del score del player
					scorePlayer1 -= hardEnemies1.get(i).getPoints();
					scorePlayer2 -= hardEnemies2.get(i).getPoints();

					vulnerable = 60;

					if (distance < 120 && hardEnemies1.get(i).getBullets().get(j).isVisible()) {

						if (distance < 120 && hardEnemies1.get(i).getBullets().get(j).isVisible()
								&& hardEnemies1.get(i).isVisible()) {

							if (vulnerable == 0) {
								scorePlayer1 -= hardEnemies1.get(i).getPoints();
								vulnerable = 60;

							}

							hardEnemies1.get(i).getBullets().get(j).setVisible(false);
							hardEnemies1.get(i).getBullets().remove(j);

							// Enviar mensaje de score
							tcp.getSessions().get(0).sendMessage(" " + scorePlayer1);

						}
					}
				}

			}
		}

		// cuando el enemigo le dispara al player 2
		for (int i = 0; i < hardEnemies2.size(); i++) {
			for (int j = 0; j < hardEnemies2.get(i).getBullets().size(); j++) {

				double distance = distEntreObj(player2.getX(), hardEnemies2.get(i).getBullets().get(j).getY(),
						player2.getY(), hardEnemies2.get(i).getBullets().get(j).getY());

				if (distance < 120 && hardEnemies2.get(i).getBullets().get(j).isVisible()
						&& hardEnemies2.get(i).isVisible()) {
					if (vulnerable == 0) {
						scorePlayer2 -= hardEnemies2.get(i).getPoints();
						vulnerable = 60;

					}

					hardEnemies2.get(i).getBullets().get(j).setVisible(false);
					hardEnemies2.get(i).getBullets().remove(j);

					// Enviar mensaje de score
					tcp.getSessions().get(0).sendMessage(" " + scorePlayer2);

				}
			}
		}

	}

	private void impactPlayers() {

		// Player 1 cuando le dispara al enemigo basico
		for (int i = 0; i < player1.getBullets().size(); i++) {
			for (int j = 0; j < basicEnemies1.size(); j++) {

				if (app.dist(player1.getBullets().get(i).getX(), player1.getBullets().get(i).getY(),
						basicEnemies1.get(j).getX(), basicEnemies1.get(j).getY()) < basicEnemies1.get(j).getWidth() / 2
						&& player1.getBullets().get(i).isVisible() && basicEnemies1.get(j).isVisible()) {
					System.out.println("si se dispara");
					scorePlayer1 += basicEnemies1.get(j).getPoints();
					basicEnemies1.get(j).setVisible(false);
					// Daño de las balas

					player1.getBullets().get(i).setVisible(false);
					System.out.println(player1.getBullets().get(i).isVisible());
					// Enviar mensaje de score
					tcp.getSessions().get(0).sendMessage(" " + scorePlayer1);
				}
			}

			// Player 1 cuando le dispara al enemigo dificil
			for (int j = 0; j < hardEnemies1.size(); j++) {

					if (app.dist(player1.getBullets().get(i).getX(), player1.getBullets().get(i).getY(),
							hardEnemies1.get(j).getX(), hardEnemies1.get(j).getY()) < hardEnemies1.get(j).getWidth() / 2
							&& player1.getBullets().get(i).isVisible() && hardEnemies1.get(j).isVisible()) {
						scorePlayer1 += hardEnemies1.get(j).getPoints();
						hardEnemies1.get(j).setVisible(false);
						// Daño de las balas
						player1.getBullets().get(i).setVisible(false);

						// Enviar mensaje de score
						tcp.getSessions().get(0).sendMessage(" " + scorePlayer1);
					}
				}
		}
		
		// Player 2 cuando le dispara al enemigo basico
		for (int i = 0; i < player2.getBullets().size(); i++) {
			
			for (int j = 0; j < basicEnemies2.size(); j++) {
			
				if (app.dist(player2.getBullets().get(i).getX(), player2.getBullets().get(i).getY(),
						basicEnemies2.get(j).getX(), basicEnemies2.get(j).getY()) < basicEnemies2.get(j).getWidth() / 2
						&& player2.getBullets().get(i).isVisible() && basicEnemies2.get(j).isVisible()) {
					scorePlayer2 += basicEnemies2.get(j).getPoints();
					basicEnemies2.get(j).setVisible(false);
					// Daño de las balas

					player2.getBullets().get(i).setVisible(false);

					// Enviar mensaje de score
					tcp.getSessions().get(0).sendMessage(" " + scorePlayer2);
				}
			}

			// Player 2 cuando le dispara al enemigo dificil
			for (int j = 0; j < hardEnemies2.size(); j++) {
				if (app.dist(player2.getBullets().get(i).getX(), player2.getBullets().get(i).getY(),
						hardEnemies2.get(j).getX(), hardEnemies2.get(j).getY()) < hardEnemies2.get(j).getWidth() / 2
						&& player2.getBullets().get(i).isVisible() && hardEnemies2.get(j).isVisible()) {
					scorePlayer2 += hardEnemies2.get(j).getPoints();
					hardEnemies2.get(j).setVisible(false);
					// Daño de las balas

					player2.getBullets().get(i).setVisible(false);

					// Enviar mensaje de score
					tcp.getSessions().get(0).sendMessage(" " + scorePlayer2);
				}
			}
			
		}
			

	}

	private void deleteEnemies() {
		for (int i = 0; i < basicEnemies1.size(); i++) {

			if (basicEnemies1.get(i).getY() > 700) {
				gameover = true;

				if (basicEnemies1.get(i).getY() > 750) {
					 gameover = true;

					basicEnemies1.remove(i);

				}
			}
		}

		for (int i = 0; i < basicEnemies2.size(); i++) {

			if (basicEnemies2.get(i).getY() > 700) {
				gameover = true;

				if (basicEnemies2.get(i).getY() > 750) {
					 gameover = true;

					basicEnemies2.remove(i);

				}
			}
		}

		for (int i = 0; i < hardEnemies1.size(); i++) {

			if (hardEnemies1.get(i).getY() > 700) {
				gameover = true;

				if (hardEnemies1.get(i).getY() > 750) {
					 gameover = true;

					hardEnemies1.remove(i);

				}
			}
		}

		for (int i = 0; i < hardEnemies2.size(); i++) {

			if (hardEnemies2.get(i).getY() > 700) {
				gameover = true;

				if (hardEnemies2.get(i).getY() > 750) {
					 gameover = true;

					hardEnemies2.remove(i);

				}
			}
		}
	}

	private void players() {
		// For player 1
		app.imageMode(PConstants.CENTER);
		app.image(player1Img, player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
		player1.coolDown();
		player1.eliminateBullet();

		// Paint player1 bullets
		for (int i = 0; i < player1.getBullets().size(); i++) {
			player1.getBullets().get(i).draw(app);
			player1.getBullets().get(i).moveBullet();

			// System.out.println(player1.getBullets().size());

		}

		// For player 2
		app.imageMode(PConstants.CENTER);
		app.image(player2Img, player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
		player2.coolDown();
		player2.eliminateBullet();

		// Paint player2 bullets
		for (int i = 0; i < player2.getBullets().size(); i++) {
			player2.getBullets().get(i).draw(app);
			player2.getBullets().get(i).moveBullet();

		}

	}
	
	public void whenGameover() {
		if (gameover) {
			
			if (scorePlayer1 < scorePlayer2) {
				winner = "Ganaste Player 2";

			} else if (scorePlayer1 > scorePlayer2) {
				winner = "Ganaste Player 1";

			}else {
				
				winner = "Empate";

			}
		}
	}

	private double distEntreObj(double x1, double x2, double y1, double y2) {
		return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	public boolean isGameover() {
		return gameover;
	}

	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}
	
	public String getWinner() {
		return winner;
	}
	

	@Override
	public void notifyMessage(Session session, String message) {
		if (session.getID().equals("player0")) {

			Player basePlayer = gson.fromJson(message, Player.class);
			player1.setAction(basePlayer.getAction());
			player1.move();

		} else if (session.getID().equals("player1")) {

			Player basePlayer = gson.fromJson(message, Player.class);
			player2.setAction(basePlayer.getAction());
			player2.move();

		}
	}
}
