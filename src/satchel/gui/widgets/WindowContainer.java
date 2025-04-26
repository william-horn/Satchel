package satchel.gui.widgets;

import java.awt.Color;
import java.awt.Component;

public class WindowContainer extends Container {
	private Window parent;

	public WindowContainer() {
		super();
		this.setBackground(Color.BLACK);
	}

	public WindowContainer(Window window) {
		this();
	}

	@Override
	public Component add(Component comp) {
		return null;
	}
}
