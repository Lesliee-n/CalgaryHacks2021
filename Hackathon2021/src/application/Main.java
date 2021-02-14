package application;
	
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
	private static ScheduleBuilder sb;
	@Override
	public void start(Stage primaryStage) {
		try {
			s = primaryStage;
			scene = new MainScreen(sb);
			ms = new MainScreen(sb);
			cs = new CreateScreen(sb);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//change scene
	public static void changeScene(String ID) {
		switch(ID) {
		case MAIN:
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
