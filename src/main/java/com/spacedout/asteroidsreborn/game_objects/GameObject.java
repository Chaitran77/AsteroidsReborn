package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public abstract class GameObject {
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double depthFromPlayer;
	protected Image image;
	protected Image pointerIcon;
	protected GraphicsContext gc;

	protected int mass;
	protected double accelerationConstant;

	protected boolean requiresLocationBalloon;


	public GameObject(int x, int y, int width, int height, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass, boolean requiresLocationBalloon) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depthFromPlayer = depthFromPlayer;
		this.image = new Image(imagePath, width, height, true, true);
		this.pointerIcon = new Image(imagePath, 40, 40, true, true);
		this.gc = gc;
		this.mass = mass;
		// calculate gravitationalAcceleration based on mass
		this.accelerationConstant = mass * AsteroidsRebornApplication.gravitationalConstant;
		this.requiresLocationBalloon = requiresLocationBalloon;
	}

	// constructor for objects without external images
	public GameObject(int x, int y, int width, int height, double depthFromPlayer, GraphicsContext gc, int mass, boolean requiresLocationBalloon) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depthFromPlayer = depthFromPlayer;
		this.gc = gc;
		this.mass = mass;
		// calculate gravitationalAcceleration based on mass
		this.accelerationConstant = mass * AsteroidsRebornApplication.gravitationalConstant;
		this.requiresLocationBalloon = requiresLocationBalloon;
	}

	protected void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	// TODO: directional explosion (flat explosion rotated to be at tangent to planet surface)
	// in any collision, the GameObject with a smaller mass explodes.

	public abstract void draw();


	// must be overridden

	public abstract void update();


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getMass() {
		return mass;
	}

	public boolean requiresLocationBalloon() {
		return requiresLocationBalloon;
	}
}
