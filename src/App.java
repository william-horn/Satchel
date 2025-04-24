import gui.Container;
import gui.Window;

public class App {
	public static void main(String[] args) {
		Window test = new Window("Test");
		Container frame = new Container();

		test.add(frame);
		System.out.println(frame.getWidth());
	}
}
