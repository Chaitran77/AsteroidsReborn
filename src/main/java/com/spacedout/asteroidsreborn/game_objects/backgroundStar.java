package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;

public class backgroundStar extends GameObject {

	protected Color colour;
	protected Player player;
	private final Canvas canvas;
	private Shadow shadow;

	public backgroundStar(int x, int y, int depthFromPlayer, Player player, GraphicsContext gc, Color colour) {
		// max width = 10px, min = 1px
		// bg stars don't have mass
		super(x, y, depthFromPlayer, depthFromPlayer, depthFromPlayer, gc, 0);

		this.player = player;
		this.canvas = gc.getCanvas();
		this.colour = colour;
		this.shadow = new Shadow(this.width+5, this.colour); // TODO: Blurring of stars (strength)
		this.shadow.setBlurType(BlurType.GAUSSIAN);
	}

	@Override
	public void draw() {
//		this.gc.save();
//		this.gc.setEffect(new Bloom(1));


		this.gc.setFill(this.colour);
		this.gc.fillOval(this.x, this.y, this.width, this.height);
//		this.gc.restore();
	}

	@Override
	public void update() {

		// to make smaller (further away) stars move slower, multiply the dx and dy values of the player by (1/this.depthFromPlayer)/10 = 10/this.depthFromPlayer
		// this.depthFromPlayer has max value of 10 (far) and min of 1 (close), so dx(10) = 10/(10) = 1 (slow), dx(1) = 10/(1) = 10 (fast)
		// also divide the original dx and dy values by 5 so the closest stars don't seem too close.
		this.x += (this.player.dx/4 * (this.depthFromPlayer/10));
		this.y += (this.player.dy/4 * (this.depthFromPlayer/10));

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
