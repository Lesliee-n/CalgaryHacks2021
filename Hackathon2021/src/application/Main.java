package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static final String MAIN = "MAIN";
	public static final String ADD_EVENT = "ADD_EVENT";
	private static Scene scene;
	private static ScheduleBuilder sb;
	@Override
	public void start(Stage primaryStage) {
		try {
			scene = new MainScreen(sb);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changeScene(String ID) {
		switch(ID) {
		case MAIN:
				scene = new MainScreen(sb);
				break;
		case ADD_EVENT:
				break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
