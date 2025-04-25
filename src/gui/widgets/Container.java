package gui.widgets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

public class Container extends JPanel {
	public Container(JComponent parent) {
		this.setLayout(null);
		this.setBackground(Color.RED);
		if (parent != null) {
			this.setSize(parent.getWidth(), parent.getHeight());
			parent.add(this);
		}
	}
}
