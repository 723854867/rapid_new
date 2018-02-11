package javax.exception;

public class AssertFailException extends RuntimeException {

	private static final long serialVersionUID = -2905784962503753982L;

	public AssertFailException(String message) {
		super(message);
	}
}
