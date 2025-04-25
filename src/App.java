import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

@SuppressWarnings("unused")
public class App {
	public static void main(String[] args) {
		Window window = new Window(self -> {
			self.setTitle("TODO list");
			self.setSize(500, 700);
			self.setLocation(0, 0);
		});

		WindowContainer windowContainer = new WindowContainer(window);

		Container container = new Container(windowContainer);
	}
}
