package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	@FXML
	private BorderPane mainPane;

	
	private void setTab(int tabId) {
		FxmlLoader fxmlLoader = new FxmlLoader(tabId);
		AnchorPane currPane = (AnchorPane)fxmlLoader.getTab();
		
		if(currPane != null) {
			mainPane.setCenter(currPane);
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		setTab(0);
	}
	

    @FXML
    void onBlockListSelection(ActionEvent event) {
    	setTab(1);
    }

    @FXML
    void onDashboardSelection(ActionEvent event) {
    	setTab(0);
    }
    
    @FXML
    void onScheduelSelection(ActionEvent event) {
    	setTab(2);
    }

}
