package br.com.edusoft.testejava;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.module.Module;
import br.com.edusoft.testejava.services.aluno.token.IAlunoTokenService;

public class Main {
	public static void main(String[] args) {
		// Configura o Injector do Guice
		final Injector injector = Guice.createInjector(new Module());

		final IAlunoTokenService tokenService = injector.getInstance(IAlunoTokenService.class);
		System.out.println(tokenService.getToken());
	}
}
