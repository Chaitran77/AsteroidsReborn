package com.spacedout.asteroidsreborn;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public final class Mouse {
	private static double x;
	private static double y;
	private static boolean primaryButton;

	public static void startListening(Canvas c) {

		EventHandler<MouseEvent> eventHandler = e -> {
			Mouse.x = e.getSceneX();
			Mouse.y = e.getSceneY();
			Mouse.primaryButton = e.isPrimaryButtonDown();
//			System.out.println(Mouse.x + " " + Mouse.y);
		};

		c.addEventFilter(MouseEvent.ANY, eventHandler);
	}



	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static boolean isPrimaryButton() {
		return primaryButton;
	}
}
