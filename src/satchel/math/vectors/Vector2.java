/*
 * @written 3/30/2025
 */
package satchel.math.vectors;

import satchel.math.vectors.abstracts.Vector;

/**
 * A custom implementation of the Vector class for dealing with math in 2D
 * space.
 */
public class Vector2 extends Vector<Vector2> {

	public Vector2() {
		this(0, 0);
	}

	public Vector2(double x, double y) {
		this.components = new double[] { x, y };
	}

	// TODO: Add documentation
	//
	// Public getters
	//
	public double getX() {
		return this.get(0);
	}

	public double getY() {
		return this.get(1);
	}

	public void setX(double value) {
		this.components[0] = value;
	}

	public void setY(double value) {
		this.components[1] = value;
	}

	public double angle() {
		return Math.atan2(this.getY(), this.getX());
	}

	// public double screenAngle() {
	// return Math.atan2(-this.getY(), this.getX());
	// }

	/**
	 * Evaluate the general linear function of the line passing through this
	 * Vector2 (x0, y0) and a target Vector2 (x1, y1), with respect to {@code x}
	 *
	 * @param x0        point1.x value
	 * @param x1        point2.x value
	 * @param y0        point1.y value
	 * @param y1        point2.y value
	 * @param x         input value to the function
	 * @param useDomain whether or not to check for the domain set by the two
	 *                  points
	 * @return f(x)
	 */
	private Double evaluateLinearFunctionWithRespectTo(
			double x0,
			double x1,
			double y0,
			double y1,
			double x,
			boolean useDomain) {
		double dx = x1 - x0;
		double dy = y1 - y0;

		// check domain
		if (useDomain && x < Math.min(x0, x1) || x > Math.max(x0, x1)) {
			return null;
		}

		// not a function
		if (dx == 0) {
			return null;
		}

		Double m = dy / dx;
		Double y = m * (x - x0) + y0;
		return y;
	}

	/**
	 * Evaluate the equation of a line passing through this Vector2 and another
	 * Vector2 with respect to {@code x}
	 *
	 * @param p2 the second point
	 * @param x  the input to the function
	 * @return f(x)
	 */
	public Double evaluateY(Vector2 p2, double x) {
		return this.evaluateLinearFunctionWithRespectTo(
				this.getX(), p2.getX(),
				this.getY(), p2.getY(),
				x, true);
	}

	/**
	 * Evaluate the equation of a line passing through this Vector2 and another
	 * Vector2 with respect to {@code y}
	 *
	 * @param p2 the second point
	 * @param y  the input to the function
	 * @return x(y)
	 */
	public Double evaluateX(Vector2 p2, double y) {
		return this.evaluateLinearFunctionWithRespectTo(
				this.getY(), p2.getY(),
				this.getX(), p2.getX(),
				y, true);
	}

	@Override
	protected Vector2 newVector(double[] components) {
		return new Vector2(components[0], components[1]);
	}

	@Override
	public String toString() {
		return new StringBuilder("Vector2{")
				.append(this.getX())
				.append(", ")
				.append(this.getY())
				.append("}")
				.toString();
	}
}