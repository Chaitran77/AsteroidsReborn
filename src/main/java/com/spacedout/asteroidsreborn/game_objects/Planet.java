package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.spacedout.asteroidsreborn.AsteroidsRebornApplication.to2dp;

public class Planet extends GameObject {

	// diameter calculated from mass - 500000 (mass) = 800px (diameter)
	public static double diameterToMassRatio = 800/500000d;

	public Planet(int x, int y, double depthFromPlayer, String imagePath, GraphicsContext gc, int mass) {
		super(x, y,
				(int)(diameterToMassRatio * mass),
				(int)(diameterToMassRatio * mass),
				depthFromPlayer,
				imagePath,
				gc,
				mass,
				true);
	}

	@Override
	public void draw() {
		this.gc.drawImage(this.image, this.x-(this.width/2), this.y-(this.height/2));
		double maxThrust = Math.sqrt(2 * Math.pow(AsteroidsRebornApplication.player.maxThrustComponentVel, 2));
		double pointOfNoReturnRadius = this.accelerationConstant/maxThrust; // need to incorporate Planet radius

		this.gc.setStroke(Color.WHITE);
		this.gc.setLineWidth(2);
		this.gc.setLineDashes(12);
		this.gc.strokeOval(this.x-((this.width+pointOfNoReturnRadius)/2), this.y-((this.width+pointOfNoReturnRadius)/2), this.width+pointOfNoReturnRadius, this.width+pointOfNoReturnRadius);
		this.gc.setLineWidth(0);
		this.gc.setLineDashes(0);
	}

	@Override
	public void update() {

	}

	@Override
	public String toString() {
		return "Planet{" +
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
				'}';
	}
}
