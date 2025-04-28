package satchel.gui.abstracts;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;

import satchel.gui.widgets.Widget;
import satchel.math.vectors.Vector2;
import satchel.shared.util.Console;
import satchel.math.util.MathUtils;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;

@SuppressWarnings("unused")
public abstract class SuperWidget<T extends Component> {
	final private T ref;

	final private UDim2 maxSize = new UDim2(Double.POSITIVE_INFINITY);
	final private UDim2 minSize = new UDim2(Double.NEGATIVE_INFINITY);
	final private Unit2 computedMaxSize = new Unit2(Integer.MAX_VALUE);
	final private Unit2 computedMinSize = new Unit2(Integer.MIN_VALUE);
	final private UDim2 transformSize = new UDim2(1, 0, 1, 0);
	final private Unit2 computedSize = new Unit2(0);

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
	public UDim2 getTransformSize() {
		return this.transformSize;
	}

	public Unit2 getComputedSize() {
		return this.computedSize;
	}

	public UDim2 getMaxSize() {
		return this.maxSize;
	}

	public UDim2 getMinSize() {
		return this.minSize;
	}

	public Unit2 getComputedMaxSize() {
		return this.computedMaxSize;
	}

	public Unit2 getComputedMinSize() {
		return this.computedMinSize;
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
		this.transformSize.setScaleX(scaleX);
		this.transformSize.setOffsetX(offsetX);
		this.transformSize.setScaleY(scaleY);
		this.transformSize.setOffsetY(offsetY);
		this.computeSize();
	}

	public void setSizeMode(SizeMode sizeMode) {
		if (this.sizeMode == sizeMode)
			return;
		this.sizeMode = sizeMode;
		this.computeSize();
	}

	public void setMaxSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.maxSize.setScaleX(scaleX);
		this.maxSize.setOffsetX(offsetX);
		this.maxSize.setScaleY(scaleY);
		this.maxSize.setOffsetY(offsetY);
		this.computeMaxSize();
		this.computeSize();
	}

	public void setMinSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.minSize.setScaleX(scaleX);
		this.minSize.setOffsetX(offsetX);
		this.minSize.setScaleY(scaleY);
		this.minSize.setOffsetY(offsetY);
		this.computeMinSize();
		this.computeSize();
	}

	public void setSatchelLayout(SatchelLayout satchelLayout) {
		this.satchelLayout = satchelLayout;
	}

	public void add(Widget<?> widget) {
		this.children.add(widget);
	}

	private void computeSize() {
		// compute virtual size
		Unit2 computedSize;
		switch (this.sizeMode) {
			case TRANSFORM -> computedSize = computeTransformSize();
			default -> throw new Error("No layout");
		}
		Unit2 maxSize = this.getComputedMaxSize();
		Unit2 minSize = this.getComputedMinSize();
		this.computedSize.setX((int) MathUtils.clamp(
				computedSize.getX(),
				minSize.getX(),
				maxSize.getX()));
		this.computedSize.setY((int) MathUtils.clamp(
				computedSize.getY(),
				minSize.getX(),
				maxSize.getX()));

		// compute ref component size
		this.ref.setSize(
				this.computedSize.getX(),
				this.computedSize.getY());
	}

	public abstract Unit2 computeTransformSize();

	public abstract Unit2 computeMaxSize();

	public abstract Unit2 computeMinSize();
}
