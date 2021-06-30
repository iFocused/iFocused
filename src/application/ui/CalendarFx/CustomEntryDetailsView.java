package application.ui.CalendarFx;

import com.calendarfx.model.Entry;
import com.calendarfx.view.popover.EntryDetailsView;

import application.entities.BlockList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class CustomEntryDetailsView extends EntryDetailsView {
	private GridPane gridPane;
	private ComboBox<BlockList> blockListComboBox;

	public CustomEntryDetailsView(Entry<?> entry) {
		super(entry);
		gridPane = (GridPane) this.getChildren().get(0);
		blockListComboBox = new ComboBox<BlockList>();

		gridPane.add(new Label("Block Lists:"), 0, 5);
		gridPane.add(blockListComboBox, 1, 5);
		renderByBlocklistName();
	}

	public GridPane getGridPane() {
		return this.gridPane;
	}

	private void renderByBlocklistName() {
		Callback<ListView<BlockList>, ListCell<BlockList>> factory = lv -> new ListCell<BlockList>() {

		    @Override
		    protected void updateItem(BlockList blockList, boolean empty) {
		        super.updateItem(blockList, empty);
		        setText(empty ? "" : blockList.getBlocklistName());
		    }

		};
		
		this.blockListComboBox.setCellFactory(factory);
		this.blockListComboBox.setButtonCell(factory.call(null));
	}

}
