import gui.apps.TodoList;
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

import shared.util.Config;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		// TodoList todo = new TodoList();

		Config.load();
		Config.set("a", "900");
		// Config.set("b", "23");
		// Config.set("c", "676");
		Config.save();
		// System.out.println(Config.get("a"));
	}
}
