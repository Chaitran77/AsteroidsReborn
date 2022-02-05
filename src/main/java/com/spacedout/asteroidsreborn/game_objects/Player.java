package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Player extends GameObject {
	// player stays in middle of screen but moves through the universe

	protected int dx = 0;
	protected int dy = 0;

	protected double rotation = 0; // degrees

	protected int centreX;
	protected int centreY;

	protected int depthFromPlayer = 0;


	public Player(int x, int y, int width, int height, String imagePath, GraphicsContext gc) {
		super(x, y, width, height, 0, imagePath, gc);

		// following saves computing the x and y position of the image, since it is constant (canvas is temporary)
		Canvas canvas = gc.getCanvas();
		this.centreX = (int)canvas.getWidth()/2 - width/2;
		this.centreY = (int)canvas.getHeight()/2 - height/2;
	}

	@Override
	public void draw() {

		this.gc.save();
		this.rotate(this.gc, this.rotation, this.gc.getCanvas().getWidth()/2, this.gc.getCanvas().getHeight()/2);
		this.gc.drawImage(this.image, this.centreX, this.centreY, this.width, this.height);
		this.gc.restore();
	}

	@Override
	public void update() {

//		this.dx += 8;
//		this.dy += 4;

		if (this.rotation < 360) {
			this.rotation += 1;
		} else {
			this.rotation = 0;
		}
//
//		this.x += this.dx;
//		this.y += this.dy;
	}

}
