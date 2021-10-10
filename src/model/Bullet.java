package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bullet {
	
	private PApplet app;
	
	private int x, y, speed, damage;
	
	private boolean visible;

	public Bullet (PApplet app, int x, int y) {
		
		this.app = app;
		
		this.x = x;
		this.y = y;
		
		this.speed = 10;
		
		this.visible = true;
		
		this.damage = 2;

	}
	
	public void draw() {
		
		if (visible == true) {
		
			/*fill (255);
			ellipse (x, y, 20, 20);*/
			
		}
	}
	
	//movimineto bala de player
	public void moveBala() {
		
		y-=speed;
		
	}
	
	//no se si esto si se hace aca 
	/*movimineto bala de enemigo fuerte
	public void moveEnemyHard() {
		
		y+=speed;

	}*/

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
