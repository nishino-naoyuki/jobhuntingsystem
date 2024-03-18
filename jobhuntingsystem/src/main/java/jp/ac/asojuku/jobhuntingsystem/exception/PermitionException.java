package jp.ac.asojuku.jobhuntingsystem.exception;

public class PermitionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PermitionException(String e) {
		super(e);
	}
	public PermitionException(Exception e) {
		super(e);
	}
}
