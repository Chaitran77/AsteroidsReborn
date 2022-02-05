package com.spacedout.asteroidsreborn;

import com.spacedout.asteroidsreborn.game_objects.GameObject;
import com.spacedout.asteroidsreborn.game_objects.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// this is where all the backend stuff happens (where we control and store our game objects - asteroids, spaceships etc...)

public class AsteroidsRebornApplication extends Application {

	private Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(AsteroidsRebornApplication.class.getResource("game-window.fxml"));
		scene = new Scene(fxmlLoader.load(), 1080, 720);

		Canvas canvas = (Canvas) scene.lookup("#gameCanvas");
		GraphicsContext gc = canvas.getGraphicsContext2D();

		ArrayList<GameObject> gameObjects = new ArrayList<>();

		gameObjects.add(new Player(0, 0, 60, 60, "file:spaceship.png", gc));


		new AnimationTimer() {

			@Override
			public void handle(long l) {
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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

	public static void main(String[] args) { launch();}
}