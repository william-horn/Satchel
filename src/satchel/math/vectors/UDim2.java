/*
 * @written 3/30/2025
 */
package satchel.math.vectors;

import satchel.math.vectors.abstracts.Vector;

// TODO: Add documentation
public class UDim2 extends Vector<UDim2> {

	public UDim2() {
		this(0, 0, 0, 0);
	}

	public UDim2(double scaleX, double offsetX, double scaleY, double offsetY) {
		this.components = new double[] {
				scaleX,
				offsetX,
				scaleY,
				offsetY };
	}

	public UDim2(double all) {
		this.components = new double[] { all, all, all, all };
	}

	// TODO: Add documentation
	// getters
	public double getScaleX() {
		return this.get(0);
	}

	public double getOffsetX() {
		return this.get(1);
	}

	public double getScaleY() {
		return this.get(2);
	}

	public double getOffsetY() {
		return this.get(3);
	}

	public Unit2 toComputedComponents(Unit2 bounds) {
		return new Unit2(
				(int) (bounds.getX() * this.getScaleX() + this.getOffsetX()),
				(int) (bounds.getY() * this.getScaleY() + this.getOffsetY()));
	}

	// setters
	public void setScaleX(double value) {
		this.components[0] = value;
	}

	public void setOffsetX(double value) {
		this.components[1] = value;
	}

	public void setScaleY(double value) {
		this.components[2] = value;
	}

	public void setOffsetY(double value) {
		this.components[3] = value;
	}

	@Override
	protected UDim2 newVector(double[] components) {
		return new UDim2(
				components[0],
				components[1],
				components[2],
				components[3]);
	}

	@Override
	public String toString() {
		return new StringBuilder(this.getClass().getSimpleName())
				.append("{")
				.append(this.getScaleX())
				.append(", ")
				.append(this.getOffsetX())
				.append(", ")
				.append(this.getScaleY())
				.append(", ")
				.append(this.getOffsetY())
				.append("}")
				.toString();
	}
}