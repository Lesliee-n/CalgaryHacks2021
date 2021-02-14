package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreateScreen extends Scene{

	private Pane pane;
	private ScheduleBuilder sBuilder;
	private Label lblName = new Label();
	
	public CreateScreen(/*ScheduleBuilder sBuilder*/Stage primaryStage) {
		super(new Pane(),800,600);
		pane = (Pane) getRoot();
		
		//this.sBuilder = sBuilder;
		
		Button btnCreate = initCreateButton();
		pane.getChildren().addAll(btnCreate);
		
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
