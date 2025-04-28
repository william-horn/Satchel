/*
 * @written 4/2/2025
 */
package satchel.math.vectors;

import satchel.math.vectors.abstracts.BaseVector;

/**
 * A class similar to Vector2, however it's components must be initialized as
 * int values and they will never change into a decimal form, even though they
 * are stored as doubles.
 */
public class Unit2 extends BaseVector<Unit2> {

	public Unit2() {
		this(0, 0);
	}

	public Unit2(int x, int y) {
		this.components = new double[] { x, y };
	}

	// TODO: Add documentation
	//
	// Public getters
	//
	public int getX() {
		return this.getInt(0);
	}

	public int getY() {
		return this.getInt(1);
	}

	public void setX(int value) {
		this.components[0] = value;
	}

	public void setY(int value) {
		this.components[1] = value;
	}

	public Vector2 toVector2() {
		return new Vector2(this.getX(), this.getY());
	}

	@Override
	protected Unit2 newVector(double[] components) {
		return new Unit2(
				(int) components[0],
				(int) components[1]);
	}

	@Override
	public String toString() {
		return new StringBuilder("Unit2{")
				.append(this.getInt(0))
				.append(", ")
				.append(this.getInt(1))
				.append("}")
				.toString();
	}
}