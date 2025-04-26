/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.gui.widgets;

import javax.swing.JComponent;
import javax.swing.JPanel;

import satchel.gui.interfaces.ContainerInitializer;
import satchel.gui.interfaces.WidgetListener;
import satchel.math.vectors.Vector2;
import satchel.shared.interfaces.VoidGenericCallback;
import satchel.shared.util.EventSignal;

import java.awt.Color;

@SuppressWarnings("unused")
public class Container extends JPanel implements WidgetListener {
	final private EventSignal onResizeEvent = new EventSignal();

	private JComponent parent;

	final private Vector2 sizeScale = new Vector2(1, 1);
	final private Vector2 sizeOffset = new Vector2(0, 0);

	public Container() {
		this.setLayout(null);
		this.setBackground(Color.RED);
	}

	public Container(JComponent parent) {
		this();
		this.setParent(parent);
	}

	public Container(ContainerInitializer init) {
		this();
		init.init(this);
	}

	private void updateSize() {
		int parentWidth = this.parent.getWidth();
		int parentHeight = this.parent.getHeight();
		this.setSize(
				(int) (parentWidth * sizeScale.getX() + sizeOffset.getX()),
				(int) (parentHeight * sizeScale.getY() + sizeOffset.getY()));
	}

	// setters
	public void setSizeScale(double x, double y) {
		this.sizeScale.setX(x);
		this.sizeScale.setY(y);
	}

	public void setSizeOffset(int x, int y) {
		this.sizeOffset.setX(x);
		this.sizeOffset.setY(y);
	}

	public void setParent(JComponent parent) {
		this.parent = parent;
		parent.add(this);
	}

	// getters
	@Override
	public JComponent getParent() {
		return this.parent;
	}

	@Override
	public void onResize(VoidGenericCallback f) {
		onResizeEvent.connect(f);
	}
}
