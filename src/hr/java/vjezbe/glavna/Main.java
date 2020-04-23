package hr.java.vjezbe.glavna;
	

import hr.java.vjezbe.util.GlavnaDatoteke;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage mainStage;
//------------------pocetak
	public static void main(String[] args) {
		GlavnaDatoteke.ucitajDatoteke();
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
