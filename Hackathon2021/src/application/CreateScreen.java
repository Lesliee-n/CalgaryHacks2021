package application;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateScreen extends Scene {

	private AnchorPane pane;
	private ScheduleBuilder sb;

	public CreateScreen(ScheduleBuilder sb) {
		super(new AnchorPane(), 800, 600);
		pane = (AnchorPane) getRoot();
		pane.getChildren().add(entries());
		this.sb = sb;
	}

	public GridPane entries() {
		String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		List<CheckBox> weekdays = new ArrayList<CheckBox>();

		GridPane gp = new GridPane();
		gp.setLayoutX(100);
		gp.setLayoutY(150);

		// setting gap and padding
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(25, 25, 25, 25));

		//Creates title, puts it in hbox and adds hbox to grid pane
		HBox hbTitle = new HBox(10);
		hbTitle.setAlignment(Pos.CENTER);
		Text txtTitle = new Text("New Event");
		hbTitle.setAlignment(Pos.CENTER);
		hbTitle.getChildren().add(txtTitle);
		gp.add(hbTitle, 0, 0, 2, 1);
		
		// Creates labels and adding them to the grid pane
		Text txtName = new Text("Name:");
		gp.add(txtName, 0, 1);
		Text txtDates = new Text("Date(s):");
		gp.add(txtDates, 0, 2);
		Text txtTime = new Text("Time:");
		gp.add(txtTime, 0, 3);
		Text txtDuration = new Text("Duration:");
		gp.add(txtDuration, 0, 4);
		Text txtURL = new Text("Meeting URL:");
		gp.add(txtURL, 0, 5);
		Text txtPassword = new Text("Password:");
		gp.add(txtPassword, 0, 6);

		// Creates text fields and adding them to the grid pane
		TextField tfName = new TextField();
		gp.add(tfName, 1, 1);
		TextField tfURL = new TextField();
		gp.add(tfURL, 1, 5);
		TextField tfPassword = new TextField();
		gp.add(tfPassword, 1, 6);

		// Creates checkboxes for days
		GridPane gpcb = new GridPane();
		gpcb.setAlignment(Pos.CENTER);
		gpcb.setHgap(10);
		for (int i = 0; i < days.length; i++) {
			CheckBox cb = new CheckBox(days[i]);
			gpcb.add(cb, i, 0);
			weekdays.add(cb);
		}
		gp.add(gpcb, 1, 2);

		// Creates text fields for time entry
		GridPane gpTime = new GridPane();
		gpTime.setAlignment(Pos.CENTER);
		gpTime.setHgap(10);
		TextField tfTimeH = new TextField();
		gpTime.add(tfTimeH, 0, 0);
		Text txtColon = new Text(":");
		gpTime.add(txtColon, 1, 0);
		TextField tfTimeM = new TextField();
		gpTime.add(tfTimeM, 2, 0);
		gp.add(gpTime, 1, 3);

		// Creates text fields for time entry
		GridPane gpDuration = new GridPane();
		gpDuration.setAlignment(Pos.CENTER);
		gpDuration.setHgap(10);
		TextField tfDurationH = new TextField();
		gpDuration.add(tfDurationH, 0, 0);
		Text txtColon2 = new Text(":");
		gpDuration.add(txtColon2, 1, 0);
		TextField tfDurationM = new TextField();
		gpDuration.add(tfDurationM, 2, 0);
		gp.add(gpDuration, 1, 4);

		// Puts create button inside an hbox and adds the hbox to grid pane
		Button btnCreate = initCreateButton();
		HBox hbBtnCreate = new HBox(10);
		hbBtnCreate.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtnCreate.getChildren().add(btnCreate);
		gp.add(hbBtnCreate, 0, 8, 2, 1);

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
