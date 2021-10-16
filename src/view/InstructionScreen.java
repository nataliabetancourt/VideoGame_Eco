package view;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class InstructionScreen {

	private PApplet app;
	private PImage background;
	
	public InstructionScreen (PApplet app) {
		this.app = app;
		background = app.loadImage("./data/Instrucciones.png");
		
	}
	
	public void draw() {
		//Dibujar el fondo de la pantalla
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	}
}
