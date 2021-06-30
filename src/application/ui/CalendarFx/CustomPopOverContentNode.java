package application.ui.CalendarFx;

import java.util.*;

import org.controlsfx.control.PopOver;

import com.calendarfx.model.Entry;
import com.calendarfx.view.DateControl;
import com.calendarfx.view.popover.EntryPopOverContentPane;

import application.entities.BlockList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

public class CustomPopOverContentNode extends EntryPopOverContentPane {
	private EntryWithBlockList<?> entryWithBlockList;

	/**
	 * {@inheritDoc}
	 */
	public CustomPopOverContentNode(PopOver popOver, DateControl dateControl, Entry<?> entry,
			Collection<BlockList> blocklists) {
		super(popOver, dateControl, entry);
		this.entryWithBlockList = (EntryWithBlockList<?>) entry;

		Accordion accordion = (Accordion) this.getCenter();
		accordion.getPanes().get(0).setContent(new CustomEntryDetailsView(entry));
		GridPane gridPane = ((CustomEntryDetailsView) accordion.getPanes().get(0).getContent()).getGridPane();

		@SuppressWarnings("unchecked")
		ComboBox<BlockList> blockListComboBox = (ComboBox<BlockList>) gridPane.getChildren()
				.get(gridPane.getChildren().size() - 1);

		blockListComboBox.getItems().setAll(blocklists);
		if (entryWithBlockList.getBlockList() != null && blocklists.contains(entryWithBlockList.getBlockList()))
			blockListComboBox.getSelectionModel().select(entryWithBlockList.getBlockList());

		addBlockListListener(blockListComboBox);
	}

	/**
	 * Enables block list combo box listener
	 * 
	 * @param blockListComboBox The block list combo box that will be observed
	 */
	private void addBlockListListener(ComboBox<BlockList> blockListComboBox) {
		blockListComboBox.valueProperty().addListener((observable, oldBlockList, newBlockList) -> {
			this.entryWithBlockList.setBlockList(newBlockList);
		});
	}
}
