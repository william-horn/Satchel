package satchel.gui.widgets;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import satchel.gui.abstracts.SuperWidget;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;
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
	public Unit2 computeNoLayoutSize() {
		UDim2 preferredSize = this.getPreferredSize();
		Unit2 parentSize = this.parent.getComputedSize();
		return new Unit2(
				(int) (parentSize.getX() * preferredSize.getScaleX() + preferredSize.getOffsetX()),
				(int) (parentSize.getY() * preferredSize.getScaleY() + preferredSize.getOffsetY()));
	}
}
