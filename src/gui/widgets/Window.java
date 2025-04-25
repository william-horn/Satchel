package gui.widgets;

import javax.swing.JFrame;

import gui.interfaces.WindowInitializer;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Window extends JFrame {

	public Window(String windowName, int x, int y) {
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(x, y);
		this.setLocationRelativeTo(null);

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				System.out.println("Resized");
			}
		});

		this.setVisible(true);
	}

	public Window(String windowName) {
		this(windowName, 400, 400);
	}

	public Window(int x, int y) {
		this("Untitled", x, y);
	}

	public Window(WindowInitializer init) {
		this();
		init.init(this);
	}

	public Window() {
		this("Untitled");
	}
}
