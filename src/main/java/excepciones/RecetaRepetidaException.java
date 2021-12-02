package excepciones;

public class RecetaRepetidaException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RecetaRepetidaException(String string) {
		super(string);
	}
}
