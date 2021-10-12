package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class BasicEnemy {

	private PApplet app;
	
	private int x, y, width, height;
	private int speed, points, chooseEnemy;
	private PImage enemy1, enemy2;
	private boolean visible;

	public BasicEnemy (PApplet app, int x, int y) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.width = 56;
		this.height = 60;
		this.visible = true;
		this.points = 10;
		
		//Random para escoger el enemigo a pintar
		this.chooseEnemy = (int)(Math.random()*2+0);
		
		enemy1 = app.loadImage("./data/easyENEMI1.png");	
		enemy2 = app.loadImage("./data/easyENEMI2.png");		

	}

	public void draw() {
       	//Dependiendo del random anterior, pintar el enemigo
		if (visible) {
			if (chooseEnemy == 0) {
	       		//naranja
	    		app.imageMode(PConstants.CENTER);
	    		app.image(enemy1, x, y, width, height);
			} else {
				//magenta
				app.imageMode(PConstants.CENTER);
				app.image(enemy2, x, y, width, height);
			}		
		}
       
	}
	
	public void move() {
		y+=speed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getPoints() {
		return points;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}