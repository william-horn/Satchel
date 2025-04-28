package satchel.gui.abstracts;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JComponent;

import satchel.gui.widgets.Widget;
import satchel.math.vectors.Vector2;
import satchel.shared.util.Console;
import satchel.shared.util.EventSignal;
import satchel.math.util.MathUtils;
import satchel.math.vectors.UDim2;
import satchel.math.vectors.Unit2;

@SuppressWarnings("unused")
public abstract class SuperWidget<T extends Component> {
	final private T ref;

	final private UDim2 maxSize = new UDim2(Double.POSITIVE_INFINITY);
	final private UDim2 minSize = new UDim2(Double.NEGATIVE_INFINITY);
	final private UDim2 transformSize = new UDim2(0);
	final private Unit2 computedMaxSize = new Unit2(Integer.MAX_VALUE);
	final private Unit2 computedMinSize = new Unit2(Integer.MIN_VALUE);
	final private Unit2 computedSize = new Unit2(0);

	final private UDim2 transformPosition = new UDim2(0);
	final private Unit2 computedPosition = new Unit2(0);

	final private ArrayList<Widget<?>> children = new ArrayList<>();

	final public EventSignal onResize = new EventSignal();

	private SizeMode sizeMode = SizeMode.TRANSFORM;
	private SatchelLayout satchelLayout = SatchelLayout.NONE;

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
	public UDim2 getTransformPosition() {
		return this.transformPosition;
	}

	public Unit2 getComputedPosition() {
		return this.computedPosition;
	}

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

	/**
	 * @return the array list of child {@code Widget} objects within this
	 *         {@code SuperWidget}.
	 */
	public ArrayList<Widget<?>> getChildren() {
		return this.children;
	}

	/**
	 * @return the inner java swing component of this {@code SuperWidget}
	 */
	public T getRef() {
		return this.ref;
	}

	/**
	 * @return the size mode that this {@code SuperWidget} is currently set to
	 */
	public SizeMode getSizeMode() {
		return this.sizeMode;
	}

	/**
	 * @return the layout preset this {@code SuperWidget} is currently using
	 */
	public SatchelLayout getSatchelLayout() {
		return this.satchelLayout;
	}

	// setters
	public void setVirtualTransformPosition(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.transformPosition.setScaleX(scaleX);
		this.transformPosition.setOffsetX(offsetX);
		this.transformPosition.setScaleY(scaleY);
		this.transformPosition.setOffsetY(offsetY);
	}

