package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Planet extends GameObject {



	public Planet(int x, int y, int diameter, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass) {
		super(x, y, diameter, diameter, depthFromPlayer, imagePath, gc, mass, true);
	}

	@Override
	public void draw() {
		this.gc.setFill(Paint.valueOf("#FFA500"));
		this.gc.beginPath();
		this.gc.fillOval(this.x-this.width/2d, this.y-this.width/2d, this.width, this.width);
	}

	@Override
	public void update() {

	}
}
