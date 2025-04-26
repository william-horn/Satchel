package satchel.gui.widgets;

import java.awt.Color;

import javax.swing.JFrame;

public class WindowContainer extends Container {
	public WindowContainer() {
		super();
		this.setBackground(Color.BLACK);
	}

	public WindowContainer(JFrame window) {
		this();
		this.setSize(window.getWidth(), window.getHeight());
		window.add(this);
	}
}
