package gui.widgets;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowContainer extends JPanel {
	public WindowContainer(JFrame window) {
		this.setSize(window.getWidth(), window.getHeight());
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		window.add(this);
	}
}
