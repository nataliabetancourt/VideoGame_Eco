package view;
import processing.core.PApplet;

public class Main extends PApplet implements IObserver{

	//Classes
	private TCPLauncher tcp;
	private StartScreen start;
	private InstructionScreen instructions;
	private ConnectionScreen connection;
	private PlayScreen play;
	
	//Variables
	private int screen;
	
	public static void main(String[] args) {
		PApplet.main("view.Main");
	}
		
	//prueba terminal
	
	@Override
	public void settings() {
		size(1200,700);
		
	}
	
	@Override
	public void setup() {
		//Classes
		tcp = TCPLauncher.getInstance();
		tcp.setObserver(this);
		
		start = new StartScreen(this);
		instructions = new InstructionScreen(this);
		connection = new ConnectionScreen(this);
		play = new PlayScreen(this);
		
		//Variables
		screen = 0;
		
	}
	
	@Override
	public void draw() {
		background(255);
		drawScreens();
	}
	
	@Override
	public void mousePressed() {
		pressButtons();
	}
	
	private void drawScreens() {
		switch (screen) {
		//Start screen 
		case 0:
			start.draw();
			break;
		//Instruction screen
		case 1:
			instructions.draw();
			break;
		//Connection screen
		case 2:
			connection.draw();
			//Revisar que esten conectados los clientes para permitir pasar de pantalla
			if (tcp.getSessions().size() == 2) {
				connection.setConnected(true);
			}
			break;
		//Play screen
		case 3:
			play.draw();
			break;

		}
	}
	
	private void pressButtons() {
		switch (screen) {
		//Start screen 
		case 0:
			if (mouseX > 492 && mouseX < 709 && mouseY > 433 && mouseY < 493) {
				screen = 1;
			}
			break;
		//Instruction screen
		case 1:
			if (mouseX > 492 && mouseX < 709 && mouseY > 552 && mouseY < 612) {
				screen = 2;
			}
			break;
		//Connection screen
		case 2:
			if (mouseX > 492 && mouseX < 709 && mouseY > 552 && mouseY < 612 && connection.isConnected()) {
					screen = 3;
			}
			break;
		}
	}


	@Override
	public void notifyMessage(Session session, String message) {}
	
}
