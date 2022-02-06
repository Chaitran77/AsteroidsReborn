package com.spacedout.asteroidsreborn;

import com.spacedout.asteroidsreborn.game_objects.Background;
import com.spacedout.asteroidsreborn.game_objects.GameObject;
import com.spacedout.asteroidsreborn.game_objects.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

// this is where all the backend stuff happens (where we control and store our game objects - asteroids, spaceships etc...)

public class AsteroidsRebornApplication extends Application {


	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(AsteroidsRebornApplication.class.getResource("game-window.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 1080, 720);

		Canvas canvas = (Canvas) scene.lookup("#gameCanvas"); // probs should be global as they will still exist for the same amt of time if global or kept here...
		GraphicsContext gc = canvas.getGraphicsContext2D();

		ArrayList<GameObject> gameObjects = new ArrayList<>(); // doesn't need to be global as not used outside here


		Player player = new Player(0, 0, 60, 60, "file:spaceship.png", gc);

		gameObjects.add(new Background(0, 0, 0, 0, gc, player, 30));
		gameObjects.add(player);

		Mouse.startListening(canvas);

		new AnimationTimer() {

			@Override
			public void handle(long l) {
				gc.setFill(Paint.valueOf("#000"));
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				for (GameObject object: gameObjects) {
					object.draw();
					object.update();
				}
			}
		}.start();

		stage.setTitle("Asteroids Reborn");
		stage.setScene(scene);
		stage.show();
	}

	public static int generateBiasedRandom(double bias, int lowerBound, int upperBound) {
		Random random = new Random();
		double biasNum = random.nextDouble();
		if (biasNum < bias) {
			return random.nextInt(lowerBound, upperBound/2);
		} else {
			return random.nextInt(upperBound/2, upperBound);
		}
	}

	public static void main(String[] args) { launch(); }
}