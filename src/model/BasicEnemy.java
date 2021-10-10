package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class BasicEnemy {

	private PApplet app;
	
	private int x, y, width, height, damage;
	private int bounce, life, speed, points;
	
	private PImage enemy1, enemy2;
	
	protected boolean visible;

	public BasicEnemy (PApplet app, int x, int y) {
		
		this.app = app;
		
		this.x = x;
		this.y = y;
		
		this.speed = 5;
		
		this.width = 56;
		this.height = 60;
		
		this.visible = true;
		this.damage = 1;
		this.points = 10;
		
		this.life = 1;
		
		enemy1 = app.loadImage("./data/easyENEMI1.png");	
		enemy2 = app.loadImage("./data/easyENEMI2.png");	
	}

	public void draw() {
		
		//enemy
		//naranja
		app.imageMode(PConstants.CENTER);
		app.image(enemy1, x, y, width, height);
		
		//magenta
		app.imageMode(PConstants.CENTER);
		app.image(enemy2, x, y, width, height);
		
	}
	
	public void move() {
		
		
	}

}