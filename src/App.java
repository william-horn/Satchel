import javax.swing.JPanel;

import satchel.gui.apps.TodoList;
import satchel.gui.widgets.Container;
import satchel.gui.widgets.Widget;
import satchel.gui.widgets.Window;
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
		content.setSize(0.5, 0, 0.5, 0);
		// Console.println(mainFrame.getAbsoluteSize());

	}
}
