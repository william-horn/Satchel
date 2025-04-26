/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package satchel.shared.util;

/**
 * Provides a library of format methods for handling general string format
 * needs, or converting complex data into a formatted string
 */
public class Formatter {

	/**
	 * Takes any array and returns a string separating the array contents by
	 * some separator string.
	 *
	 * @param array     the array to concat
	 * @param separator the string to separate each array element by in the
	 *                  returned string
	 *
	 * @return the returned string of the concatenated array
	 * 
	 * @see #objectArrayToString(Object[], String)
	 */
	public static String objectArrayToString(Object[] array, String separator) {
		StringBuilder out = new StringBuilder();

		for (int index = 0; index < array.length; index++) {
			if (array[index] == null)
				out.append("null");
			else
				out.append(array[index].toString());

			if (index < array.length - 1)
				out.append(separator);
		}

		return out.toString();
	}

	/**
	 * An overload of {@link #objectArrayToString(Object[], String)} which defaults
	 * the
	 * {@code separator} parameter to {@code ", "}
	 *
	 * @param array the array to concat
	 * @return the returned string of the concatenated array
	 * 
	 * @see #objectArrayToString(Object[])
	 */
	public static String objectArrayToString(Object[] array) {
		return objectArrayToString(array, ", ");
	}

	public static String toTimerString(double seconds) {
		double minutes = (seconds / 60.0) % 60.0;
		double hours = (minutes / 60.0);
		return String.format(
				"%02d:%02d:%02d.%02d",
				(long) hours,
				(long) minutes,
				(long) seconds % 60,
				(long) ((seconds * 1000.0) % 1000));
	}
}