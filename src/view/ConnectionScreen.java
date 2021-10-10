package view;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class ConnectionScreen {
	
	private PApplet app;
	private PImage background;
	private boolean connected;
	
	public ConnectionScreen (PApplet app) {
		this.app = app;
		
		//Fondo
		background = app.loadImage("./data/connection.png");
		
		//Variable para mostrar boton de continuar
		connected = false;
	}
	
	public void draw() {
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		if (connected == false) {
			app.fill(255, 240, 243);
			app.noStroke();
			app.rect(480, 540, 300, 80);
		}
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	
}
