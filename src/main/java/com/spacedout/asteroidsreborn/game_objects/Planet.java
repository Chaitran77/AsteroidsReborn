package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

public class Planet extends GameObject {



	public Planet(int x, int y, int diameter, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass) {
		super(x, y, diameter, diameter, depthFromPlayer, imagePath, gc, mass, true);
	}

	@Override
	public void draw() {
		this.gc.drawImage(this.image, this.x-(this.width/2), this.y-(this.height/2));
	}

	@Override
	public void update() {

	}
}
