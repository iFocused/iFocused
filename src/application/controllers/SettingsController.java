package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.fxml.Initializable;

public class SettingsController implements Initializable {
	private UseCasePool useCasePool;
	private FxmlViewBuilder fxmlViewBuilder;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}
	
	public SettingsController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

}
