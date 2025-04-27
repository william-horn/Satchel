/*
 * @written 3/30/2025
 */
package satchel.math.vectors;

import satchel.math.vectors.abstracts.Vector;

/**
 * A custom implementation of the Vector class for dealing with math in 2D
 * space.
 */
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

	// TODO: Add documentation
	//
	// Public getters
	//
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

	public void setX(double value) {
		this.components[0] = value;
	}

	public void setY(double value) {
		this.components[1] = value;
	}

	public void setZ(double value) {
		this.components[2] = value;
	}

	public void setW(double value) {
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
		return "UDim2";
	}
}