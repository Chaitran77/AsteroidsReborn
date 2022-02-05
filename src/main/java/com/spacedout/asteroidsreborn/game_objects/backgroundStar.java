package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class backgroundStar extends GameObject {
	protected int x;
	protected int y;
	protected int depthFromPlayer; // need to apply this scale to other GameObjects
	protected Color colour;


	public backgroundStar(int x, int y, int depthFromPlayer, GraphicsContext gc) {
		// max width = 20px, min = 1px
		super(x, y, 20/depthFromPlayer, 20/depthFromPlayer, depthFromPlayer, gc);

	}

	@Override
	public void draw() {


	}

	@Override
	public void update() {

	}

}
