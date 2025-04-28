package satchel.gui.abstracts;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;

import satchel.gui.widgets.Widget;
import satchel.math.vectors.Vector2;
import satchel.shared.util.Console;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;

@SuppressWarnings("unused")
public abstract class SuperWidget<T extends Component> {
	final private T ref;

	private UDim2 maxSize = null;
	private UDim2 minSize = null;
	final private UDim2 preferredSize = new UDim2(1, 0, 1, 0);
	final private Unit2 computedSize = new Unit2(0, 0);

	private SizeMode sizeMode = SizeMode.TRANSFORM;
	private SatchelLayout satchelLayout = SatchelLayout.NONE;

	final private ArrayList<Widget<?>> children = new ArrayList<>();

	public SuperWidget(T ref) {
		this.ref = ref;
	}

	public enum SizeMode {
		FIT_CONTENT,
		TRANSFORM
	}

	public enum SatchelLayout {
		NONE,
	}

	// getters
	public Unit2 getComputedSize() {
		return this.computedSize;
	}

	public UDim2 getPreferredSize() {
		return this.preferredSize;
	}

	public UDim2 getMaxSize() {
		return this.maxSize;
	}

	public UDim2 getMinSize() {
		return this.minSize;
	}

	public ArrayList<Widget<?>> getChildren() {
		return this.children;
	}

	public T getRef() {
		return this.ref;
	}

	public SizeMode getSizeMode() {
		return this.sizeMode;
	}

	public SatchelLayout getSatchelLayout() {
		return this.satchelLayout;
	}

	// setters
	public void setSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.preferredSize.setScaleX(scaleX);
		this.preferredSize.setOffsetX(offsetX);
		this.preferredSize.setScaleY(scaleY);
		this.preferredSize.setOffsetY(offsetY);
		this.computeSize();
	}

	public void setSizeMode(SizeMode sizeMode) {
		if (this.sizeMode == sizeMode)
			return;
		this.sizeMode = sizeMode;
		this.computeSize();
	}

	public void setMaxSize(UDim2 maxSize) {
		this.maxSize = maxSize;
		this.computeSize();
	}

	public void setMinSize(UDim2 minSize) {
		this.minSize = minSize;
		this.computeSize();
	}

	public void setSatchelLayout(SatchelLayout satchelLayout) {
		this.satchelLayout = satchelLayout;
	}

	public void add(Widget<?> widget) {
		this.children.add(widget);
	}

	public void computeSize() {
		// compute virtual size
		Unit2 computedSize;
		switch (this.satchelLayout) {
			case NONE -> computedSize = computeNoLayoutSize();
			default -> throw new Error("No layout");
		}
		this.computedSize.setX(computedSize.getX());
		this.computedSize.setY(computedSize.getY());

		// compute ref component size
		this.ref.setSize(
				this.computedSize.getX(),
				this.computedSize.getY());
	}

	public abstract Unit2 computeNoLayoutSize();
}
