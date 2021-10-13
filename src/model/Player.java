package model;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Player {
	
	private int x, y, width, height;
	private String action;
	private int coolDown, speed;
	private ArrayList<Bullet> bullets;
	
	public Player (int x, int y, String action) {
		this.x = x;
		this.y = y;
		this.speed = 30;
		this.coolDown = 0;
		this.width = 55;
		this.height = 115;
		this.action = action;
		
		//array
		bullets = new ArrayList <>();
		
	}
	
	public void move() {
		switch (action) {
		case "left":
			x-=speed;
			break;
		case "right":
			x+=speed;
			break;
		case "shoot":
			createBullet();
			System.out.println("disparo");
			break;
		case "no movement":
			break;
		}
	}
	
	public void coolDown() {
		if (coolDown > 0) {
			coolDown--;
		}
	}
	
	private void createBullet() {
		if (coolDown == 0) {
			Bullet bullet = new Bullet(x, y);
			bullets.add(bullet);
			coolDown = 20;

		}
	}
	
	public void eliminateBullet() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).getY() < 0) {
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
	
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getWidth() {
		return width;
	}
    
    public int getHeight() {
		return height;
	}
    
    public ArrayList<Bullet> getBullets() {
		return bullets;
	}

}