	/**
	 * Set the transform size of this {@code SuperWidget} using {@code UDim2}
	 * components (scaleX, offsetX, scaleY, offsetX).
	 * 
	 * @param scaleX  an alpha value from {@code 0} to {@code 1} determining how
	 *                much horizontal space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMinSize}
	 * @param offsetX horizontal offset in pixels
	 * @param scaleY  an alpha value from {@code 0} to {@code 1} determining how
	 *                much vertical space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMinSize}
	 * @param offsetY vertical offset in pixels
	 */
	public void setVirtualTransformSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.transformSize.setScaleX(scaleX);
		this.transformSize.setOffsetX(offsetX);
		this.transformSize.setScaleY(scaleY);
		this.transformSize.setOffsetY(offsetY);
	}

	public void setTransformSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.setVirtualTransformSize(scaleX, offsetX, scaleY, offsetY);
	}

	/**
	 * Set the {@code sizeMode} of this {@code SuperWidget}.
	 * 
	 * <p>
	 * {@code sizeMode} determines how this {@code SuperWidget}'s size is
	 * calculated. The current size modes are:
	 * 
	 * <ul>
	 * <li>{@code TRANSFORM} - use {@code UDim2} to calculate size based on scale
	 * and offset</li>
	 * <li>{@code FIT_CONTENT} - automatically set the size of this
	 * {@code SuperWidget} to the total size of its children</li>
	 * </ul>
	 * 
	 * @param sizeMode
	 */
	public void setSizeMode(SizeMode sizeMode) {
		if (this.sizeMode == sizeMode)
			return;
		this.sizeMode = sizeMode;
		this.updateComputedSize();
	}

	/**
	 * Sets the maximum size that this {@code SuperWidget} can be using
	 * {@code UDim2} components (scaleX, offsetX, scaleY, offsetX), and updates the
	 * internal size along with re-rendering the swing component's size.
	 * 
	 * @param scaleX  an alpha value from {@code 0} to {@code 1} determining how
	 *                much horizontal space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMaxSize}
	 * @param offsetX horizontal offset in pixels
	 * @param scaleY  an alpha value from {@code 0} to {@code 1} determining how
	 *                much vertical space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMaxSize}
	 * @param offsetY vertical offset in pixels
	 * 
	 * @see #updateMaxSize
	 */
	public void setMaxSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.maxSize.setScaleX(scaleX);
		this.maxSize.setOffsetX(offsetX);
		this.maxSize.setScaleY(scaleY);
		this.maxSize.setOffsetY(offsetY);
		this.updateMaxSize();
	}

	/**
	 * Sets the minimum size that this {@code SuperWidget} can be using
	 * {@code UDim2} components (scaleX, offsetX, scaleY, offsetX), and updates the
	 * internal size along with re-rendering the swing component's size.
	 * 
	 * @param scaleX  an alpha value from {@code 0} to {@code 1} determining how
	 *                much horizontal space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMinSize}
	 * @param offsetX horizontal offset in pixels
	 * @param scaleY  an alpha value from {@code 0} to {@code 1} determining how
	 *                much vertical space this {@code SuperWidget} should occupy
	 *                based on the implementation of {@code computeMinSize}
	 * @param offsetY vertical offset in pixels
	 * 
	 * @see #updateMinSize
	 */
	public void setMinSize(double scaleX, int offsetX, double scaleY, int offsetY) {
		this.minSize.setScaleX(scaleX);
		this.minSize.setOffsetX(offsetX);
		this.minSize.setScaleY(scaleY);
		this.minSize.setOffsetY(offsetY);
		this.updateMinSize();
	}

	/**
	 * Sets the layout to use for this {@code SuperWidget}, which determines how its
	 * children will behave in terms of size, position, and visibility.
	 * 
	 * <p>
	 * The available layout options are:
	 * <ul>
	 * <li>{@code NONE} - no layout is set; you have full control over the absolute
	 * size and position of this {@code SuperWidget}</li>
	 * </ul>
	 * 
	 * @param satchelLayout the {@code SatchelLayout} preset to use for this
	 *                      {@code SuperWidget}
	 */
	public void setSatchelLayout(SatchelLayout satchelLayout) {
		this.satchelLayout = satchelLayout;
	}

	/**
	 * Adds a new child {@code Widget} to this {@code SuperWidget}. The top-level
	 * behavior of this method is adding the child {@code Widget} to the internal
	 * {@code children} array of this {@code SuperWidget}, but does <b>NOT</b> call
	 * the {@code add} method from the {@code SuperWidget}'s rendered swing
	 * component.
	 * 
	 * <p>
	 * <b>Note:</b> You <i>must</i> override this method to manually add the child
	 * swing component to this widget's swing component, while making sure to call
	 * {@code super.add()} beforehand to ensure the child widget is kept track of by
	 * the parent.
	 * 
	 * <p>
	 * <b>Example:</b>
	 * </p>
	 * 
	 * <pre>
	 * <code>
	 * &#64;Override
	 * public void add(Widget<?> childWidget) {
	 * 	super.add(childWidget);
	 * 	this.getRef().add(childWidget.getRef());
	 * }
	 * </code>
	 * </pre>
	 * 
	 * @param widget the {@code Widget} to add to this {@code SuperWidget}.
	 */
	public void add(Widget<?> widget) {
		this.children.add(widget);
	}

	public void updateComputedPosition() {
		Unit2 newComputedPosition = this.computeTransformPosition();
		this.computedPosition.setX(newComputedPosition.getX());
		this.computedPosition.setY(newComputedPosition.getY());

		this.ref.setLocation(
				this.computedPosition.getX(),
				this.computedPosition.getY());
	}

	/**
	 * Compute what the new {@code minSize} of this {@code SuperWidget}
	 * <b>should</b> be and then update the internal {@code computedMinSize} field
	 * along with rendering the widget's new size. Determining what the new
	 * {@code minSize} should be is up to the manual implementation of
	 * {@link #computedMinSize} in each subclass.
	 */
	public void updateMinSize() {
		Unit2 newComputedMinSize = this.computeMinSize();
		this.computedMinSize.setX(newComputedMinSize.getX());
		this.computedMinSize.setY(newComputedMinSize.getY());
		this.updateComputedSize();
	}

	/**
	 * Compute what the new {@code maxSize} of this {@code SuperWidget}
	 * <b>should</b> be and then update the internal {@code computedMaxSize} field
	 * along with rendering the widget's new size. Determining what the new
	 * {@code minSize} should be is up to the manual implementation of
	 * {@link #computedMinSize} in each subclass.
	 */
	public void updateMaxSize() {
		Unit2 newComputedMaxSize = this.computeMaxSize();
		this.computedMaxSize.setX(newComputedMaxSize.getX());
		this.computedMaxSize.setY(newComputedMaxSize.getY());
		this.updateComputedSize();
	}

	/**
	 * Compute what the new absolute size of this {@code SuperWidget} <b>should</b>
	 * be and then update the internal {@code computedSize} field along with
	 * rendering the widget's new size.
	 */
	public void updateComputedSize() {
		Unit2 newComputedSize = this.computeSize();
		this.computedSize.setX(newComputedSize.getX());
		this.computedSize.setY(newComputedSize.getY());

		this.ref.setSize(
				this.computedSize.getX(),
				this.computedSize.getY());
	}

	/**
	 * Calculate what the current absolute size of this {@code SuperWidget}
	 * <b>should</b> be, based on the widget's current {@code sizeMode} and other
	 * size constraints such as {@code maxSize} and {@code minSize}.
	 * 
	 * <p>
	 * <b>Note:</b> This will not update the rendered size of the
	 * {@code SuperWidget}, nor will it update the internal field which stores the
	 * computed size. This is a pure function, which will only re-compute what the
	 * {@code SuperWidget}'s absolute size <b>should</b> be, and then returns that
	 * result.
	 * 
	 * <p>
	 * This method will use the implemented methods by its subclasses for computing
	 * what its absolute size should be based on the currently set
	 * {@code sizeMode}. For example, if the size mode {@code TRANSFORM} is used,
	 * then this method will call the abstract method {@code computeTransformSize()}
	 * to retrieve what the transform size should be for that specific
	 * implementation of this {@code SuperWidget}.
	 * 
	 * @return what the newly computed absolute size of the {@code SuperWidget}
	 *         should be
	 */
	public Unit2 computeSize() {
		// compute virtual size
		Unit2 computedSize;
		switch (this.sizeMode) {
			case TRANSFORM -> computedSize = computeTransformSize();
			default -> throw new Error("No layout");
		}

		// apply constraints
		Unit2 maxSize = this.getComputedMaxSize();
		Unit2 minSize = this.getComputedMinSize();
		computedSize.setX((int) MathUtils.clamp(
				computedSize.getX(),
				minSize.getX(),
				maxSize.getX()));
		computedSize.setY((int) MathUtils.clamp(
				computedSize.getY(),
				minSize.getY(),
				maxSize.getY()));

		return computedSize;
	}

	public abstract Unit2 computeTransformPosition();

	/**
	 * A custom implementation of what this widget's size <b>should</b> be when
	 * using size mode {@code TRANSFORM}.
	 * 
	 * <p>
	 * The main purpose of this abstract method is to differentiate between the
	 * Widget and Window class, since they both inherit from SuperWidget. For
	 * example, the Window object will compute its transform size (scale and offset)
	 * with respect to the screen resolution, whereas Widgets compute their
	 * transform size based on their SuperWidget parent, which can be a Window or
	 * another Widget.
	 * 
	 * @return the newly computed transform size in {@code Unit2} pixels with
	 *         respect to some container boundaries such as the parent's absolute
	 *         size, or screen size if the SuperWidget is a Window.
	 */
	public abstract Unit2 computeTransformSize();

	/**
	 * A custom implementation of what this widget's {@code maxSize} <b>should</b>
	 * be in {@Code Unit2} pixels.
	 * 
	 * @return the newly computed {@code maxSize} of the widget in {@code Unit2}
	 *         pixels
	 */
	public abstract Unit2 computeMaxSize();

	/**
	 * A custom implementation of what this widget's {@code minSize} <b>should</b>
	 * be in {@Code Unit2} pixels.
	 * 
	 * @return the newly computed {@code minSize} of the widget in {@code Unit2}
	 *         pixels
	 */
	public abstract Unit2 computeMinSize();
}
