package satchel.shared.util;

public class Parser {
	public static Object fromJSON(String json) {
		return null;
	}

	public static <T> T parsePrimitive(String value, Class<T> type) {
		if (type == Integer.class) {
			return type.cast(Integer.parseInt(value));
		} else if (type == Double.class) {
			return type.cast(Double.parseDouble(value));
		} else if (type == Boolean.class) {
			return type.cast(Boolean.parseBoolean(value));
		}
		return type.cast(value);
	}
}
