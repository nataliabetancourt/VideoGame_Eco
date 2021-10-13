package model;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bullet {
	
	private int x, y, speed;
	private boolean visible;

	public Bullet (int x, int y) {
		this.x = x;
		this.y = y;
		this.speed = 3;
		this.visible = true;
	}
	
	public void draw(PApplet app) {
		if (visible == true) {
			app.fill (255);
			app.ellipse (x, y, 20, 20);
		}
	}
	
	//movimineto bala de player
	public void moveBullet() {
		y-=speed;
	}
	
	//movimineto bala de enemigo fuerte
	public void moveEnemyBullet() {
		y+=speed;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getY() {
		return y;
	}
	
	

}
