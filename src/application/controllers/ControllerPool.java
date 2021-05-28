package application.controllers;

import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.fxml.FXMLLoader;

public class ControllerPool {
	private final MainController mainController;
	private final NameRegistrationController nameRegistrationController;
	private final ScheduleController scheduleController;
	private final SettingsController settingsController;
	private final StatsController statsController;
	private final TimerController timerController;
	private FxmlViewBuilder fxmlViewBuilder;

	public ControllerPool(FxmlViewBuilder fxmlViewBuilder) {
		UseCasePool useCasePool = new UseCasePool();
		mainController = new MainController(useCasePool, fxmlViewBuilder);
		statsController = new StatsController(useCasePool);
		nameRegistrationController = new NameRegistrationController(useCasePool);
		scheduleController = new ScheduleController(useCasePool);
		settingsController = new SettingsController(useCasePool); 
		timerController = new TimerController(useCasePool);
		this.fxmlViewBuilder = fxmlViewBuilder;
	}
	

	/**
	 * Controller factory containing all of the controllers
	 * 
	 * @param loader	The loader containing the view's FXML location and view
	 * 
	 * @return The controller loaded for injection into the view
	 */
	public FXMLLoader getController(FXMLLoader loader) {
		loader.setControllerFactory(controllerType -> {

		    if (controllerType == MainController.class) {
		        return this.mainController;
		    }

		    if (controllerType == StatsController.class) {
		        return this.statsController;
		    }
		    
		    if (controllerType == NameRegistrationController.class) {
		        return this.nameRegistrationController;
		    }
		    
		    if (controllerType == ScheduleController.class) {
		        return this.scheduleController;
		    }
		    
		    if (controllerType == SettingsController.class) {
		        return this.settingsController;
		    }
		    
		    if (controllerType == TimerController.class) {
		        return this.timerController;
		    }

		    return null ; // can also throw an unchecked exception
		});
		
		return loader;
	}

	public MainController getMainController() {
		return mainController;
	}

	public NameRegistrationController getNameRegistrationController() {
		return nameRegistrationController;
	}

	public ScheduleController getScheduleController() {
		return scheduleController;
	}

	public SettingsController getSettingsController() {
		return settingsController;
	}

	public StatsController getStatsController() {
		return statsController;
	}

	public TimerController getTimerController() {
		return timerController;
	}
	
	public FxmlViewBuilder getFxmlViewBuilder() {
		return fxmlViewBuilder;
	}

}
