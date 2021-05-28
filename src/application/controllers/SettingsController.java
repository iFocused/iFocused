package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.usecases.UseCasePool;
import javafx.fxml.Initializable;

public class SettingsController implements Initializable {
	private UseCasePool useCasePool;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
	}
	
	public SettingsController(UseCasePool useCasePool) {
		this.useCasePool = useCasePool;
	}

}
