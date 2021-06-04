package application.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.entities.Process;
import application.usecases.ProcessRepository;
import application.usecases.UseCasePool;
import application.views.FxmlViewBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class BlocklistsController implements Initializable {

	private final UseCasePool useCasePool;
//	private final UserManager userManager;
	private final ProcessRepository processRepository;
	private FxmlViewBuilder fxmlViewBuilder;
	private File appToBlock;

	@FXML
	private TextField processLbl;

	@FXML
	private TextField appDescriptionTxt;

	@FXML
	private ListView<Process> appListView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initListBindinds();
	}

	@FXML
	void onHandleAppBrowse(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("iFocused - Application Selection");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Exectuable Files", "*.exe"));
		appToBlock = fileChooser.showOpenDialog(fxmlViewBuilder.getMainStage());
		if (appToBlock != null) {
			processLbl.setText(appToBlock.getAbsolutePath());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("iFocused - File Selection Error");
			alert.setHeaderText("The selected file could not be recognized");
			alert.setContentText("If this problem presists, manually paste the directory");
			alert.showAndWait();
			processLbl.setText("");
		}

	}

	@FXML
	void onHandleAddApp(ActionEvent event) {
		if (!processLbl.getText().isEmpty()) {
			if (appToBlock == null) {
				appToBlock = new File(processLbl.getText());
			}

			processRepository.createProcess(appToBlock, appDescriptionTxt.getText());
			appListView.getItems().add(processRepository.getMostRecentProcess());

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("iFocused - File Addition Error");
			alert.setHeaderText("No application to block was selected");
			alert.setContentText("Enter the application directory path manually or select it using the browse button");
			alert.showAndWait();
			processLbl.setText("");
		}
	}

	public BlocklistsController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.processRepository = useCasePool.getProcessRepository();
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

	private void initListBindinds() {
		/* Binding for application list view */
		appListView.setCellFactory(new Callback<ListView<Process>, ListCell<Process>>() {
			@Override
			public ListCell<Process> call(ListView<Process> lv) {
				return new ListCell<Process>() {
					@Override
					public void updateItem(Process item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null) {
							setText(null);
						} else {
							// assume MyDataType.getSomeProperty() returns a string
							setText(item.getProcessName().replace(".exe", ""));
						}
					}
				};
			}
		});
		
		/* Binding for website list view */
		
		/* Binding for exception list view */

	}
}
