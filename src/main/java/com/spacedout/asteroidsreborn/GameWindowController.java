package com.spacedout.asteroidsreborn;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

// this is where we can interact with the window (like getting mouse and keyboard events as well as draw to the canvas)

public class GameWindowController {
	@FXML
	private Label welcomeText;

	@FXML
	protected void onHelloButtonClick() {
		welcomeText.setText("Welcome to JavaFX Application!");
	}
}