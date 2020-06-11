package exception;

public class DatabaseException extends Exception {

	private static final long serialVersionUID = 6186263838409046177L;
	
	public DatabaseException() {
		super("Neuspjesno spajanje na bazu podataka.");
	}
	
	public DatabaseException(String message) {
		super(message);
	}
	
	public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}
}
