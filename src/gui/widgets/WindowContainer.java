package gui.widgets;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.interfaces.ComponentInitializer;

public class WindowContainer extends JPanel {
	public WindowContainer() {
		this.setBackground(Color.BLACK);
		this.setLayout(null);
	}

	public WindowContainer(JFrame window) {
		this();
		this.setSize(window.getWidth(), window.getHeight());
		window.add(this);
	}

	public WindowContainer(ComponentInitializer init) {
		this();
		init.init(this);
	}
}
