/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package shared.util;

import java.util.Scanner;

import shared.exceptions.ParsingException;

public class Console {
	final private static Scanner input = new Scanner(System.in);

	final private static String COLOR_TAG_PATTERN = "\\$([a-zA-Z]+)\\-?([a-zA-Z_]*)";
	final private static String TEXT_RESET = "\u001B[39m";
	final private static String BG_RESET = "\u001B[49m";
	final private static String FULL_RESET = "\u001B[0";
	final private static String TEXT_CODE = "\u001B[38;2;";
	final private static String BG_CODE = "\u001B[48;2;";

	/**
	 * Takes a given list of {@code Object} values, converts them into strings
	 * using {@code toString()} (if possible), and applies the console color tag
	 * parser to scan the strings for tokens like {@code $text-color},
	 * {@code $bg-color}, etc.
	 * 
	 * <p>
	 * If {@code Console.consoleColorsEnabled} is set to {@code false}, then this
	 * method will remove all console tags like {@code $text-color} and
	 * {@code $bg-color} from any processed string values.
	 * 
	 * @param contents the list of {@code Object} values to toString
	 * @return a cleaned String that either consumes the console color tokens, or
	 *         removes them, based on {@code Console.consoleColorsEnabled}
	 * 
	 * @see #parseConsoleColors(Object...)
	 */
	public static String parseConsoleColors(Object... contents) {
		if (Config.get("console.consoleColorsEnabled", Boolean.class))
			return substituteASCIIColors(Formatter.objectArrayToString(contents));
		else
			return Formatter
					.objectArrayToString(contents)
					.replaceAll(COLOR_TAG_PATTERN + " ", "");
	}

	// <text blue>hello world</text>
	// <text #F8WHU9>sup world</text>
	public static String substituteASCIIColors(String str, boolean resetAtEnd) {
		StringBuilder out = new StringBuilder();
		char lastToken = '\0';

		int len = str.length();
		for (int index = 0; index < len; index++) {
			char token = str.charAt(index);
			if (token == '<' && lastToken != '\\') {
				int start = index + 1;
				int end = start;
				while (end < len && str.charAt(end) != ' ') {
					end++;
				}

				StringBuilder ASCIIBuffer = new StringBuilder();
				String directive = str.substring(start, end);

				if (directive.equals("text")) {
					ASCIIBuffer.append(TEXT_CODE);
				} else if (directive.equals("bg")) {
					ASCIIBuffer.append(BG_CODE);
				} else {
					throw new ParsingException("Invalid directive to console colors");
				}

				start = end + 1;
				end = start;
				while (end < len && str.charAt(end) != '>') {
					end++;
				}

				String color = str.substring(start, end);
				if (color.equals("reset")) {
					ASCIIBuffer.append();
				} else {
					int[] rgb = Conversion.hexToRGB(color);

					ASCIIBuffer.append(rgb[0]).append(";");
					ASCIIBuffer.append(rgb[1]).append(";");
					ASCIIBuffer.append(rgb[2]).append("m");
				}

				index = end;
			} else {
				out.append(token);
			}
		}

		return out.toString();
	}

	public static String substituteASCIIColors(String str) {
		return substituteASCIIColors(str, true);
	}

	/**
	 * Internally calls {@code System.out.println} but applies conditional
	 * console colors.
	 *
	 * @param contents the collection of objects to print
	 * @see #println(Object...)
	 */
	public static void println(Object... contents) {
		System.out.println(parseConsoleColors(contents));
	}

	/**
	 * Internally calls {@code System.out.print} but applies conditional console
	 * colors
	 *
	 * @param message the message to inline print
	 * @see #print(String)
	 */
	public static void print(String message) {
		System.out.print(parseConsoleColors(message));
	}

	public static String promptMessage(String message, String def) {
		print("> $text-green " + message);
		String submission = input.nextLine();

		// Handle default case
		if (submission.equals("")) {
			print("$text-purple default: $text-reset " + def + "\n\n");
			return def;
		}

		return submission;
	}
}