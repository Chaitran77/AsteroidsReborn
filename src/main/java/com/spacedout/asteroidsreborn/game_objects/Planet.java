package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Planet extends GameObject {

	public Planet(int x, int y, int diameter, double depthFromPlayer, GraphicsContext gc, int mass) {
		super(x, y, diameter, diameter, depthFromPlayer, gc, mass);
	}

	@Override
	public void draw() {
		this.gc.setFill(Paint.valueOf("#FFF"));
		this.gc.beginPath();
		this.gc.fillOval(this.x, this.y, this.width, this.width);
	}

	@Override
	public void update() {

	}
}
