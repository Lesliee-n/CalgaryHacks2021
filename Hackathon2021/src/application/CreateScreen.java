package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreateScreen extends Scene {

	private BorderPane pane;
	private ScheduleBuilder sb;
	private Label lblName = new Label();

	public CreateScreen(ScheduleBuilder sb) {
		super(new BorderPane(), 800, 600);
		pane = (BorderPane) getRoot();
		pane.getChildren().add(entries());

		// this.sBuilder = sBuilder;
	}

	public GridPane entries() {
		GridPane gp = new GridPane();

		// setting gap and padding
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));
		
		Button btnCreate = initCreateButton();
		gp.add(btnCreate,1,2);
		
		return gp;
	}

	public Button initCreateButton() {
		Button btnCreate = new Button("Create");

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				// TODO Auto-generated method stub

			}

		};

		btnCreate.setOnAction(event);
		return btnCreate;
	}

}
