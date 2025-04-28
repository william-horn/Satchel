/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JFrame;

import satchel.gui.abstracts.SuperWidget;
import satchel.gui.interfaces.WidgetListener;
import satchel.gui.interfaces.WindowInitializer;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.EventSignal;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("unused")
public class Window extends SuperWidget<JFrame> {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Window() {
		super(new JFrame());
		JFrame ref = this.getRef();
		ref.setTitle("Unnamed");
		ref.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ref.setLayout(null);
		ref.setLocationRelativeTo(null);
		ref.setVisible(true);

		this.computeSize();
		ref.setSize(
				(int) this.getComputedSize().getX(),
				(int) this.getComputedSize().getY());
	}

	@Override
	public void add(Widget<?> widget) {
		super.add(widget);
		this.getRef().add(widget.getRef());
	}

	@Override
	public Unit2 computeNoLayoutSize() {
		UDim2 preferredSize = this.getPreferredSize();
		return new Unit2(
				(int) (screenSize.getWidth() * preferredSize.getScaleX() + preferredSize.getOffsetX()),
				(int) (screenSize.getHeight() * preferredSize.getScaleY() + preferredSize.getOffsetY()));
	}
}
