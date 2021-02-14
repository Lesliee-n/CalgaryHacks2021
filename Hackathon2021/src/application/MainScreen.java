package application;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;

//test
public class MainScreen extends Scene {
	private HashMap<String, Integer> weekdayList;
	private HashMap<String, ArrayList<StackPane>> classes;
	private BorderPane pane;
	private boolean isLaunching = false;
	Event launchedEvent;
	GridPane week;
	private int minTime = 8 * 60 * 60;
	StackPane slotsFill;
	private Schedule sb;

	public MainScreen(Schedule sb) {
		super(new BorderPane(), 800, 600);
		slotsFill = new StackPane();
		classes = new HashMap<String, ArrayList<StackPane>> ();
		weekdayList = new HashMap<String, Integer>();
		initWeekday(weekdayList);
		pane = (BorderPane) getRoot();

		HBox hb = new HBox();
		hb.getChildren().add(initClock());
		pane.setBottom(hb);
		hb.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

		pane.setLeft(navBar());
		
		//Icon made by https://www.freepik.com"
		//https://www.flaticon.com/free-icon/shuttle_2285537?term=rocket&page=1&position=9&page=1&position=9&related_id=2285537&origin=tag

		Image imgRocket = new Image(getClass().getResourceAsStream("rocket.png"));
		ImageView ivRocket = new ImageView(imgRocket);
		ivRocket.setFitHeight(70);
		ivRocket.setFitWidth(70);
		ivRocket.setLayoutX(40);
		ivRocket.setLayoutY(470);
		pane.getChildren().add(ivRocket);	
		week = weekday();
		pane.getChildren().add(week);

		this.sb = sb;
	}
	
	private void addToClasses(Event e,StackPane sp) {
		if(classes.containsKey(e.getName()) == false) {
			classes.put(e.getName(),new ArrayList<StackPane>());
		}
		classes.get(e.getName()).add(sp);
	}

	public void update() {
		pane.getChildren().remove(slotsFill);
		for (Event e : Main.sb.getEvents()) {
			System.out.println(e.getName());
			setSlots(e);
		}
		pane.getChildren().add(slotsFill);

	}

	private void setSlots(Event e) {
		StackPane p = new StackPane();
		int max = 200;
		int min = 150;
		Random rn = new Random();
		int r = rn.nextInt((max - min) + 1) + min;
		rn = new Random();
		int g = rn.nextInt((max - min) + 1) + min;
		rn = new Random();
		int b = rn.nextInt((max - min) + 1) + min;
		for (String day : e.getDay()) {
			day = day.toUpperCase();
			Date d = e.getTime();
			int st = d.getHours() * 60 * 60 + d.getMinutes() * 60;
			StackPane s = setSlot(pane, week, e.getName(), day, st, (long) e.getDuration(), e, Color.rgb(r,g,b) );
			addToClasses(e,s);
		}

	}

