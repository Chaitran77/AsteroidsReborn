module com.spacedout.asteroidsreborn {
	requires javafx.controls;
	requires javafx.fxml;
	requires com.google.gson;


	opens com.spacedout.asteroidsreborn to javafx.fxml;
	exports com.spacedout.asteroidsreborn;
}