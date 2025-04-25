package shared.util;

public class Conversion {
	public static int[] hexToRGB(String hex) {
		if (hex.charAt(0) != '#')
			throw new IllegalArgumentException("Argument is missing '#' before hex code");

		int r = Integer.valueOf(hex.substring(1, 3), 16);
		int g = Integer.valueOf(hex.substring(3, 5), 16);
		int b = Integer.valueOf(hex.substring(5, 7), 16);
		return new int[] { r, g, b };
	}
}
