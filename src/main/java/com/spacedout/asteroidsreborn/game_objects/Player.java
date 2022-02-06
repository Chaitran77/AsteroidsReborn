package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.GameWindowController;
import com.spacedout.asteroidsreborn.Mouse;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Player extends GameObject {
	// player stays in middle of screen but moves through the universe

	protected int dx = 0;
	protected int dy = 0;

	protected double rotation = 0; // degrees

	protected int centreX;
	protected int centreY;


	public Player(int x, int y, int width, int height, String imagePath, GraphicsContext gc) {
		super(x, y, width, height, 0, imagePath, gc);

		Canvas canvas = gc.getCanvas();

		// following saves computing the x and y position of the image, since it is constant (canvas is temporary)
		this.centreX = (int) canvas.getWidth()/2 - width/2;
		this.centreY = (int) canvas.getHeight()/2 - height/2;
	}

	@Override
	public void draw() {

		this.gc.save();
		this.rotate(this.gc, this.rotation, this.gc.getCanvas().getWidth()/2, this.gc.getCanvas().getHeight()/2);
		this.gc.drawImage(this.image, this.centreX, this.centreY, this.width, this.height);

		if (Mouse.isPrimaryButton()) {
			// draw the thruster
			this.gc.setFill(Paint.valueOf("rgba(79, 181, 255, 0.9)"));
			this.gc.beginPath();
			this.gc.moveTo(this.centreX + 20, this.centreY + 60);
			this.gc.lineTo(this.centreX + 30, this.centreY + 80);
			this.gc.lineTo(this.centreX + 40, this.centreY + 60);
			this.gc.fill();
		}
		this.gc.restore();
	}

	@Override
	public void update() {



		if (Mouse.isPrimaryButton()) {
//			apply mouse acceleration (strength)/mouse gravity, 5 = acceleration strength
//			Must use centreX and centreY because it is relative to the screen and so is the mouse
			this.dx += (Math.signum(Mouse.getX()-this.centreX) * -2);
			this.dy += (Math.signum(Mouse.getY()-this.centreY) * -2);
		}

		this.rotation = Math.toDegrees(Math.atan2((Mouse.getY() -this.centreY), (Mouse.getX() -this.centreX))) + 90;


		if (this.dx != 0) {
			this.dx -= (Math.signum(this.dx)*1);
		}
		if (this.dy != 0) {
			this.dy -= (Math.signum(this.dy)*1);
		}
//      instead of applying to player (like following), apply to stars
//		this.x += this.dx;
//		this.y += this.dy;
	}

}
