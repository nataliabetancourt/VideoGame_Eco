package view;

import processing.core.PApplet;
import processing.core.PConstants;
//import processing.core.PFont;
import processing.core.PImage;

public class GameOverScreen {

	private PApplet app;	 
	private PImage background;
	/*private PFont font;
	private int scorePlayer1, scorePlayer2, minutes, seconds;
	private int timeCounter;*/
	
	public GameOverScreen (PApplet app) {
		
		this.app = app;
		background = app.loadImage("./data/Lost.png");
		//font = app.createFont("./data/Poppins-Bold.ttf", 24);		
	
	}
	
	public void draw() {

		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
	}
	
	
	
}
