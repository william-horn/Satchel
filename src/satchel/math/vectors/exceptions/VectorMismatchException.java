package satchel.math.vectors.exceptions;

public class VectorMismatchException extends RuntimeException {
	public VectorMismatchException() {
		super("Illegal Vector comparison or operation. Vectors operands must be the same dimensions.");
	}
}
