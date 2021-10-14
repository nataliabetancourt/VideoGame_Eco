package view;

import processing.core.PApplet;
import processing.core.PConstants;
//import processing.core.PFont;
import processing.core.PImage;

public class GameOverScreen {

	private PApplet app;	 
	private PImage background;
	
	public GameOverScreen (PApplet app) {
		
		this.app = app;
		background = app.loadImage("./data/Lost.png");
	
	}
	
	public void draw() {

		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	}
	
	
	
}
