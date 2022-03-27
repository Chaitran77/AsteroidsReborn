package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;

public class Enemy extends GameObject {
	// A basic space invaders enemy
	// has a random shooting frequency, shoots every frequency^-1 (=period)
	// usually part of a blockade so not created individually

	public Enemy(int x, int y, int width, int height, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass, boolean requiresLocationBalloon) {
		super(x, y, width, height, depthFromPlayer, imagePath, gc, 0, requiresLocationBalloon);
	}

	@Override
	public void draw() {

	}

	@Override
	public void update() {

	}


}
