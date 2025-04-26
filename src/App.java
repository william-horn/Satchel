import gui.apps.TodoList;
import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;
import math.vectors.Vector2;
import shared.util.Config;
import shared.util.Console;
import shared.util.Conversion;
import shared.util.Parser;
import shared.util.ValueMeter;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		Config.load();
		// Console.promptString("Enter name: ", "will");
		// Console.promptBoolean("Enter a boolean: ", false);

		Console.setMenuChoices(
				"One",
				"Two",
				"Three");
	}
}
