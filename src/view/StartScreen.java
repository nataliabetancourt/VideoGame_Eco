package view;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class StartScreen {

	private PApplet app;
	private PImage background;

	public StartScreen(PApplet app) {
		this.app = app;
		background = app.loadImage("./data/Pantalla de inicio.png");
		
	}
	
	public void draw() {
		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	}

	
}
