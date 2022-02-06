package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class backgroundStar extends GameObject {

	protected Color colour;
	protected Player player;
	private final Canvas canvas;

	public backgroundStar(int x, int y, int depthFromPlayer, Player player, GraphicsContext gc) {
		// max width = 20px, min = 1px
		super(x, y, depthFromPlayer, depthFromPlayer, depthFromPlayer, gc);

		this.player = player;
		this.canvas = gc.getCanvas();

	}

	@Override
	public void draw() {
		this.gc.setFill(Paint.valueOf("#FFF"));
		this.gc.fillOval(this.x, this.y, this.width, this.height);
	}

	@Override
	public void update() {

		this.x += this.player.dx;
		this.y += this.player.dy;

		if (this.x > this.canvas.getWidth()) {
			this.x -= (this.canvas.getWidth()+10);
		} else if (this.x < 0) {
			this.x += (this.canvas.getWidth()+10);
		}

		if (this.y > this.canvas.getHeight()) {
			this.y -= (this.canvas.getHeight()+10);
		} else if (this.y < 0) {
			this.y += (this.canvas.getHeight()+10);
		}
	}

}
