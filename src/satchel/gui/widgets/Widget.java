package satchel.gui.widgets;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import satchel.math.vectors.Vector2;
import satchel.shared.util.EventSignal;

@SuppressWarnings("unused")
public class Widget<T> {
	private T ref;
	private Widget<?> parent;

	final private Vector2 sizeScale = new Vector2(1, 1);
	final private Vector2 sizeOffset = new Vector2(0, 0);
	final private Vector2 absoluteSize = new Vector2(0, 0);
	private SizeMode sizeMode = SizeMode.TRANSFORM;

	final public ArrayList<Widget<?>> children = new ArrayList<>();

	public enum SizeMode {
		FIT_CONTENT,
		TRANSFORM
	}

	public Widget(T ref) {
		JComponent ref_Component = (JComponent) ref;
		ref_Component.setBackground(Color.RED);
		ref_Component.setLayout(null);
		this.ref = ref;
	}

	public Widget(T ref, Widget<T> parent) {
		this(ref);
		this.setParent(parent);
		this.computeAbsoluteSize();
	}

	// getters
	public Widget<T> getParent() {
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

	public ArrayList<Widget<T>> getChildren() {
		return this.children;
	}

	// setters
	public void setParent(Widget<T> parent) {
		this.parent = parent;
		parent.add(this);
	}

	public void add(Widget<T> widget) {
		this.children.add(widget);
		this.ref.add(widget.getRef());
	}

	public void setSizeScale(double x, double y) {
		this.sizeScale.setX(x);
		this.sizeScale.setY(y);
		this.computeAbsoluteSize();
	}

	public void setSizeOffset(int x, int y) {
		this.sizeOffset.setX(x);
		this.sizeOffset.setY(y);
		this.computeAbsoluteSize();
	}

	public void setSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.setSizeScale(offsetX, offsetY);
		this.setSizeOffset(offsetX, offsetY);
	}

	public void setSizeMode(SizeMode sizeMode) {
		this.sizeMode = sizeMode;
		this.computeAbsoluteSize();
	}

	private void computeAbsoluteSize() {
		switch (this.sizeMode) {
			case TRANSFORM -> {
				int parentWidth = this.parent.getAbsoluteSize().getInt(0);
				int parentHeight = this.parent.getAbsoluteSize().getInt(1);
				int x = (int) (parentWidth * sizeScale.getX() + sizeOffset.getX());
				int y = (int) (parentHeight * sizeScale.getY() + sizeOffset.getY());
				this.absoluteSize.setX(x);
				this.absoluteSize.setY(y);
			}
			case FIT_CONTENT -> {
				int x = 0;
				int y = 0;
				for (Widget<T> widget : this.children) {
					x += widget.getAbsoluteSize().getX();
					y += widget.getAbsoluteSize().getY();
				}
				this.absoluteSize.setX(x);
				this.absoluteSize.setY(y);
			}
		}
		ref.setSize(
				this.absoluteSize.getInt(0),
				this.absoluteSize.getInt(1));
	}
}
