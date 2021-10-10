package model;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class HardEnemy {
	
	protected PApplet app;
	
	private int x, y, width, height, damage;
	private int bounce, life, speed, points;
	
	protected boolean visible;
	
	private ArrayList <Bullet> bullets;
	
	private PImage Henemy;
	
	public HardEnemy (PApplet app, int x, int y) {
		
		this.app = app;
		
		this.x = x;
		this.y = y;
		
		this.speed = 8;
		
		this.width = 113;
		this.height = 118;
		
		this.visible = true;
		this.damage = 1;
		this.points = 10;
		
		this.life = 2;
		
		//ArrayList
		bullets = new ArrayList<>();

		Henemy = app.loadImage("./data/hardENEMY.png");
		
	}
	
	public void draw () {
		
		app.imageMode(PConstants.CENTER);
		app.image(Henemy, x, y, width, height);
		
	}
	
	//no se si lo vamos hacer asi
	
	/*public void creaBala() {
		
		if (app.frameCount%80 == 0) {
			
			Bullet bala = new Bullet(app, x, y);
			bullets.add(bala);
			
		}

	}
	
	
	
	public void disparo() {
			
		for (int i = 0; i < bullets.size(); i++) {
				
			bullets.get(i).draw();
			bullets.get(i).moveEnemyHard();
				
		}
	}
		
	public void eliminarBala() {
			
		for (int i = 0; i <bullets.size(); i++) {
				
			if (bullets.get(i).getY() > 700) {
					
				bullets.remove(i);
				
			}
			}
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getBounce() {
		return bounce;
	}

	public void setBounce(int bounce) {
		this.bounce = bounce;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public PImage getHenemy() {
		return Henemy;
	}

	public void setHenemy(PImage henemy) {
		Henemy = henemy;
	}
	
	


}
