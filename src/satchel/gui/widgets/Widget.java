package satchel.gui.widgets;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import satchel.gui.abstracts.SuperWidget;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;
import satchel.math.vectors.Vector2;
import satchel.shared.util.Console;
import satchel.shared.util.EventSignal;

@SuppressWarnings("unused")
public class Widget<T extends JComponent> extends SuperWidget<T> {
	private SuperWidget<?> parent;

	public Widget(T ref) {
		super(ref);
	}

	// getters
	public SuperWidget<?> getParent() {
		return this.parent;
	}

	public boolean hasParent() {
		return this.parent != null;
	}

	// setters
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
	public Unit2 computeTransformSize() {
		UDim2 transformSize = this.getTransformSize();
		Unit2 parentSize = this.parent.getComputedSize();
		return transformSize.toComputedComponents(parentSize);
	}

	@Override
	public Unit2 computeMaxSize() {
		Unit2 parentSize = this.parent.getComputedSize();
		Console.println("Parent size: ", parentSize);
		return this.getMaxSize().toComputedComponents(parentSize);
	}

	@Override
	public Unit2 computeMinSize() {
		Unit2 parentSize = this.parent.getComputedSize();
		return this.getMinSize().toComputedComponents(parentSize);
	}

	@Override
	public Unit2 computeTransformPosition() {
		Unit2 parentSize = this.parent.getComputedSize();
		return this.getTransformPosition().toComputedComponents(parentSize);
	}

	@Override
	public String toString() {
		return "SuperWidget{Widget{?}}";
	}
}
