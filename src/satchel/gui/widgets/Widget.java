package satchel.gui.widgets;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import satchel.gui.abstracts.SuperWidget;
import satchel.math.vectors.Vector2;
import satchel.shared.util.EventSignal;

@SuppressWarnings("unused")
public class Widget<T extends JComponent> extends SuperWidget<T> {
	private SuperWidget<?> parent;

	public Widget(T ref) {
		super(ref);
	}

	public void setParent(SuperWidget<?> parent) {
		this.parent = parent;
		parent.add(this);
	}

	@Override
	public void add(Widget<?> widget) {
		super.add(widget);
		this.getRef().add(widget.getRef());
	}

	@Override
	public void setSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		super.setSize(scaleX, offsetX, scaleY, offsetY);
	}
}
