import satchel.gui.apps.TodoList;
import satchel.gui.widgets.Container;
import satchel.gui.widgets.Window;
import satchel.gui.widgets.WindowContainer;
import satchel.math.vectors.Vector2;
import satchel.shared.util.Config;
import satchel.shared.util.Console;
import satchel.shared.util.Conversion;
import satchel.shared.util.Parser;
import satchel.shared.util.ValueMeter;

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

		Console.promptBoolean("Enter: ", false);
	}
}
