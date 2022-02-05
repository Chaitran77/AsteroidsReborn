package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class backgroundStar extends GameObject {
	protected int x;
	protected int y;
	protected int depthFromPlayer; // need to apply this scale to other GameObjects
	protected Color colour;


	public backgroundStar(int x, int y, int width, int height, int depthFromPlayer, GraphicsContext gc) {
		super(x, y, width, height, depthFromPlayer, gc);
	}

	@Override
	public void draw() {


	}

	@Override
	public void update() {

	}

}
