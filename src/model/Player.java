package model;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Player {
	
	private PApplet app;
	private PImage player, bullet;
	private int x, y, width, height;
	private int coolDown, speed;
	private ArrayList<Bullet> bullets;
	
	public Player (PApplet app) {
		this.app = app;
		this.x = 600;
		this.y = 570;
		this.speed = 30;
		this.coolDown = 0;
		this.width = 70;
		this.height = 130;
		
		//Imagenes
		player = app.loadImage("./data/Spaceship.png");
		bullet = app.loadImage("./data/Bullet.png");
		
		//array
		bullets = new ArrayList <>();
		
	}
	
	public void draw() {
		app.imageMode(PConstants.CENTER);
		app.image(player, x, y, width, height);
		
		//CoolDown 
		if (coolDown > 0) {
			coolDown--;
		}	
		
	}
	
	public void createBullet() {
		if (coolDown == 0) {
			Bullet bullet = new Bullet(app, x, y);
			bullets.add(bullet);
			coolDown = 10;
		}
		
	}
	
	public void shoot() {
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw();
			bullets.get(i).moveBullet();
		}
	}
	
	public void eliminateBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getY() < 0) {
				bullets.remove(i);
			}
		}
	}


}
