package application;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

//let me sleep

public class CreateScreen extends Scene {

	private AnchorPane pane;
	private Schedule s;
	private TextField tfs[] = new TextField[7];
	List<CheckBox> weekdays = new ArrayList<CheckBox>();
	String daysArray[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

	public CreateScreen(Schedule s) {
		super(new AnchorPane(), 800, 600);
		pane = (AnchorPane) getRoot();
		pane.getChildren().add(entries());
		this.s = s;
	}

	public GridPane entries() {

		GridPane gp = new GridPane();
		gp.setLayoutX(30);
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
		Text txtTime = new Text("Time (24-hour clock):");
		gp.add(txtTime, 0, 3);
		Text txtDuration = new Text("Duration:");
		gp.add(txtDuration, 0, 4);
		Text txtURL = new Text("Meeting URL:");
		gp.add(txtURL, 0, 5);
		Text txtPassword = new Text("Meeting Password (Optional):");
		gp.add(txtPassword, 0, 6);

		// Creates text fields
		for (int i = 0; i < 7; i++) {
			TextField tf = new TextField();
			tfs[i] = tf;
		}
		
		gp.add(tfs[0], 1, 1);

		// Creates checkboxes for days
		GridPane gpcb = new GridPane();
		gpcb.setAlignment(Pos.CENTER);
		gpcb.setHgap(10);
		for (int i = 0; i < daysArray.length; i++) {
			CheckBox cb = new CheckBox(daysArray[i]);
			gpcb.add(cb, i, 0);
			weekdays.add(cb);
		}
		gp.add(gpcb, 1, 2);

		// Creates text fields for time entry
		GridPane gpTime = new GridPane();
		gpTime.setAlignment(Pos.CENTER);
		gpTime.setHgap(10);
		gpTime.add(tfs[1], 0, 0);
		Text txtColon = new Text(":");
		gpTime.add(txtColon, 1, 0);
		gpTime.add(tfs[2], 2, 0);
		gp.add(gpTime, 1, 3);

		// Creates text fields for time entry
		GridPane gpDuration = new GridPane();
		gpDuration.setAlignment(Pos.CENTER);
		gpDuration.setHgap(10);
		gpDuration.add(tfs[3], 0, 0);
		Text txtColon2 = new Text(":");
		gpDuration.add(txtColon2, 1, 0);
		gpDuration.add(tfs[4], 2, 0);
		gp.add(gpDuration, 1, 4);
		
		gp.add(tfs[5], 1, 5);
		gp.add(tfs[6], 1, 6);

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
				boolean correct = true; 
				int count = 0;
				
				//Error handling name
				if(tfs[0].getText().length() < 1)  {
					tfs[0].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[0].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling days
				boolean empty = true;
				for(int i = 0; i < weekdays.size(); i++) {
					if(weekdays.get(i).isSelected() == true) {
						empty = false;
						count++;
					}
				}
				
				if(empty == true) {
					for(int i = 0; i < weekdays.size(); i++) {
						weekdays.get(i).setStyle("-fx-inner-border: #f98585");
					}
					correct = false;
				}else {
					for(int i = 0; i < weekdays.size(); i++) {
						weekdays.get(i).setStyle("-fx-inner-border: #ffffff");
					}
				}
				
				
				//Error handling meeting URL
				if(isNumeric(tfs[5].getText()) || tfs[5].getText().length() < 1)  {
					tfs[5].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[5].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling time hours
				if(!isNumeric(tfs[1].getText()) || tfs[1].getText().length() < 1 || Integer.parseInt(tfs[1].getText()) < 0 || Integer.parseInt(tfs[1].getText()) > 23) {
					tfs[1].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[1].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling time minutes
				if(!isNumeric(tfs[2].getText()) || tfs[2].getText().length() < 1 || Integer.parseInt(tfs[2].getText()) < 0 || Integer.parseInt(tfs[2].getText()) > 59) {
					tfs[2].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[2].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling duration hours
				if(!isNumeric(tfs[3].getText()) || tfs[3].getText().length() < 1 || Integer.parseInt(tfs[3].getText()) < 0 || Integer.parseInt(tfs[3].getText()) > 23) {
					tfs[3].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[3].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling duration minutes
				if(!isNumeric(tfs[4].getText()) || tfs[4].getText().length() < 1 || Integer.parseInt(tfs[4].getText()) < 0 || Integer.parseInt(tfs[4].getText()) > 59) {
					tfs[4].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[4].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				//Error handling meetingURL
				if(isNumeric(tfs[5].getText()) || tfs[5].getText().length() < 1)  {
					tfs[5].setStyle("-fx-control-inner-background: #f98585");
					correct = false;
				} else {
					tfs[5].setStyle("-fx-control-inner-background: #ffffff");
				}
				
				
				if (correct == true) {
					//make day array
					String days[] = new String[count];
					for(int i = 0; i < weekdays.size(); i++) {
						if(weekdays.get(i).isSelected() == true) {
							days[i] = daysArray[i];
						}
					}
					
					//converting duration to seconds
					int duration = (Integer.parseInt(tfs[3].getText()) * 3600) + (Integer.parseInt(tfs[4].getText()) * 60);
					
					
					//if there is no password
					if(tfs[6].getText().length() == 0) {
						s.addEvent(new Event(tfs[0].getText(), days, Integer.parseInt(tfs[1].getText()), Integer.parseInt(tfs[2].getText()), duration, tfs[5].getText()));
					}else {
						s.addEvent(new Event(tfs[0].getText(), days, Integer.parseInt(tfs[1].getText()), Integer.parseInt(tfs[2].getText()), duration, tfs[5].getText(), tfs[6].getText()));
					}
					Main.changeScene(Main.MAIN);
				}
				
			}

		};

		btnCreate.setOnAction(event);
		return btnCreate;
	}

	public boolean isNumeric(String text) {
		boolean numeric = true;
		try {
            Double num = Double.parseDouble(text);
        } catch (NumberFormatException e) {
            numeric = false;
        }
		
		return numeric;
	}
}
