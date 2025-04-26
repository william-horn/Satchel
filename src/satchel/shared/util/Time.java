/*
 * @written 3/29/2025
 */
package satchel.shared.util;

/**
 * A public static library for converting between units of time.
 */
public class Time {

	// TODO: Add documentation
	public static long tick() {
		return System.nanoTime();
	}

	public static long secondsToNano(double seconds) {
		return (long) (seconds * 1_000_000_000L);
	}

	public static double nanoToSeconds(long nano) {
		return (double) nano / 1_000_000_000L;
	}

	public static double nanoToMillisecond(long nano) {
		return nano / 1_000_000.0;
	}
}