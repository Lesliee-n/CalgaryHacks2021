package application;
	
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//tset

public class Main extends Application {
	public static final String MAIN = "MAIN";
	public static final String ADD_EVENT = "ADD_EVENT";
	private static MainScreen ms;
	private static CreateScreen cs;
	private static Scene scene;
	public static Stage s;
	public static Schedule sb;
	@Override
	public void start(Stage primaryStage) {
		try {
			//asdasd
			s = primaryStage;
			sb = new Schedule();
			//testEvent();
			cs = new CreateScreen(sb);		
			ms = new MainScreen(sb);
			scene = ms;
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testEvent() {
		String days[] = {"MONDAY","WEDNESDAY","FRIDAY"};
		Event e = new Event("abc",days,8,0,60*60,"asad");
		e.setName("MATH 267");
		e.setDay(days);
		e.setDuration(60*60);
		sb.addEvent(e);

	}
	
	public static void buildTimers() {
		for(Event e : sb.getEvents()){
			setTimer(e);
		}
		
	}
	public static void setTimer(Event e) {
		Timer timer = new Timer(true); 
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				try {
					e.launchMeeting();
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, e.getTime(), 7*1000*60*60*24);
	}
	
	
	//change scen
	public static void changeScene(String ID) {
		switch(ID) {
		case MAIN:
				ms.update();
				scene = ms;
				break;
		case ADD_EVENT:
				scene = cs;
				break;
		}
		s.setScene(scene);
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
