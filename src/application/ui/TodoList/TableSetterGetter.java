package application.ui.TodoList;

import javafx.scene.control.CheckBox;

public class TableSetterGetter {
	private int id;
	private String name;
	private CheckBox checkBox;
	
	public TableSetterGetter(int id, String name, CheckBox checkBox) {
		this.id = id;
		this.name = name;
		this.checkBox = checkBox;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}

}
