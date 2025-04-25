import gui.apps.TodoList;
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

import shared.util.Config;
import shared.util.Parser;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		Config.load();
		// Config.set("console.debugModeEnabled", "true");
		// Config.set("console.consoleColorsEnabled", "true");
		// Config.save();
		// System.out.println(Config.get("console.debugModeEnabled") == true);
	}
}
