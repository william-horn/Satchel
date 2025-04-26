package satchel.gui.widgets;

import javax.swing.JComponent;

import satchel.math.vectors.Vector2;
import satchel.shared.util.EventSignal;

public class Widget {
	private JComponent ref;
	private Widget parent;

	final private Vector2 sizeScale = new Vector2(1, 1);
	final private Vector2 sizeOffset = new Vector2(0, 0);
	final private Vector2 absoluteSize = new Vector2(0, 0);

	final public EventSignal onResize = new EventSignal();

	public enum SizeMode {
		FIT_CONTENT,
		TRANSFORM
	}

	public Widget(JComponent ref) {
		this.ref = ref;
	}

	public Widget getParent() {
		return this.parent;
	}

	public JComponent getRef() {
		return this.ref;
	}

	public Vector2 getSizeScale() {
		return this.sizeScale;
	}

	public Vector2 getSizeOffset() {
		return this.sizeOffset;
	}

	public Vector2 getAbsoluteSize() {
		return this.absoluteSize;
	}

	public void setParent(Widget parent) {
		this.parent = parent;
		parent.getRef().add(this.ref);
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

	public void setSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.setSizeScale(offsetX, offsetY);
		this.setSizeOffset(offsetX, offsetY);
	}

	// private void updateSize() {
	// int parentWidth = this.parent.getWidth();
	// int parentHeight = this.parent.getHeight();
	// int x = (int) (parentWidth * sizeScale.getX() + sizeOffset.getX());
	// int y = (int) (parentHeight * sizeScale.getY() + sizeOffset.getY());
	// }
}
