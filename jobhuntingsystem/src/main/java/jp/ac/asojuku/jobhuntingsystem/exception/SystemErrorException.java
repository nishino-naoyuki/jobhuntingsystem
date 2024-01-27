package jp.ac.asojuku.jobhuntingsystem.exception;

public class SystemErrorException extends Exception{
	public SystemErrorException(String e) {
		super(e);
	}
	public SystemErrorException(Exception e) {
		super(e);
	}
}
