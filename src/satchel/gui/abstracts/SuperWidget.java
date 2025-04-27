package satchel.gui.abstracts;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;

import satchel.gui.widgets.Widget;
import satchel.math.vectors.Vector2;
import satchel.math.vectors.UDim2;

@SuppressWarnings("unused")
public abstract class SuperWidget<T extends Component> {
	final private T ref;

	private UDim2 maxSize = null;
	private UDim2 minSize = null;
	final private UDim2 size = new UDim2(1, 0, 1, 0);
	final private Vector2 absoluteSize = new Vector2(0, 0);

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

	public Vector2 getAbsoluteSize() {
		return this.absoluteSize;
	}

	public ArrayList<Widget<?>> getChildren() {
		return this.children;
	}

	public T getRef() {
		return this.ref;
	}

	public void setSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.size.setX(scaleX);
		this.size.setY(scaleY);
		this.size.setX(offsetX);
		this.size.setY(offsetY);
	}

	public void setSizeMode(SizeMode sizeMode) {
		this.sizeMode = sizeMode;
	}

	public void add(Widget<?> widget) {
		this.children.add(widget);
	}
}
