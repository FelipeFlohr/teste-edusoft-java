package br.com.edusoft.testejava.modules.env.exceptions;

public class PropertiesNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PropertiesNotFoundException() {
		super("Arquivo \"application.properties\" não encontrado nos recursos da aplicação.");
	}
}
