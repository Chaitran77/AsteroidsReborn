package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.image.Image;

public class backgroundImageSection extends Image {
	protected double x;
	protected double y;

	public backgroundImageSection(String s, double x, double y) {
		super(s);

		this.x = x;
		this.y = y;
	}

}
