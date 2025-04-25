/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package math.util;

/**
 * Provides additional math utility functions that are not natively part of the
 * Java Math library
 */
public class MathUtils {

	/**
	 * Linear interpolation between {@code a} and {@code b} using some value
	 * 1 >= {@code t} >= 0
	 *
	 * @param t parameterized variable for {@code a} and {@code b}
	 * @param a initial value
	 * @param b final value
	 * 
	 * @return the interpolation between {@code a} and {@code b} using t
	 * 
	 * @see #lerp(double, double, double)
	 */
	public static double lerp(double a, double b, double t) {
		return a * (1.0 - t) + b * t;
	}

	/**
	 * Takes a {@code double} and converts it to the multiplicative identity of the
	 * same sign. If {@code n} is zero then the result stays zero.
	 * 
	 * @param n
	 * @return the signed multiplicative identity of {@code n}
	 * @see #toSign(double)
	 */
	public static double sign(double n) {
		if (n == 0)
			return n;

		return Math.abs(n) / n;
	}

	/**
	 * Get a random integer from {@code 0} to a specified {@code upperBound}
	 * 
	 * @param upperBound the specified {@code upperBound}
	 * @return a random integer from {@code 0} to the specified {@code upperBound}
	 * @see #randInt(int)
	 */
	public static int randInt(int upperBound) {
		return (int) (Math.random() * upperBound);
	}

	public static double clamp(double value, double min, double max) {
		return Math.max(min, Math.min(max, value));
	}
}
