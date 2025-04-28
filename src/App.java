import javax.swing.JPanel;

import satchel.gui.Window;
import satchel.gui.apps.TodoList;
import satchel.gui.widgets.Container;
import satchel.gui.widgets.Widget;
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
		// new TodoList();
		// Console.println(Math.atan2(-0.5, 1));
		Window win = new Window();
		// WindowContainer mainFrame = new WindowContainer(win);
		Container content = new Container(win);
		content.setTransformSize(0.8, 500, 0.5, 0);
		content.setMaxSize(0.5, 0, 1, 0);
		// Console.println(mainFrame.getAbsoluteSize());

	}
}
