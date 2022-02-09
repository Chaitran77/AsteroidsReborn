package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Laser extends GameObject {
    protected int rotation; // doesn't influence travel direction, only referenced for rotation it is drawn at
    protected Paint colour;
    protected double dy; // calculated in constructor from speed
    protected double dx;
    protected Player player;

    public Laser(int x, int y, int width, int height, double depthFromPlayer, GraphicsContext gc, Player player, int rotation, int speed, String colour) {
        super(x, y, width, height, depthFromPlayer, gc);

        this.player = player;

        this.rotation = rotation;
        this.colour = Paint.valueOf(colour);

        // The Sine and Cosine functions work by default in radians
        this.dx = (speed * Math.cos(Math.toRadians(this.rotation)));
        this.dy = (speed * Math.sin(Math.toRadians(this.rotation)));
    }

    @Override
    public void draw() {
        // Save canvas state (rotation = 0deg, other effects), rotate, draw, restore --> player image is drawn rotated
        this.gc.save();
        this.rotate(this.gc, this.rotation, this.gc.getCanvas().getWidth()/2, this.gc.getCanvas().getHeight()/2); // easier than calculating rotated coords
        this.gc.setFill(this.colour);
        this.gc.fillRect(this.x+(this.player.width/2), this.y, this.height, this.width);
        this.gc.restore();
    }

    @Override
    public void update() {
        this.x += this.dx;
        this.y += this.dy;
    }
}
