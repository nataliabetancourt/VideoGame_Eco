package view;

import java.util.ArrayList;

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
	private int timeCounter;
	private ArrayList<BasicEnemy> basicEnemies1, basicEnemies2;
	private ArrayList<HardEnemy> hardEnemies1, hardEnemies2;
	private Player player1, player2;
	private boolean lost;
	private Gson gson;
	
	public PlayScreen (PApplet app) {
		this.app = app;
		
		//Comunicacion
		tcp = TCPLauncher.getInstance();
		tcp.setObserver(this);
		gson = new Gson();
		
		//Players
		player1 = new Player(206, 650, "no movement");
		player2 = new Player(973, 650, "no movement");
		
		//Elementos graficos
		background = app.loadImage("./data/Pantalla de juego-1.png");
		font = app.createFont("./data/Poppins-Bold.ttf", 24);
		player1Img = app.loadImage("./data/player1.png");
		player2Img = app.loadImage("./data/player2.png");
		
		//Lists
		basicEnemies1 = new ArrayList<>();
		basicEnemies2 = new ArrayList<>();
		hardEnemies1 = new ArrayList<>();
		hardEnemies2 = new ArrayList<>();
		
		//Variables
		lost = false;
	}
	
	public void draw() {
		//Fondo
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	
		//Datos
		time();
		score();
		
		//Enemigos
		createBasicEnemies();
		drawBasicEnemies();
		drawHardEnemies();
		enemiesBorder();
		deleteEnemies();
		
		//Players
		players();

	}
	
	private void time(){
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
		
		//Estructura para que el tiempo quede adecuado
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

	}
	
	private void createBasicEnemies() {
		//Random para las posiciones de los enemigos
		int xTemp1 = (int) app.random(60, 500);
		int xTemp2 = (int) app.random(650, 1140);
		int yTemp = -60;
		
		//Contador para crear enemigo cada cierto tiempo
		app.frameRate(60);
		
		//Agregar enemigos basicos
		if (app.frameCount%200 == 0 && basicEnemies1.size() < 4) {
			basicEnemies1.add(new BasicEnemy(app, xTemp1, yTemp));
			basicEnemies2.add(new BasicEnemy(app, xTemp2, yTemp));
		}
		
		//Agregar enemigos que disparan		
		if (app.frameCount%500 == 0 && hardEnemies1.size() < 3) {
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
	
	private void enemiesBorder() {
		
		for (int i = 0; i < basicEnemies1.size(); i++) {
		if (basicEnemies1.get(i).getY() > 700) {
			lost = true;
			basicEnemies1.remove(i);
			
			}
		}
		
		for (int i = 0; i < basicEnemies2.size(); i++) {
			if (basicEnemies2.get(i).getY() > 700) {
				lost = true;
				basicEnemies2.remove(i);
				
			}
		}
		
		for (int i = 0; i < hardEnemies1.size(); i++) {
			if (hardEnemies1.get(i).getY() > 700) {
				lost = true;
				hardEnemies1.remove(i);
				
			}
		}

		for (int i = 0; i < hardEnemies2.size(); i++) {
			if (hardEnemies2.get(i).getY() > 700) {
				lost = true;
				hardEnemies2.remove(i);
				
			}
		}		
	}
	
	private void deleteEnemies() {
		for (int i = 0; i < basicEnemies1.size(); i++) {
			if (basicEnemies1.get(i).getY() > 700) {
				basicEnemies1.remove(i);
			}
		}
		
		for (int i = 0; i < basicEnemies2.size(); i++) {
			if (basicEnemies2.get(i).getY() > 700) {
				basicEnemies2.remove(i);
			}
		}
		
		for (int i = 0; i < hardEnemies1.size(); i++) {
			if (hardEnemies1.get(i).getY() > 700) {
				hardEnemies1.remove(i);
			}
		}

		for (int i = 0; i < hardEnemies2.size(); i++) {
			if (hardEnemies2.get(i).getY() > 700) {
				hardEnemies2.remove(i);
			}
		}
	}
	
	private void players() {
		//For player 1
		app.imageMode(PConstants.CENTER);
		app.image(player1Img, player1.getX(), player1.getY(), player1.getWidth(), player1.getHeight());
		player1.coolDown();
		player1.eliminateBullet();
		
		//Paint player1 bullets
		for (int i = 0; i < player1.getBullets().size(); i++) {
			player1.getBullets().get(i).draw(app);
			player1.getBullets().get(i).moveBullet();
			System.out.println(player1.getBullets().size());
		}

		
		//For player 2
		app.imageMode(PConstants.CENTER);
		app.image(player2Img, player2.getX(), player2.getY(), player2.getWidth(), player2.getHeight());
		player2.coolDown();
		player2.eliminateBullet();
		
		//Paint player2 bullets
		for (int i = 0; i < player2.getBullets().size(); i++) {
			player2.getBullets().get(i).draw(app);
			player2.getBullets().get(i).moveBullet();
		}

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

