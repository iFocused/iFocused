package application.views;

import java.io.IOException;

import application.controllers.ControllerPool;
import application.entities.ViewMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FxmlViewBuilder {
	private ControllerPool controllerPool;
	private Stage mainStage;

	public FxmlViewBuilder(Stage mainStage) {
		this.controllerPool = new ControllerPool(this);
		this.mainStage = mainStage;
	}

	
	/**
	 * Returns the pane corresponding to <tabId>. If an invalid id was given,
	 * returning null.
	 * 
	 * @return pane corresponding to <tabId>. If an invalid id was given, null is
	 *         returned.
	 * @throws IOException 
	 */
	public Parent getView(ViewMode whichView) throws IOException {
		switch (whichView) {
		case REGISTRATION:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/nameRegistration.fxml"))).load();
			
		case DASHBOARD:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/dashboard.fxml"))).load();

		case BLOCKLISTS:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/blocklists.fxml"))).load();

		case SCHEDULE:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/schedule.fxml"))).load();

		case POMODORO:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/timer.fxml"))).load();

		case STATS:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/stats.fxml"))).load();
			
		case SETTINGS:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/settings.fxml"))).load();

		case MAIN:
			return this.controllerPool.getController(new FXMLLoader(getClass().getResource("../views/main.fxml"))).load();

		default:
			break;

		}

		return null;
	}
	
	public Stage getMainStage() {
		return this.mainStage;
	}
}
