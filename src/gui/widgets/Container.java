package gui.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import gui.interfaces.ComponentInitializer;

import java.awt.Color;

@SuppressWarnings("unused")
public class Container extends JPanel {

	public Container() {
		this.setLayout(null);
		this.setBackground(Color.RED);
	}

	public Container(JComponent parent) {
		this();
		this.setSize(parent.getWidth(), parent.getHeight());
		parent.add(this);
	}

	public Container(ComponentInitializer init) {
		this();
		init.init(this);
	}
}
