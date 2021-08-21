package application.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.entities.Task;
import application.entities.TaskStatus;
import application.entities.ViewMode;
import application.entities.Website;
import application.usecases.UseCasePool;
import application.usecases.UserManager;
import application.views.FxmlViewBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DashboardController implements Initializable {

	private final UseCasePool useCasePool;
	private final UserManager userManager;
	private FxmlViewBuilder fxmlViewBuilder;

	@FXML
	private Text usernameLbl;

	@FXML
	private ListView<Task> todoListView;

	@FXML
	private BarChart<String, String> barChart;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (!useCasePool.populateAll()) {
			handleFirstLogin();
		} else {
			usernameLbl.setText(userManager.getUser().getUsername());
		}

		viewListeners();
		renderTodoListByName();
		
		// loading dummy data into bar chart
		//barChart.getData().add(new XYChart.Data("", ))

	}

	@FXML
	void onHandleAddTask(ActionEvent event) {
		TextInputDialog td = new TextInputDialog();
		td.getDialogPane().setStyle("-fx-font-family: 'calibri';");
		td.setTitle("TodoList Task Addition");
		td.setHeaderText("Enter task:");
		td.setContentText("Task description:");

		Optional<String> result = td.showAndWait();

		result.ifPresent(name -> {
			Task newTask = new Task(name);
			this.useCasePool.getTodoList().addTask(newTask);
			todoListView.getItems().add(newTask);
		});
	}

	@FXML
	void onHandleCompleteTask(ActionEvent event) {
		Task selTask = todoListView.getSelectionModel().getSelectedItem();
		if (selTask != null) {
			selTask.setTaskStatus(TaskStatus.COMPLETED);

			todoListView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
				@Override
				public ListCell<Task> call(ListView<Task> lv) {
					return new ListCell<Task>() {
						@Override
						public void updateItem(Task task, boolean empty) {
							super.updateItem(task, empty);
							if (task != null) {
								setStyle("-fx-font-weight: bold;");
							}

						}
					};
				}
			});

		}
	}

	@FXML
	void onHandleRemoveTask(ActionEvent event) {
		// get selected task
		Task selTask = todoListView.getSelectionModel().getSelectedItem();
		if (selTask != null) {
			todoListView.getItems().remove(selTask);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.getDialogPane().setStyle("-fx-font-family: 'calibri';");
			alert.initOwner(fxmlViewBuilder.getMainStage());
			alert.setTitle("No selected task was found");
			alert.setHeaderText("No task was selected");
			alert.setContentText("Please make sure to select the correct task you wish to remove");
			alert.showAndWait();
		}

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

	private void renderTodoListByName() {
		todoListView.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {
			@Override
			public ListCell<Task> call(ListView<Task> lv) {
				return new ListCell<Task>() {
					@Override
					public void updateItem(Task item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null) {
							setText(null);
						} else {
							setText(item.getName());
						}
					}
				};
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
