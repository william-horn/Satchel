package satchel.gui.widgets;

import javax.swing.JLabel;
import java.awt.Font;

public class Text extends JLabel {
	public Text(String text) {
		this.setText(text);
		this.setFont(new Font("Arial", Font.BOLD, 16));
	}
}
