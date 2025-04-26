package satchel.shared.exceptions;

public class ParsingException extends RuntimeException {
	public ParsingException(String message) {
		super("Parsing error: " + message);
	}
}