	private StackPane setSlot(Pane parent, GridPane gp, String eventName, String day, long start, long duration,
			Event eb, Paint c) {
		start = (int) ((start));

		StackPane vb = new StackPane();
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(240, 0, 72), CornerRadii.EMPTY, Insets.EMPTY)));
		VBox textBox = new VBox();
		textBox.setSpacing(-10);

		Rectangle rec = new Rectangle();
		rec.setFill(c);
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
			for(StackPane sp : classes.get(eb.getName())) {
				pane.getChildren().remove(sp);				
			}
			Main.sb.deleteEvent(eb);
		});
		textBox.getChildren().add(closeBtn);
		vb.getChildren().add(textBox);
		Text text = new Text(eventName);
		text.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		text.setFill(Color.WHITE);
		text.setWrappingWidth(78);
		text.setTextAlignment(TextAlignment.CENTER);
		textBox.getChildren().add(text);
		textBox.setAlignment(Pos.TOP_CENTER);
		vb.setAlignment(textBox, Pos.TOP_CENTER);
		parent.getChildren().add(vb);

		return vb;
		// test
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
				checKEventTimes(date);
				
				
			}
		}, 0, 1000);
		return timeL;
	}
	//test
	private void checKEventTimes(Date d) {
		String days[] = { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY" };
		for( Event e : Main.sb.getEvents()) {
			String wd[] = e.getDay();
			int hour = d.getHours();
			int min = d.getMinutes();
			Date d2 = e.getTime();
			if(!isLaunching && (checkDay(days[d.getDay()], wd) && (d2.getHours() == hour && d2.getMinutes() == min ))) {
				isLaunching = true;
				launchedEvent = e; 
				try {
					e.launchMeeting();
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}else if(e == launchedEvent && (d2.getHours() != hour || d2.getMinutes() != min)) {
				isLaunching = false;
			}
		}
	}
	private boolean checkDay(String day, String[] days) {
		
		for(String str : days) {
			if(str.equalsIgnoreCase(day)) return true;
		}
		return false;
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
		vb.setSpacing(10);
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(1, 175, 209), CornerRadii.EMPTY, Insets.EMPTY)));
		vb.setPrefWidth(150);
		int pad = 20;
		Button toCreateSceneBtn = createButton("ADD EVENT");
		vb.setPadding(new Insets(pad, 0, 0, 0));
		vb.setAlignment(Pos.BASELINE_CENTER);
		toCreateSceneBtn.setOnAction(e -> Main.changeScene(Main.ADD_EVENT));
		toCreateSceneBtn.setOnMouseEntered(e -> {
			toCreateSceneBtn.setBackground(new Background(new BackgroundFill(Color.rgb(249, 133, 133), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		toCreateSceneBtn.setOnMouseExited(e -> {
			toCreateSceneBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
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

		Button icsBtn = createButton("OPEN ICS");
		vb.setPadding(new Insets(pad, 0, 0, 0));
		vb.setAlignment(Pos.BASELINE_CENTER);
		icsBtn.setOnAction(e -> Main.changeScene(Main.ADD_EVENT));
		icsBtn.setOnMouseEntered(e -> {
			icsBtn.setBackground(new Background(new BackgroundFill(Color.rgb(249, 133, 133), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		icsBtn.setOnMouseExited(e -> {
			icsBtn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		icsBtn.setOnMousePressed(e -> {
			icsBtn.setBackground(
					new Background(new BackgroundFill(Color.rgb(133, 91, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		icsBtn.setOnMouseReleased(e -> {
			icsBtn.setBackground(
					new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		});
		
		icsBtn.setOnAction(e -> {
			selectFile();
			ScheduleBuilder.makeICS();
			update();
		});
		
		vb.getChildren().add(icsBtn);

		return vb;
	}
	
	 private File selectFile() {
	        FileChooser fc = new FileChooser();
	        fc.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("ICS FILE", "*.ics")
	        );
	        //HERE IS THING 
	        File f = fc.showOpenDialog(Main.s);
	        
	        
	        if (f != null) {
	            return f;
	        } else {
	            System.out.println("file not selected");
	            return null;
	        }
	    }

	private Button createButton(String label) {
		Button btn = new Button(label);
		btn.setPrefWidth(150);
		btn.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(255, 174, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		return btn;

	}

	private Button createCloseButton() {
		Button btn = new Button("X");
		btn.setTextFill(Color.rgb(230,230,230));
		btn.setFont(Font.font("Sans", FontWeight.BOLD, 10));
		btn.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));
		btn.setOnMouseEntered(e -> {
			btn.setTextFill(Color.rgb(200,200,200));
		});
		btn.setOnMouseExited(e -> {
			btn.setTextFill(Color.rgb(230,230,230));
		});
		btn.setOnMousePressed(e -> {
			btn.setTextFill(Color.rgb(190,190,190));
		});
		btn.setOnMouseReleased(e -> {
			btn.setTextFill(Color.rgb(230,230,230));
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