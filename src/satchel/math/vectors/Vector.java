/*
 * @written 4/2/2025
 */
package satchel.math.vectors;

import satchel.math.util.MathUtils;

/**
 * A more specific sub-category of BaseVector which includes implementation that
 * does not assume that it's components are necessarily strictly int values.
 */
public abstract class Vector<T extends Vector<T>> extends BaseVector<T> {

	private Double magnitude;
	private T unit;

	/**
	 * Divide the components of this Vector<T> by some other Vector<T>
	 *
	 * @param v the Vector<T> to divide the components of
	 * @return the Vector<T> quotient
	 */
	public T divide(T v) {
		return this.newVector(this.computeComponents(
				v,
				args -> (double) args[0] / (double) args[1]));
	}

	/**
	 * Overload: {@code divide}
	 *
	 * Divide the components of this Vector<T> with some common divisor
	 *
	 * @param dec the common divisor to divide into the components of this
	 *            Vector<T>
	 * @return ...
	 */
	public T divide(double dec) {
		return this.newVector(this.computeComponents(
				args -> (double) args[0] / dec));
	}

	/**
	 * Overload: {@code divide}
	 *
	 * @param scalar the common integer divisor to divide into the components of
	 *               this Vector<T>
	 * @return ...
	 */
	public T divide(int scalar) {
		return this.newVector(this.computeComponents(
				args -> (double) args[0] / scalar));
	}

	/**
	 * Overload: {@code multiply}
	 *
	 * @param dec the common double to multiply the components of this Vector<T>
	 *            by
	 * @return ...
	 */
	public T multiply(double dec) {
		return this.newVector(this.computeComponents(
				args -> (double) args[0] * dec));
	}

	/**
	 * Overload: {@code add}
	 *
	 * @param dec
	 * @return ...
	 */
	public T add(double dec) {
		return this.newVector(this.computeComponents(
				args -> (double) args[0] + dec));
	}

	/**
	 * Overload: {@code subtract}
	 *
	 * @param dec
	 * @return ...
	 */
	public T subtract(double dec) {
		return this.newVector(this.computeComponents(
				args -> (double) args[0] - dec));
	}

	/**
	 * Floor the components of this Vector<T>
	 *
	 * @return the new Vector<T> with floored components of this Vector<T>
	 */
	public T floor() {
		return this.newVector(this.computeComponents(
				args -> Math.floor((double) args[0])));
	}

	/**
	 * Ceil the components of this Vector<T>
	 *
	 * @return the new Vector<T> with ceiled components of this Vector<T>
	 */
	public T ceil() {
		return this.newVector(this.computeComponents(
				args -> Math.ceil((double) args[0])));
	}

	/**
	 * Get the magnitude of this Vector<T>
	 *
	 * @return the magnitude of this Vector<T>
	 */
	public double magnitude() {
		if (this.magnitude != null) {
			return this.magnitude;
		}

		this.magnitude = 0.0;
		for (int i = 0; i < this.components.length; i++) {
			this.magnitude += this.components[i] * this.components[i];
		}
		this.magnitude = Math.sqrt(this.magnitude);

		return this.magnitude;
	}

	/**
	 * Get the midpoint between this Vector<T> and some other Vector<T>
	 *
	 * @param v the Vector<T> to get the midpoint between
	 * @return the resulting Vector<T> representing the midpoint
	 */
	public T midpoint(T v) {
		return this.newVector(this.computeComponents(
				v,
				args -> (((double) args[0] + (double) args[1]) / 2)));
	}

	/**
	 * Get the unit vector of this Vector<T>
	 *
	 * @return the unit vector of this Vector<T>
	 */
	public T unit() {
		if (this.unit != null) {
			return this.unit;
		}
		this.unit = this.divide(this.magnitude());
		return this.unit;
	}

	/**
	 * Create a {@code Vector} linearly interpolated from this vector to a target
	 * vector given some {@code alpha} value between {@code 0} and {@code 1}
	 * 
	 * @param v     the target vector
	 * @param alpha the parameterized change from this vector to the target vector
	 * 
	 * @return the linearly interpolated vector
	 * 
	 * @see #lerp(Vector, double)
	 * @see classes.util.Math2#lerp(double, double, double)
	 */
	public T lerp(T v, double alpha) {
		return this.newVector(this.computeComponents(
				v,
				args -> MathUtils.lerp((double) args[0], (double) args[1], alpha)));
	}
}