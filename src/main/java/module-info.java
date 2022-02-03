module com.spacedout.asteroidsreborn {
	requires javafx.controls;
	requires javafx.fxml;


	opens com.spacedout.asteroidsreborn to javafx.fxml;
	exports com.spacedout.asteroidsreborn;
}