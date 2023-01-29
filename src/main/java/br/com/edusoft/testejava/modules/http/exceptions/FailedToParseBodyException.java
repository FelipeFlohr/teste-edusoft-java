package br.com.edusoft.testejava.modules.http.exceptions;

import okhttp3.ResponseBody;

public class FailedToParseBodyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FailedToParseBodyException(String url, ResponseBody body) {
		super("Não foi possível transformar a resposta da requisição do endereço " + url + " para String. Corpo da requisição: " + body.toString());
	}
}
