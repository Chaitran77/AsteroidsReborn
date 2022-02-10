package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected double depthFromPlayer;
	protected Image image;
	protected GraphicsContext gc;

	protected int mass;
	protected double gravitationalAcceleration;


	public GameObject(int x, int y, int width, int height, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depthFromPlayer = depthFromPlayer;
		this.image = new Image(imagePath);
		this.gc = gc;
		this.mass = mass;
		// calculate gravitationalAcceleration based on mass
		this.gravitationalAcceleration = gravitationalAcceleration;
	}

	// constructor for objects without external images
	public GameObject(int x, int y, int width, int height, double depthFromPlayer, GraphicsContext gc, int mass) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depthFromPlayer = depthFromPlayer;
		this.gc = gc;
		this.mass = mass;
		// calculate gravitationalAcceleration based on mass
		this.gravitationalAcceleration = gravitationalAcceleration;
	}

	protected void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}


	public abstract void draw();

	// must be overridden
	public abstract void update();



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
}
