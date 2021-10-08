package view;
import processing.core.PApplet;

public class Main extends PApplet implements IObserver{

	private TCPLauncher tcp;
	
	public static void main(String[] args) {
		PApplet.main("view.Main");
	}
		
	
	@Override
	public void settings() {
		size(900,700);
		
	}
	
	@Override
	public void setup() {
		tcp = TCPLauncher.getInstance();
		tcp.setObserver(this);
	}
	
	@Override
	public void draw() {
		background(255);
		
		for (int i = 0; i < tcp.getSessions().size(); i++) {
			fill(80);
			ellipse(600, (150*i )+ 100, 50, 50);
		}

	}


	@Override
	public void notifyMessage(Session session, String message) {
		System.out.println("Mensaje recibido de: " + session.getID() + " Dice: " + message);
	}
	
	
}
