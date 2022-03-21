package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class LocationBalloon extends GameObject {

	// only create 1 which is used to draw balloons for all objects

	// TODO: ROTATION

	private final Image pinImage;

	public LocationBalloon(GraphicsContext gc) {
		super(0, 0, 50, 100, 0, gc, 0, false); // will be calculated

		this.pinImage = new Image("file:pin.png", 50, 100, true, true);
	}

	@Override
	public void draw() {}
	public void update() {}

	public void draw(GameObject object, GameObject player) {

		Canvas canvas = this.gc.getCanvas();

		double x = AsteroidsRebornApplication.constrainToRange(object.x, 0, gc.getCanvas().getWidth()-100);
		double y = AsteroidsRebornApplication.constrainToRange(object.y, 0, gc.getCanvas().getHeight()-100);

		if (!(((x < canvas.getWidth()-100)&&(x > 0)) && ((y < canvas.getHeight()-100)&&(y > 0)))) {
			double rotation = Math.toDegrees(Math.atan2(object.y - player.y, object.x - player.x)) + 270;

			this.gc.save();
			this.rotate(this.gc, rotation, x + 25, y + 50);
			this.gc.drawImage(this.pinImage, x, y);
			this.gc.drawImage(object.pointerIcon, x+(this.pinImage.getWidth()/2-object.pointerIcon.getWidth()/2), y+5);
			this.gc.restore();
		}
	}




}
