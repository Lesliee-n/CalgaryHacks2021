package application;



import java.util.HashMap;

import org.w3c.dom.css.Rect;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainScreen extends Scene{
	private HashMap<String, Integer>weekdayList;
	private BorderPane pane;
	private int minTime = 8*60*60;
	private ScheduleBuilder sb;
	public MainScreen(ScheduleBuilder sb) {
		super(new BorderPane(),800,600);
		weekdayList = new HashMap<String, Integer>();
		initWeekday(weekdayList);
		pane = (BorderPane) getRoot();
		pane.setLeft(navBar());
		GridPane week = weekday();
		pane.getChildren().add(week);
		int dur = 60*60;
		pane.getChildren().add(setSlot(week, "MATH 329", "FRIDAY", minTime+2*dur,2*dur));
		this.sb = sb;
		
	}
	private void initWeekday( HashMap<String, Integer>hm) {
		hm.put("SUNDAY", 0);
		hm.put("MONDAY", 1);
		hm.put("TUESDAY", 2);
		hm.put("WEDNESDAY", 3);
		hm.put("THURSDAY", 4);
		hm.put("FRIDAY", 5);
		hm.put("SATURDAY", 6);
	}
	
	private VBox navBar() {
		VBox vb = new VBox();	
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(250, 250, 250), CornerRadii.EMPTY, Insets.EMPTY)));
		Button toCreateSceneBtn = createButton("ADD EVENT");
		vb.setPrefWidth(150);
		int pad = 20;
		vb.setPadding(new Insets(pad,0,0,0));
		vb.setAlignment(Pos.BASELINE_CENTER);
		toCreateSceneBtn.setOnAction(e -> Main.changeScene(Main.ADD_EVENT));
		toCreateSceneBtn.setOnMouseEntered(e -> {vb.setPadding(new Insets(pad,0,0,-20));});
		toCreateSceneBtn.setOnMouseExited(e ->  {vb.setPadding(new Insets(pad,0,0,0));});
		toCreateSceneBtn.setOnMousePressed(e -> {toCreateSceneBtn.setBackground(new Background(new BackgroundFill(Color.rgb(133, 91, 0), CornerRadii.EMPTY, Insets.EMPTY)));});
		toCreateSceneBtn.setOnMouseReleased(e -> {toCreateSceneBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));});
		vb.getChildren().add(toCreateSceneBtn);
		
		return vb;
	}
	private Button createButton(String label) {
		Button btn = new Button(label);
		btn.setPrefWidth(150);
		btn.setFont(Font.font("Sans", FontWeight.BOLD,10));
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		return btn;
		
	}
	private VBox setSlot(GridPane gp, String eventName, String day, int start, int duration) {
		VBox vb = new VBox();
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(240, 0, 72), CornerRadii.EMPTY, Insets.EMPTY)));
		Rectangle rec = new Rectangle();
		rec.setFill(Color.rgb(240, 0, 72));
		int secToHour = 60*60;
		int height = 20;
		int width = 80;
		int hour = start/secToHour;
		int min = start%secToHour;
		vb.setLayoutX(gp.getLayoutX()+80+width*(weekdayList.get(day)));
		vb.setLayoutY(gp.getLayoutY()+30 + height*(start - minTime)/secToHour);
		System.out.println((start - minTime)/secToHour);
		vb.setPrefSize(width, height*(duration)/secToHour);
		rec.setLayoutY(height*(start - minTime)/secToHour);
		rec.setWidth(width*5/6);
		rec.setHeight(height*(duration)/secToHour);
		vb.setAlignment(Pos.BASELINE_CENTER);
		vb.getChildren().add(createCloseButton());
		vb.getChildren().add(rec);
		return  vb;
		//test
	}
	
	private Button createCloseButton() {
		Button btn = new Button("X");
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0), CornerRadii.EMPTY, Insets.EMPTY)));
		btn.setOnMouseEntered(e -> {btn.setTextFill(Color.GRAY);});
		btn.setOnMouseExited(e ->  {btn.setTextFill(Color.WHITE);});
		btn.setOnMousePressed(e -> {btn.setTextFill(Color.RED);});
		btn.setOnMouseReleased(e -> {btn.setTextFill(Color.WHITE);});
		
		return btn;
	}
	
	
	
	//fix
	private GridPane weekday() {
		int rows = 15;
		String days[] = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		GridPane gp = new GridPane();
		gp.setLayoutX(175);
		gp.setLayoutY(100);
		for (int i = 1; i < rows-1; i++) {
			Rectangle rec = new Rectangle(0,0,40,20);			
			if(i%2 == 0) rec.setFill(Color.GRAY);
			else  rec.setFill(Color.DARKGRAY);
			Label l = new Label(7+i+"");
			l.setFont(Font.font("Sans", FontWeight.BOLD,10));
			l.setTextFill(Color.WHITE);
			gp.setHalignment(l, HPos.RIGHT);
			gp.add(rec, 0,i);
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
				Rectangle timeSlot = new Rectangle(0,0,80,20);			
				if((i)%2 == 0) timeSlot.setFill(Color.BEIGE);
				else  timeSlot.setFill(Color.WHEAT);
				gp.add(timeSlot, i,j);
				
			}
		}
			
		return gp;
	}

}
