import gui.apps.TodoList;
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

import shared.util.Config;
import shared.util.Console;
import shared.util.Conversion;
import shared.util.Parser;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		Config.load();
		// Config.set("console.debugModeEnabled", "true");
		// Config.set("console.consoleColorsEnabled", "true");
		// Config.save();
		// System.out.println(Config.get("console.asd"));
		// StringBuilder t = new StringBuilder();
		// t.append("\u001B[38;2;255;165;0m");
		// t.append("test");
		// System.out.println(t.toString());
		// Config.set("console.consoleColorsEnabled", "false");
		Console.println("<full #3adb1a>hellow world<full reset>something");
	}
}
