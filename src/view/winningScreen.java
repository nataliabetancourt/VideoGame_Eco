package view;

import processing.core.PApplet;
import processing.core.PConstants;
//import processing.core.PFont;
import processing.core.PImage;

public class winningScreen {

	private PApplet app;	 
	private PImage background;
	
	public winningScreen (PApplet app) {
		
		this.app = app;
		background = app.loadImage("./data/Win.png");
		//font = app.createFont("./data/Poppins-Bold.ttf", 24);		
	
	}
	
	public void draw() {

		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	}
	
	
	
}
