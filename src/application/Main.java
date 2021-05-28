package application;
	
import application.entities.ViewMode;
import application.views.FxmlViewBuilder;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			FxmlViewBuilder fxmlViewBuilder = new FxmlViewBuilder();
			Parent root = fxmlViewBuilder.getView(ViewMode.MAIN);

	        Scene scene = new Scene(root);
	        stage.setTitle("iFocused");
	        stage.setScene(scene);
	        stage.setResizable(false);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
