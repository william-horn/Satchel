package gui.apps;

import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

public class TodoList {
	public TodoList() {
		Window window = new Window("TODO List", 500, 700);

		WindowContainer windowContainer = new WindowContainer(window);

		Container container = new Container(windowContainer);
	}
}
