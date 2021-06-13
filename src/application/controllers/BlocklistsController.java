package application.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import application.entities.BlockList;
import application.entities.Process;
import application.entities.Website;
import application.usecases.BlockListRepository;
import application.usecases.ProcessRepository;
import application.usecases.UseCasePool;
import application.usecases.WebsiteRepository;
import application.views.FxmlViewBuilder;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class BlocklistsController implements Initializable {

	private final UseCasePool useCasePool;
//	private final UserManager userManager;
	private final BlockListRepository blockListRepository;
	private final ProcessRepository processRepository;
	private final WebsiteRepository websiteRepository;
	private FxmlViewBuilder fxmlViewBuilder;
	private File appToBlock;

	@FXML
	private TextField processLbl;

	@FXML
	private TextField blocklistDescription;

	@FXML
	private TextField websiteLbl;

	@FXML
	private TextField blocklistNameLbl;

	@FXML
	private ListView<Process> appListView;

	@FXML
	private ListView<Website> websiteListView;

	@FXML
	private TableView<BlockList> blocklistTableView;

	@FXML
	private TableColumn<BlockList, String> nameColumn;

	@FXML
	private TableColumn<BlockList, String> statusColumn;

	@FXML
	private TableColumn<BlockList, String> descriptionColumn;

	@FXML
	private Button blocklistCreationBtn;

	@FXML
	private CheckBox blocklistChkBox;

	@FXML
	private JFXToggleButton blocklistToggleBtn;

	@FXML
	private TabPane blocklistPane;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initListBindinds();
		initTableViewBinding();
		initBlocklistComponentBindings();

		blocklistNameLbl.selectionProperty().addListener((observable, oldVal, newVal) -> {
			if (blocklistNameLbl.getText().isEmpty()) {
				blocklistPane.setDisable(true);
				blocklistCreationBtn.setDisable(true);

			} else {
				blocklistPane.setDisable(false);
				blocklistCreationBtn.setDisable(false);
			}
		});
	}

	@FXML
	void onHandleCreateBlocklist(ActionEvent event) {
		if (websiteListView.getItems().isEmpty() && appListView.getItems().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("Invalid block list input");
			alert.setHeaderText("No website and applications were selected to be blocked");
			alert.setContentText("Select at least a single website or application to be blocked and try again");
			alert.showAndWait();
		} else {
			int blockListId = blockListRepository.createBlockList(blocklistNameLbl.getText(),
					blocklistDescription.getText(), blocklistChkBox.isSelected(),
					new ArrayList<>(websiteListView.getItems()), new ArrayList<>(appListView.getItems()));
			blocklistTableView.getItems().add(this.blockListRepository.getBlockListById(blockListId));
			blocklistToggleBtn.setDisable(false);
			System.out.println("done");
			resetBlocklistCreationFields();

		}

	}

	@FXML
	void onHandleBlocklistDeletion(ActionEvent event) {
		BlockList selectedBlocklist = blocklistTableView.getSelectionModel().getSelectedItem();
		if (selectedBlocklist != null) {
			Alert alert = new Alert(AlertType.INFORMATION, "This action cannot be undone", ButtonType.YES,
					ButtonType.NO);
			alert.setTitle("Blocklist deletion confirmation");
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setHeaderText("Are you sure?");
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				blocklistTableView.getItems().remove(selectedBlocklist);
			}

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("iFocused - Blocklist Table View Error");
			alert.setHeaderText("An invalid blocklist was selected");
			alert.setContentText("Select a valid blocklist and try again");
			alert.showAndWait();
		}
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

			processRepository.createProcess(appToBlock);
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

	@FXML
	void onHandleAddWebsite(ActionEvent event) {
		String tmpWebsiteURL = websiteLbl.getText();
		if (!tmpWebsiteURL.isEmpty()) {
			if (!tmpWebsiteURL.startsWith("www.") && !tmpWebsiteURL.startsWith("https://")
					&& !tmpWebsiteURL.startsWith("http://")) {
				tmpWebsiteURL = "https://www." + tmpWebsiteURL;
			}

			if (tmpWebsiteURL.startsWith("www.")) {
				tmpWebsiteURL = "https://" + tmpWebsiteURL;
			}

			int newWebsiteID = this.websiteRepository.createWebsite(tmpWebsiteURL.replaceAll("https?://|/$", ""),
					tmpWebsiteURL);
			websiteListView.getItems().add(this.websiteRepository.getNWebsiteById(newWebsiteID));
			websiteLbl.setText("");
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("Invalid Website URL");
			alert.setHeaderText("An invalid website URL was entered");
			alert.setContentText("Enter a valid website URL and try again");
			alert.showAndWait();
		}

	}

	public BlocklistsController(UseCasePool useCasePool, FxmlViewBuilder fxmlViewBuilder) {
		this.useCasePool = useCasePool;
		this.processRepository = useCasePool.getProcessRepository();
		this.websiteRepository = useCasePool.getWebsiteRepository();
		this.blockListRepository = useCasePool.getBlockListRepository();
		this.fxmlViewBuilder = fxmlViewBuilder;
	}

	private void initBlocklistComponentBindings() {
		blocklistToggleBtn.selectedProperty().addListener((observable, oldValue, newValue) -> {
			BlockList selectedBlocklist = blocklistTableView.getSelectionModel().getSelectedItem();
			if (selectedBlocklist != null) {
				selectedBlocklist.setIsEnabled(newValue);
				blocklistTableView.refresh();
			}
		});
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
		websiteListView.setCellFactory(new Callback<ListView<Website>, ListCell<Website>>() {
			@Override
			public ListCell<Website> call(ListView<Website> lv) {
				return new ListCell<Website>() {
					@Override
					public void updateItem(Website item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null) {
							setText(null);
						} else {
							// assume MyDataType.getSomeProperty() returns a string
							setText(item.getWebsiteName());
						}
					}
				};
			}
		});

		/* Binding for exception list view */

	}

	private void initTableViewBinding() {
		// Initialize the block list table with the three columns
		initTableViewColumns();

		// Listen for selection changes and show the block list details when changed
		blocklistTableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBlocklistDetails(newValue));
	}

	/**
	 * Initializing the actions table
	 *
	 */
	public void initTableViewColumns() {
		// Setting up the block list name column
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

		nameColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<BlockList, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BlockList, String> cell) {
						// TODO Auto-generated method stub
						return new ReadOnlyStringWrapper(cell.getValue().getBlocklistName());
					}

				});

		// Setting up the block list status column
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));

		statusColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<BlockList, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BlockList, String> cell) {
						if (cell.getValue().getIsEnabled()) {
							return new ReadOnlyStringWrapper("YES");
						}
						return new ReadOnlyStringWrapper("NO");
					}

				});

		// Setting up the block list description column
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));

		descriptionColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<BlockList, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<BlockList, String> cell) {
						String descriptionText = cell.getValue().getBlocklistDescription();
						if (descriptionText.isEmpty()) {
							return new ReadOnlyStringWrapper("N/A");
						}
						return new ReadOnlyStringWrapper(cell.getValue().getBlocklistDescription());
					}

				});
	}

	private void showBlocklistDetails(BlockList blocklist) {
		if (blocklist != null) {
			blocklistToggleBtn.setSelected(blocklist.getIsEnabled());
			System.out.println("new blocklist was selected");
		}

	}

	private void resetBlocklistCreationFields() {
		blocklistNameLbl.setText("");
		blocklistDescription.setText("");
		blocklistChkBox.setSelected(false);
		websiteListView.getItems().clear();
		appListView.getItems().clear();
		blocklistPane.setDisable(true);
		blocklistCreationBtn.setDisable(true);
	}
}
