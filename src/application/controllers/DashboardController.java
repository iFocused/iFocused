package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.entities.ViewMode;
import application.usecases.UseCasePool;
import application.usecases.UserManager;
import application.views.FxmlViewBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

	private final UseCasePool useCasePool;
	private final UserManager userManager;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private Text usernameLbl;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (!useCasePool.populateAll()) {
			handleFirstLogin();
		} else {
			usernameLbl.setText(userManager.getUser().getUsername());
		}

		viewListeners();

	}

	public DashboardController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.userManager = useCasePool.getUserManager();
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

	private void viewListeners() {

		userManager.getIsDataChangedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				usernameLbl.setText(this.userManager.getUser().getUsername());
				userManager.SetIsUserManagerChangedProperty(false);
			}
		});
	}

	private void handleFirstLogin() {
		try {
			Stage nameRegStage = new Stage();
			Parent root = fxmlViewBuilder.getView(ViewMode.REGISTRATION);

			Scene scene = new Scene(root);
			nameRegStage.setTitle("iFocused - Registration");
			nameRegStage.setScene(scene);
			nameRegStage.setResizable(false);
			nameRegStage.initModality(Modality.APPLICATION_MODAL);
			nameRegStage.getIcons().add(new Image("/application/resources/pics/app-logo.png"));
			nameRegStage.initModality(Modality.WINDOW_MODAL);
			nameRegStage.showAndWait();

			// check if the user has registered successfully
			if (this.useCasePool.getUserManager().getUser() == null) {
				System.out.println("user object DNE!");
			} else {
				usernameLbl.setText(this.useCasePool.getUserManager().getUser().getUsername());
				//
				this.userManager.saveUserData(this.useCasePool.getUserManager().getUser());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
