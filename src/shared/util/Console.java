/*
 * @author: William J. Horn
 * github: https://github.com/william-horn
 */
package shared.util;

import java.util.ArrayList;
import java.util.Scanner;

import shared.exceptions.ParsingException;

public class Console {
	final private static Scanner input = new Scanner(System.in);

	final private static String TEXT_RESET = "\u001B[39m";
	final private static String BG_RESET = "\u001B[49m";
	final private static String FULL_RESET = "\u001B[0m";
	final private static String TEXT_CODE = "\u001B[38;2;";
	final private static String BG_CODE = "\u001B[48;2;";

	final private static ArrayList<String> menuChoices = new ArrayList<>();

	private static int jumpToChar(String str, int startIndex, char ch) {
		int len = str.length();
		int end = startIndex;
		while (end < len && str.charAt(end) != ch)
			end++;
		return end;
	}

	/**
	 * Replaces the console color styling tags in a given string into their
	 * respective ASCII codes. The color styling tag syntax is as follows:
	 * 
	 * <p>
	 * <ul>
	 * <li>{@code <directive colorName>} - looks up {@code colorName} in system
	 * config to find the corresponding hex color</li>
	 * <li>{@code <directive #xxxxxx>} - parses the given hex string and uses it's
	 * color directly
	 * </ul>
	 * 
	 * <p>
	 * The valid {@code directive} values are:
	 * <ul>
	 * <li>{@code <text ...>} - sets the text color</li>
	 * <li>{@code <bg ...>} - sets the background color</li>
	 * </ul>
	 * 
	 * @param str        the string to parse the color styling tags in
	 * @param resetAtEnd whether or not the color styles reset at the end of the
	 *                   string ({@code true} means they reset, {@code false} means
	 *                   they don't)
	 * @return the parsed string with the ASCII code colors
	 * @see #parseConsoleColors(String, boolean)
	 */
	public static String parseConsoleColors(String str, boolean resetAtEnd) {
		StringBuilder out = new StringBuilder();
		char lastToken = '\0';
		int len = str.length();
		boolean colorsEnabled = Config.get(
				"console.consoleColorsEnabled",
				Boolean.class);

		for (int index = 0; index < len; index++) {
			char token = str.charAt(index);
			if (token == '<' && lastToken != '\\') {
				// build directive
				int start = index + 1;
				int end = jumpToChar(str, start, ' ');
				String fg = null;
				String bg = null;
				String reset = null;

				// check the directive
				switch (str.substring(start, end)) {
					case "text" -> {
						fg = TEXT_CODE;
						reset = TEXT_RESET;
					}
					case "bg" -> {
						bg = BG_CODE;
						reset = BG_RESET;
					}
					case "full" -> {
						fg = TEXT_CODE;
						bg = BG_CODE;
						reset = FULL_RESET;
					}
					default -> throw new ParsingException(
							"Invalid directive to console colors");
				}

				// build color
				start = end + 1;
				end = jumpToChar(str, start, '>');

				// apply the color
				String color = str.substring(start, end);
				String colorProp = Config.get("console.colors." + color);

				if (colorsEnabled) {
					if (color.equals("reset")) {
						out.append(reset);
					} else {
						if (colorProp != null) {
							color = colorProp;
						}
						if (fg != null) {
							out.append(fg);
							Conversion.bufferRGBToASCII(
									Conversion.hexToRGB(color),
									out);
						}
						if (bg != null) {
							out.append(bg);
							Conversion.bufferRGBToASCII(
									Conversion.hexToRGB(color),
									out);
						}
					}
				}
				index = end;
			} else if (token != '\\') {
				out.append(token);
			}
			lastToken = token;
		}

		if (resetAtEnd && colorsEnabled) {
			out.append(FULL_RESET);
		}
		return out.toString();
	}

