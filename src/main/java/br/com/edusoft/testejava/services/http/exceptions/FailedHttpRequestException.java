package br.com.edusoft.testejava.services.http.exceptions;

public class FailedHttpRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FailedHttpRequestException(String url) {
		super("A requisição para o endereço " + url + " falhou.");
	}

	public FailedHttpRequestException(String url, int statusCode) {
		super("A requisição para o endereço " + url + " falhou com o código " + statusCode + ".");
	}
}
