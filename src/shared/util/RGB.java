package shared.util;

public class RGB {
	final private int r;
	final private int g;
	final private int b;

	public RGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getB() {
		return this.b;
	}

	public int getG() {
		return this.g;
	}

	public int getR() {
		return this.r;
	}

	@Override
	public String toString() {
		return new StringBuilder("RGB<")
				.append(this.getR())
				.append(", ")
				.append(this.getG())
				.append(", ")
				.append(this.getB())
				.append(">")
				.toString();
	}
}
