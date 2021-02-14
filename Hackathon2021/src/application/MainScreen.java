package application;

import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//test
public class MainScreen extends Scene {
	private HashMap<String, Integer> weekdayList;
	private BorderPane pane;
	GridPane week;
	private int minTime = 8 * 60 * 60;
	StackPane slotsFill;
	private Schedule sb;

	public MainScreen(Schedule sb) {
		super(new BorderPane(), 800, 600);
		slotsFill = new StackPane();
		weekdayList = new HashMap<String, Integer>();
		initWeekday(weekdayList);
		pane = (BorderPane) getRoot();

		HBox hb = new HBox();
		hb.getChildren().add(initClock());
		pane.setBottom(hb);
		hb.setBackground(new Background(new BackgroundFill(Color.rgb(51, 51, 51), CornerRadii.EMPTY, Insets.EMPTY)));

		pane.setLeft(navBar());
		week = weekday();
		pane.getChildren().add(week);
		int dur = 60 * 60;
		/*
		 * setSlot(pane, week, "MATH 329", "FRIDAY", minTime + dur, dur); setSlot(pane,
		 * week, "MATH 329", "MONDAY", minTime + dur, dur); setSlot(pane, week,
		 * "MATH 329", "WEDNESDAY", minTime + 2 * dur, 2 * dur);
		 */
		this.sb = sb;

	}

	public void update(Date dd) {
		pane.getChildren().remove(slotsFill);
		for (Event e : sb.getEvents()) {
			slotsFill = setSlots(pane, week, e, dd);
		}
		pane.getChildren().add(slotsFill);

	}

	private Text initClock() {
		HBox vb = new HBox();
		Text timeL = new Text("TIME");
		timeL.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		timeL.setFill(Color.WHITE);
		vb.getChildren().add(timeL);
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				java.util.Date date = new java.util.Date();
				String t = date.toString();
				timeL.setText(t);
			}
		}, 0, 1000);
		return timeL;
	}

	private void initWeekday(HashMap<String, Integer> hm) {
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
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(61, 61, 61), CornerRadii.EMPTY, Insets.EMPTY)));
		Button toCreateSceneBtn = createButton("ADD EVENT");
		vb.setPrefWidth(150);
		int pad = 20;
		vb.setPadding(new Insets(pad, 0, 0, 0));
		vb.setAlignment(Pos.BASELINE_CENTER);
		toCreateSceneBtn.setOnAction(e -> Main.changeScene(Main.ADD_EVENT));
		toCreateSceneBtn.setOnMouseEntered(e -> {
			vb.setPadding(new Insets(pad, 0, 0, -20));
		});
		toCreateSceneBtn.setOnMouseExited(e -> {
			vb.setPadding(new Insets(pad, 0, 0, 0));
		});
		toCreateSceneBtn.setOnMousePressed(e -> {
			toCreateSceneBtn.setBackground(
					new Background(new BackgroundFill(Color.rgb(133, 91, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		toCreateSceneBtn.setOnMouseReleased(e -> {
			toCreateSceneBtn.setBackground(
					new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		vb.getChildren().add(toCreateSceneBtn);

		return vb;
	}

	private Button createButton(String label) {
		Button btn = new Button(label);
		btn.setPrefWidth(150);
		btn.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		return btn;

	}

	private StackPane setSlots(Pane parent, GridPane gp, Event e, Date dd) {
		StackPane p = new StackPane();
		for (String day : e.getDay()) {
			day = day.toUpperCase();
			StackPane s = setSlot(parent, gp, e.getName(), day,dd.getHours()*60*60 + dd.getMinutes()*60, (long) e.getDuration());
			p.getChildren().add(s);
		}
		System.out.println();
		return p;

	}

	private StackPane setSlot(Pane parent, GridPane gp, String eventName, String day, long start, long duration) {
		start = (int)((start));
		System.out.println(start);

		StackPane vb = new StackPane();
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(240, 0, 72), CornerRadii.EMPTY, Insets.EMPTY)));
		VBox textBox = new VBox();
		textBox.setSpacing(-10);

		Rectangle rec = new Rectangle();
		rec.setFill(Color.rgb(227, 41, 78));
		int secToHour = 60 * 60;
		int height = 30 + 5;
		int width = 80;
		vb.setLayoutX(gp.getLayoutX() + 80 + width * (weekdayList.get(day)));
		vb.setLayoutY(gp.getLayoutY() + 30 + height * (start - minTime) / secToHour);
		vb.setPrefSize(width, height * (duration) / secToHour);
		rec.setLayoutY(height * (start - minTime) / secToHour);
		rec.setWidth(width * 9 / 10);
		rec.setHeight(height * (duration) / secToHour);
		vb.setAlignment(Pos.BASELINE_CENTER);
		vb.getChildren().add(rec);
		Button closeBtn = createCloseButton();
		closeBtn.setOnAction(e -> {
			pane.getChildren().remove(vb);
		});
		textBox.getChildren().add(closeBtn);
		vb.getChildren().add(textBox);
		Text text = new Text(eventName);
		text.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		text.setFill(Color.WHITE);
		textBox.getChildren().add(text);
		textBox.setAlignment(Pos.TOP_CENTER);
		vb.setAlignment(textBox, Pos.TOP_CENTER);
		parent.getChildren().add(vb);

		return vb;
		// test
	}

	private Button createCloseButton() {
		Button btn = new Button("X");
		btn.setTextFill(Color.DARKRED);
		btn.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		btn.setOnMouseEntered(e -> {
			btn.setTextFill(Color.rgb(171, 27, 56));
		});
		btn.setOnMouseExited(e -> {
			btn.setTextFill(Color.DARKRED);
		});
		btn.setOnMousePressed(e -> {
			btn.setTextFill(Color.RED);
		});
		btn.setOnMouseReleased(e -> {
			btn.setTextFill(Color.DARKRED);
		});

		return btn;
	}

	// fix
	private GridPane weekday() {
		int rows = 15;
		String days[] = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		GridPane gp = new GridPane();
		gp.setLayoutX(175);
		gp.setLayoutY(50);
		gp.setVgap(4);
		for (int i = 1; i < rows - 1; i++) {
			Rectangle rec = new Rectangle(0, 0, 40, 30);
			if (i % 2 == 0)
				rec.setFill(Color.GRAY);
			else
				rec.setFill(Color.DARKGRAY);
			Label l = new Label(7 + i + "");
			l.setFont(Font.font("Sans", FontWeight.BOLD, 10));
			l.setTextFill(Color.WHITE);
			gp.setHalignment(l, HPos.RIGHT);
			gp.add(rec, 0, i);
			gp.add(l, 0, i);
		}
		for (int i = 1; i < 8; i++) {
			Rectangle rec = new Rectangle(0, 0, 80, 30);
			if (i % 2 == 0)
				rec.setFill(Color.GRAY);
			else
				rec.setFill(Color.DARKGRAY);
			Label l = new Label(days[i - 1]);
			l.setFont(Font.font("Sans", FontWeight.BOLD, 20));
			l.setTextFill(Color.WHITE);
			gp.add(rec, i, 0);
			gp.add(l, i, 0);
			gp.setHalignment(l, HPos.CENTER);
			for (int j = 1; j < rows - 1; j++) {
				Rectangle timeSlot = new Rectangle(0, 0, 80, 30);
				if ((i) % 2 == 0)
					timeSlot.setFill(Color.BEIGE);
				else
					timeSlot.setFill(Color.WHEAT);
				gp.add(timeSlot, i, j);

			}
		}

		return gp;
	}

}