	/**
	 * Overload of {@link #parseConsoleColors(String, boolean)} which sets the
	 * second parameter of {@code resetAtEnd} to {@code true} by default.
	 * 
	 * @param str        the string to parse the color styling tags in
	 * @param resetAtEnd whether or not the color styles reset at the end of the
	 *                   string ({@code true} means they reset, {@code false} means
	 *                   they don't)
	 * @return the parsed string with the ASCII code colors
	 * @see #parseConsoleColors(String, boolean)
	 * @see #parseConsoleColors(String)
	 */
	public static String parseConsoleColors(String str) {
		return parseConsoleColors(str, true);
	}

	/**
	 * Internally calls {@code System.out.println} but applies conditional
	 * console colors.
	 *
	 * @param contents the collection of objects to print
	 * @see #println(Object...)
	 */
	public static void println(Object... contents) {
		System.out.println(
				parseConsoleColors(Formatter.objectArrayToString(contents)));
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

	/**
	 * Prompt the user to enter some text field
	 * 
	 * @param message the message the user enters as a string
	 * @param def     the default string to be returned if the user entered an empty
	 *                string
	 * @return the text value the user entered, or the default string
	 */
	public static String promptString(String message, String def) {
		String submission = promptString(message);

		// handle default case
		if (submission.isEmpty()) {
			printInputConfirmation("default: " + def);
			return def;
		}
		return submission;
	}

	public static String promptString(String message) {
		print("> <text #40FF2B>" + message);
		return input.nextLine();
	}

	public static boolean promptBoolean(String message, boolean def) {
		Boolean answer = null;
		do {
			String submission = promptString(message);
			if (submission.equals("true")) {
				answer = true;
			} else if (submission.equals("false")) {
				answer = false;
			} else if (submission.isEmpty()) {
				answer = def;
				printInputConfirmation("default: " + def);
			} else {
				printError("Expected \"y\" or \"no\", got \"" + submission + "\"");
			}
		} while (answer == null);
		return answer;
	}

	public static int promptInt(String message, int def) {
		Integer answer = null;
		do {
			String submission = promptString(message);
			if (submission.matches("-?\\d+")) {
				answer = Integer.parseInt(submission);
			} else if (submission.isEmpty()) {
				answer = def;
				printInputConfirmation("default: " + def);
			} else {
				printError("Expected parsable int value, got \"" + submission + "\"");
			}
		} while (answer == null);
		return answer;
	}

	public static double promptDouble(String message, double def) {
		Double answer = null;
		do {
			String submission = promptString(message);
			if (submission.matches("[+-]?\\d*\\.?\\d+")) {
				answer = Double.parseDouble(submission);
			} else if (submission.isEmpty()) {
				answer = def;
				printInputConfirmation("default: " + def);
			} else {
				printError("Expected parsable double value, got \"" + submission + "\"");
			}
		} while (answer == null);
		return answer;
	}

	public static int promptMenu(String menuTitle, String message, int def) {
		if (menuChoices.size() == 0) {
			throw new Error("Menu choices have not been set");
		} else if (def < 0) {
			throw new Error("Default choice cannot be less than 0");
		} else if (def > menuChoices.size()) {
			throw new Error("Default choice cannot exceed choice list");
		}

		println(menuTitle);

		Integer choice = null;
		do {
			int submission = promptInt(message, def);
			if (submission < 0) {
				printError("Menu choice cannot be less than 0");
			} else if (submission > menuChoices.size()) {
				printError("Menu choice cannot exceed choice list");
			} else {
				choice = submission;
			}
		} while (choice == null);
		return choice;
	}

	public static void setMenuChoices(String... choices) {
		menuChoices.clear();
		for (int index = 0; index < choices.length; index++) {
			menuChoices.add(choices[index]);
		}
	}

	/*
	 * Private utilities
	 */
	private static void printError(String message) {
		println("> <text red>Input error: " + message);
	}

	private static void printInputConfirmation(String message) {
		println("<text #18a1e1>" + message);
	}
}