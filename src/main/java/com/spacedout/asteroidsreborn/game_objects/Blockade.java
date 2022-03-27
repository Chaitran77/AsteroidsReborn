package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;

public class Blockade extends GameObject {
	public Blockade(int x, int y, int width, int height, double depthFromPlayer, GraphicsContext gc, int mass, boolean requiresLocationBalloon) {
		super(x, y, width, height, depthFromPlayer, gc, mass, requiresLocationBalloon);
	}

	@Override
	public void draw() {

	}

	@Override
	public void update() {

	}
	// this is a grid of enemies (this is added to the ArrayList<gameObject> which then calls this class' draw and update methods, which in turn will call the D&U methods of all the enemies stored here)

}
