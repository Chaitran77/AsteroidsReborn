package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ObjectPointer extends GameObject {

	// TODO: ROTATION

	private final GameObject trackingObject;
	private final Image pinImage;
	private final Image objectIcon;

	public ObjectPointer(GraphicsContext gc, String imagePath, GameObject gameObject) {
		super(0, 0, 50, 100, 0, gc, 0); // will be calculated

		this.objectIcon = new Image(imagePath, 40, 40, true, true);
		this.pinImage = new Image("pin.png", 50, 100, true, true);

		this.trackingObject = gameObject;
	}

	@Override
	public void draw() {
		gc.drawImage(this.pinImage, this.x, this.y);
		gc.drawImage(this.objectIcon, this.x, this.y);

	}

	@Override
	public void update() {
		this.x = AsteroidsRebornApplication.constrainToRange(this.trackingObject.x, 0, gc.getCanvas().getWidth());
		this.y = AsteroidsRebornApplication.constrainToRange(this.trackingObject.y, 0, gc.getCanvas().getHeight());
	}
}
