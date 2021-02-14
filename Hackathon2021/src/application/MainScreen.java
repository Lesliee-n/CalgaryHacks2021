package application;



import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainScreen extends Scene{
	private BorderPane pane;
	private ScheduleBuilder sb;
	public MainScreen(ScheduleBuilder sb) {
		super(new BorderPane(),800,600);
		pane = (BorderPane) getRoot();
		pane.getChildren().add(weekday());
		this.sb = sb;
		
	}
	//fix
	private GridPane weekday() {
		int rows = 21;
		String days[] = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		GridPane gp = new GridPane();
		gp.setLayoutX(150);
		gp.setLayoutY(100);
		for (int i = 1; i < rows-1; i++) {
			Label l = new Label();
			gp.add(l, 0,i);
		}
		for (int i = 1; i < 8; i++) {
			Rectangle rec = new Rectangle(0,0,80,30);			
			if(i%2 == 0) rec.setFill(Color.GRAY);
			else  rec.setFill(Color.DARKGRAY);
			Label l = new Label(days[i-1]);
			l.setFont(Font.font("Sans", FontWeight.BOLD,20));
			l.setTextFill(Color.WHITE);
			gp.add(rec, i,0);
			gp.add(l, i,0);
			gp.setHalignment(l, HPos.CENTER);
			for (int j = 1; j < rows-1; j++) {
				Rectangle timeSlot = new Rectangle(0,0,80,15);			
				if((i)%2 == 0) timeSlot.setFill(Color.BEIGE);
				else  timeSlot.setFill(Color.WHEAT);
				gp.add(timeSlot, i,j);
				
			}
		}
			
		return gp;
	}

}
