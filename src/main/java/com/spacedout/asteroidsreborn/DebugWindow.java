package com.spacedout.asteroidsreborn;

import com.spacedout.asteroidsreborn.game_objects.GameObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;

public class DebugWindow extends Stage {

	private static VBox labelContainer;

	// an array of references to the actual GameObject's stored in the gameObjects ArrayList
	// values can be get and set
	private static GameObject[] debuggingGameObjects;
	private static String[] debugStrings;
	private static Label[] debugLabels;

	public static Stage stage = new Stage();


	public DebugWindow(GameObject[] gameObjectsToDebug) throws IOException {
		debuggingGameObjects = gameObjectsToDebug;
		debugStrings = new String[gameObjectsToDebug.length];
		debugLabels = new Label[gameObjectsToDebug.length];

		Scene debugScene = new Scene(new FXMLLoader(AsteroidsRebornApplication.class.getResource("debug-window.fxml")).load(), 400, 900);

		labelContainer = (VBox) debugScene.lookup("#labelContainer");
		for (int i = 0; i < debugLabels.length; i++) {
			debugLabels[i] = new Label();
			labelContainer.getChildren().add(debugLabels[i]);
		}

		stage.setScene(debugScene);
		stage.show();
	}


	public void updateData() {
		// assign the toString() return value of each GameObject to the corresponding element in the debugStrings[]
		for (int i = 0; i < debuggingGameObjects.length; i++) {
			debugStrings[i] = debuggingGameObjects[i].toString();
			debugLabels[i].setText("\n\n" + debugStrings[i]);
		}


	}

}
