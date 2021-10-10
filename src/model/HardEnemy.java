package model;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class HardEnemy {
	
	protected PApplet app;
	
	private int x, y, width, height;
	private int speed, points;
	protected boolean visible;
	private ArrayList <Bullet> bullets;
	private PImage enemyHard;
	
	public HardEnemy (PApplet app, int x, int y) {
		this.app = app;
		this.x = x;
		this.y = y;
		this.speed = 1;
		this.width = 113;
		this.height = 118;
		this.visible = true;
		this.points = 10;
		
		//ArrayList
		bullets = new ArrayList<>();

		//Imagen
		enemyHard = app.loadImage("./data/hardENEMY.png");
	}
	
	public void draw () {
		if (visible) {
			app.imageMode(PConstants.CENTER);
			app.image(enemyHard, x, y, width, height);
		}
		
		//Proceso de las balas
		createBullet();
		shoot();
		eliminateBullet();
	}
	
	public void move() {
		y+=speed;
	}
	
	public void createBullet() {
		if (app.frameCount%300 == 0) {
			Bullet bullet = new Bullet(app, x, y);
			bullets.add(bullet);
		}
	}

	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw();
			bullets.get(i).moveEnemyBullet();
		}
	}
		
	public void eliminateBullet() {
		for (int i = 0; i <bullets.size(); i++) {
			if (bullets.get(i).getY() > 700) {
				bullets.remove(i);
			}
		}
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

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
}
