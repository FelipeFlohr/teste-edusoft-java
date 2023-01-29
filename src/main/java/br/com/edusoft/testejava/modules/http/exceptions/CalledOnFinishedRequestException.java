package br.com.edusoft.testejava.modules.http.exceptions;

public class CalledOnFinishedRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CalledOnFinishedRequestException(String url) {
		super("Uma chamada foi feita para um serviço POST HTTP já finalizado no endereço " + url + ".");
	}
}
