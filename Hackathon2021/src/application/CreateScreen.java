package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class CreateScreen extends Scene{

	private BorderPane pane;
	
	public CreateScreen() {
		super(new BorderPane(),800,600);
		pane = (BorderPane) getRoot();
	}

}
