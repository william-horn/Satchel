package gui;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Container extends JPanel {
	public Container() {
		this.setSize(this.getParent().getWidth(), this.getParent().getHeight());
		this.setLayout(null);
	}
}
