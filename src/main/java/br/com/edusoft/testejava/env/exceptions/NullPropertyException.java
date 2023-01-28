package br.com.edusoft.testejava.env.exceptions;

public class NullPropertyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NullPropertyException(String propKey) {
		super("A chave " + propKey + " possui um valor nulo nas configurações da aplicação.");
	}
}
