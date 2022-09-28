package com.spacedout.asteroidsreborn;

import com.google.gson.stream.JsonReader;
import com.spacedout.asteroidsreborn.game_objects.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.*;
// this is where all the backend stuff happens (where we control and store our game objects - asteroids, spaceships etc...)

public class  AsteroidsRebornApplication extends Application {

	private static final boolean debugging = true;

	public static ArrayList<GameObject> gameObjects;
	public static Scene scene;

	public static double gravitationalConstant = 0.030;

	public static Player player;

	private static DebugWindow debuggingWindow;


	@Override
	public void start(Stage stage) throws IOException {

		Rectangle2D screenDimensions = Screen.getPrimary().getVisualBounds();
		FXMLLoader fxmlLoader = new FXMLLoader(AsteroidsRebornApplication.class.getResource("game-window.fxml"));

		scene = new Scene(fxmlLoader.load(), 1920, 1080);

		Canvas canvas = (Canvas) scene.lookup("#gameCanvas"); // probs should be global as they will still exist for the same amt of time if global or kept here...
		canvas.setWidth(1920);
		canvas.setHeight(1080);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gameObjects = new ArrayList<>();// doesn't need to be global as not used outside here

		player = new Player(0, 0, 60, 60, "file:spaceship.png", gc, 10);
		gameObjects.add(new Background(0, 0, 0, 0, gc, player, 70));
		gameObjects.add(player);
		gameObjects.add(new Planet(2000, 2000,  9, "file:purple-planet.png", gc, 500000));
		gameObjects.add(new Planet(-1000, -1000, 9, "file:purple-planet.png", gc, 700000));


		if (debugging) {
			// all the GameObjects now present in the gameObjects ArrayList will be debugged
			debuggingWindow = new DebugWindow(gameObjects.toArray(new GameObject[0]));
		}
		LocationBalloon locationBalloon = new LocationBalloon(gc);

		// correct positions of all GameObjects
		for (GameObject object: gameObjects) {
			object.setX((int) (object.getX() + canvas.getWidth()/2));
			object.setY((int) (object.getY() + canvas.getHeight()/2));
		}

		int currentStage = 0;

		Mouse.startListening(canvas);

		new AnimationTimer() {

			@Override
			public void handle(long l) {
				gc.setFill(Paint.valueOf("#000"));
				gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

				for (GameObject object: gameObjects) {
					object.draw();
					object.update();

					if (object.requiresLocationBalloon()) {
						locationBalloon.draw(object, player);
					}

				}

				if (debugging) {
					debuggingWindow.updateData();

					// draw debug graphics
					gc.setStroke(Paint.valueOf("#FFF"));

					gc.moveTo(player.getCentreX(), player.getCentreY());
					gc.lineTo(player.getCentreX()-(player.getDx()), player.getCentreY());
					gc.stroke();

					gc.moveTo(player.getCentreX(), player.getCentreY());
					gc.lineTo(player.getCentreX(), player.getCentreY()-(player.getDy()));
					gc.stroke();
				}
			}
		}.start();

		stage.setTitle("Asteroids Reborn");
		stage.setScene(scene);
		stage.show();
	}

	private static void generateStage(int stageIndex) {
		Gson gson = new Gson();
		try {
			File stagesFile = new File(AsteroidsRebornApplication.class.getResource("stages.json").toURI());
			JSONSchemaClasses.Stage[] stages = gson.fromJson(new FileReader(stagesFile), JSONSchemaClasses.Stage[].class);

			JSONSchemaClasses.Stage stageData = stages[stageIndex];

//			for (:
//			     stageData.) {
//
//			}

		} catch (URISyntaxException | FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("Couldn't load stages file :(");
		}
	}


	public static int generateBiasedRandom(double bias, int lowerBound, int upperBound) {
		Random random = new Random();
		double biasNum = random.nextDouble();
		if (biasNum < bias) {
			return random.nextInt(upperBound/2) - lowerBound;
		} else {
			return random.nextInt(upperBound) - upperBound/2;
		}
	}

	public static double constrainToRange(double value, double min, double max) {
		return Math.max(min, Math.min(value, max));
	}

	public static double to2dp(double number) {
		return Math.round(number*100)/100D;
	}

	public static void main(String[] args) { launch(); }
}