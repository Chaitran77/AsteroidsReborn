package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.Mouse;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;

import static com.spacedout.asteroidsreborn.AsteroidsRebornApplication.*;

public class Player extends GameObject {
	// player stays in middle of screen but moves through the universe

	protected double dx = 0;
	protected double dy = 0;

	protected double maxThrustComponentVel = 80d; // for thrust from the mouse
	protected double maxUniversalVel = 300d; // TODO: MAXUNIVERSALVEL

	protected double rotation = 0; // degrees

	protected int centreX;
	protected int centreY;

	protected double thrusterLength = 0; // max 100px

	protected GaussianBlur thrusterBlur;


	public Player(int x, int y, int width, int height, String imagePath, GraphicsContext gc, int mass) {
		super(x, y, width, height, 0, imagePath, gc, mass, false);

		Canvas canvas = gc.getCanvas();


		// following saves computing the x and y position of the image, since it is constant (canvas is temporary)
		this.centreX = (int) canvas.getWidth()/2 - width/2;
		this.centreY = (int) canvas.getHeight()/2 - height/2;

		// listen for shoot event
		EventHandler<KeyEvent> keyEventHandler = e -> {
			if (!e.getCharacter().equals(" ")) return;
			System.out.println("shoot");
			this.shoot();
		};

		scene.addEventFilter(KeyEvent.ANY, keyEventHandler);

		this.thrusterBlur = new GaussianBlur(5);
	}

	private void shoot() {
		gameObjects.add(new Laser(this.getCentreX(), this.getCentreY(), 50, 3, 9, gc, this, (int) this.getRotation(), 5, "#0F0"));
	}

	@Override
	public void draw() {

		// Save canvas state (rotation = 0deg, other effects), rotate, draw, restore --> player image is drawn rotated
		this.gc.save();
		this.rotate(this.gc, this.rotation+90, this.gc.getCanvas().getWidth()/2, this.gc.getCanvas().getHeight()/2);
		this.gc.drawImage(this.image, this.centreX, this.centreY, this.width, this.height);

		if (Mouse.isPrimaryButton() || (this.thrusterLength != 0)) {
			// draw the thruster
			this.gc.setFill(Paint.valueOf("rgba(79, 181, 255, 0.9)")); // TODO: Thruster colour
			this.gc.setEffect(this.thrusterBlur);
			this.gc.beginPath();
			this.gc.moveTo(this.centreX + 20, this.centreY + 60);
			this.gc.lineTo(this.centreX + 30, this.centreY + 60 + this.thrusterLength);
			this.gc.lineTo(this.centreX + 40, this.centreY + 60);
			this.gc.fill();
		}
		this.gc.restore();

	}

	@Override
	public String toString() {
		return "Player{" +
				"\n    x=" + to2dp(x) +
				"\n    y=" + to2dp(y) +
				"\n    width=" + width +
				"\n    height=" + height +
				"\n    depthFromPlayer=" + depthFromPlayer +
				"\n    image=" + image +
				"\n    pointerIcon=" + pointerIcon +
				"\n    gc=" + gc +
				"\n    mass=" + mass +
				"\n    accelerationConstant=" + accelerationConstant +
				"\n    requiresLocationBalloon=" + requiresLocationBalloon +
				"\n    dx=" + to2dp(dx) +
				"\n    dy=" + to2dp(dy) +
				"\n    maxComponentVel=" + maxThrustComponentVel +
				"\n    rotation=" + rotation +
				"\n    centreX=" + centreX +
				"\n    centreY=" + centreY +
				"\n    thrusterLength=" + thrusterLength +
				"\n    thrusterBlur=" + thrusterBlur +
				'}';
	}

	@Override
	public void update() {

		if (Mouse.isPrimaryButton()) {
//			apply mouse acceleration (strength)/mouse gravity, 5 = acceleration strength
//			Must use centreX and centreY because it is relative to the screen and so is the mouse

			// if the magnitude of the velocity (given by sqrt of the sum of x and y components squared) < max speed, increase both components
			if (Math.abs(this.dx) < Math.abs(this.maxThrustComponentVel)) { // TODO: Max speed = 100
				this.dx += (-1*Math.cos(Math.toRadians(this.rotation))); // TODO: |Acceleration| = 2, horizontal velocity multiplier
			}
			if (Math.abs(this.dy) < Math.abs(this.maxThrustComponentVel)) {
				this.dy += (-1*Math.sin(Math.toRadians(this.rotation)));
			}

			if (this.thrusterLength < 50) {
				this.thrusterLength += 1; // TODO: Rate of thruster length increase
			}

		} else if (this.thrusterLength > 0) {
			this.thrusterLength -= 2; // TODO: Rate of thruster length decrease
		}

		// impart gravitational acceleration by all other GameObjects on player (this way, only the player is affected, and they don't affect each other)
		for (GameObject object: gameObjects) {
			if (!(object instanceof Player || object instanceof Background)) {
				// if outside circle

					// change each component by the acceleration constant multiplied by the displacement in that component's axis
					// TODO: NOT JUST ADD, SUBTRACT AS WELL!!
					if ((this.x - object.x) != 0) { // otherwise result will be infinity (better than div by zero error)
						this.dx += Math.toDegrees(Math.cos(Math.atan2(this.y- object.y, this.x-object.x))) * object.accelerationConstant/(Math.pow(this.y - object.y, 2) + Math.pow(this.x - object.x, 2));
					}
					if ((this.y - object.y) != 0) {
						this.dy += Math.toDegrees(Math.sin(Math.atan2(this.y- object.y, this.x-object.x))) * object.accelerationConstant/(Math.pow(this.y - object.y, 2) + Math.pow(this.x - object.x, 2));
					}

					// reverse velocity if inside circle
					if ((Math.pow(this.x - object.x, 2) + Math.pow(this.y - object.y, 2)) < Math.pow(object.width/2d, 2)) {
						System.out.println("REV");
						this.dx = -this.dx;
						this.dy = -this.dy;
					}
			}
		}

		// try and get the x and y velocity components equal to 0 if not already
		// the min velocity value must be LESS than the min velocity that can be gained otherwise player will never move!
		if (this.dx != 0d) {
			this.dx -= (Math.signum(this.dx)*0.1); // TODO: |deceleration| = 0.01

			if (Math.abs(this.dx) < 0.1) {
				this.dx = 0; // to prevent jittering
			}
		}
		if (this.dy != 0d) {
			this.dy -= (Math.signum(this.dy)*0.1);

			if (Math.abs(this.dy) < 0.1) {
				this.dy = 0; // to prevent jittering
			}
		}

		// all changes to this.dx and this.dy done so...
		// now move all the other GameObjects
		for (GameObject object: gameObjects) {
			if (!(object instanceof Player)) { // skip player
				if (!(object instanceof Background)) {
//					object.x += (this.dx/4 * (object.depthFromPlayer/10));
//					object.y += (this.dy/4 * (object.depthFromPlayer/10));
					object.x += this.dx;
					object.y += this.dy;
				} else {
					// just skip (already moved in mainloop)
				}
			}
		}



		this.rotation = Math.toDegrees(Math.atan2((Mouse.getY() -this.centreY), (Mouse.getX() -this.centreX))); //cannot + 90 here as that would cause values to exceed 360
		this.rotation = ((this.rotation + 720) % 360);

	}

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	public int getCentreX() {
		return centreX;
	}

	public int getCentreY() {
		return centreY;
	}

	public double getRotation() { return rotation; }
}
