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
	private static Scene ms;
	private static Scene cs;
	private static Scene scene;
	private static Stage s;
	private static Schedule sb;
	@Override
	public void start(Stage primaryStage) {
		try {
			
			s = primaryStage;
			sb = new Schedule();
			scene = new MainScreen(sb);
			ms = new MainScreen(sb);
			cs = new CreateScreen(sb);
			
			testEvent();
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testEvent() {
		Event e = new Event();
		 Date dd = new Date(); 
         dd.setHours(8); 
         dd.setMinutes(0); 
		e.setName("MATH 267");
		String days[] = {"MONDAY","WEDNESDAY","FRIDAY"};
		e.setDay(days);
		e.setDuration(60*60);
		sb.addEvent(e);
		((MainScreen) ms).update(dd);
	}
	
	public void buildTimers() {
		for(Event e : sb.getEvents()){
			setTimer(e);
		}
		
	}
	public void setTimer(Event e) {
		Date active = e.getTime();
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
		}, active.getTime(), 7*1000*60*60*24);
	}
	
	
	//change scen
	public static void changeScene(String ID) {
		switch(ID) {
		case MAIN:
				//((MainScreen) ms).update();
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
