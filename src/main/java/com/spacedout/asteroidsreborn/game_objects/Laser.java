package com.spacedout.asteroidsreborn.game_objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Laser extends GameObject {
    protected int rotation; // doesn't influence travel direction, only referenced for rotation it is drawn at
    protected Paint colour;
    protected double dy; // calculated in constructor from speed
    protected double dx;
    protected Player player;

    // stop and self-destruct after travelling a certain distance
    

    public Laser(int x, int y, int width, int height, double depthFromPlayer, GraphicsContext gc, Player player, int rotation, int speed, String colour) {
        super(x, y, width, height, depthFromPlayer, gc, 1, false);

        this.player = player;
        this.colour = Paint.valueOf(colour);
        this.rotation = rotation;

        // The Sine and Cosine functions work by default in radians
        this.dx = (speed * Math.toDegrees(Math.cos(Math.toRadians(rotation))));
        this.dy = (speed * Math.toDegrees(Math.sin(Math.toRadians(rotation))));


    }

    public void hitObject(GameObject obj) {

    }

    @Override
    public void draw() {
        // Save canvas state (rotation = 0deg, other effects), rotate, draw, restore --> player image is drawn rotated
        this.gc.save();
        this.rotate(this.gc, this.rotation+90, this.x+this.player.width/2d, this.y+this.player.height/2d); // easier than calculating rotated coords
        this.gc.setFill(this.colour);
        this.gc.fillRect(this.x+(this.player.width/2d), this.y-this.player.height+10, this.height, this.width);
        this.gc.restore();
    }

    @Override
    public void update() {
        this.x += this.dx;
        this.y += this.dy;

        // black holes may influence direction, therefore rotation needs to be changed as well
//        this.rotation = Math.atan2()
    }
}
