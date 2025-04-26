package satchel.gui.apps;

import satchel.gui.widgets.Container;
import satchel.gui.widgets.Window;
import satchel.gui.widgets.WindowContainer;

@SuppressWarnings("unused")
public class TodoList {
	public TodoList() {
		Window window = new Window("TODO List", 500, 700);
		WindowContainer windowContainer = new WindowContainer(window);

		Container container = new Container(self -> {
		});
	}
}
