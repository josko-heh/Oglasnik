package hr.java.vjezbe.glavna;


import hr.java.vjezbe.niti.DatumObjaveNiti;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	private static Stage mainStage;
//------------------pocetak
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Timeline prikazSlavljenika = new Timeline(new KeyFrame(Duration.seconds(18), new EventHandler<ActionEvent>() {
																							@Override
																							public void handle(ActionEvent event) {
																							 Platform.runLater(new DatumObjaveNiti());
																							 }
																							}
									));
				prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
				prikazSlavljenika.play();
	}
//-----------------------	
	
	
	public static Stage getMainStage() {
		return mainStage;
	}
	
	
	public static void setMainPage(BorderPane pane) {
		Scene scene = new Scene(pane, 600, 500);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		mainStage.setScene(scene);
		mainStage.show();
		
	}

}
