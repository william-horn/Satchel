package shared.util;

import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Config {
	private static Properties config = new Properties();

	public static void set(String prop, String data) {
		config.setProperty(prop, data);
	}

	public static String get(String prop) {
		return config.getProperty(prop);
	}

	public static void save() {
		try (FileOutputStream out = new FileOutputStream("config.properties")) {
			config.store(out, "Config properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try (FileInputStream in = new FileInputStream("config.properties")) {
			config.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
