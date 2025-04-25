import gui.widgets.Container;
import gui.widgets.Window;
import gui.widgets.WindowContainer;

public class App {
	public static void main(String[] args) {
		Window test = new Window(window -> {
			window.setTitle("New title");
		});

		WindowContainer main = new WindowContainer(test);

		Container box = new Container(main);

		// System.out.println(frame.getWidth());
	}
}
