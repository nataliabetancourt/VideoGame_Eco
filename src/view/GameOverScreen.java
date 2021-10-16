package view;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
//import processing.core.PFont;
import processing.core.PImage;

public class GameOverScreen {

	private PApplet app;	 
	private PImage background;
	private PFont font;
	private String winner;
	
	public GameOverScreen (PApplet app) {
		
		this.app = app;
		background = app.loadImage("./data/GameOver.png");
		
		font = app.createFont("./data/Poppins-Bold.ttf", 24);
	
		winner = "";
	}
	
	public void draw() {

		app.imageMode(PConstants.CORNER);
		app.image(background, 0, 0, 1200, 700);
		app.textFont(font);
		app.textAlign(PConstants.CENTER);
		app.fill(80);
		app.text(winner, 600, 430);
		
	}
	
	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	
	
}
