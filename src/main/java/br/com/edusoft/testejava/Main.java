package br.com.edusoft.testejava;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
import br.com.edusoft.testejava.modules.aluno.datasources.IFetchAlunos;

public class Main {
	public static void main(String[] args) {
		// Configura o Injector do Guice
		final Injector injector = Guice.createInjector(new Container());

		final IFetchAlunos fetcher = injector.getInstance(IFetchAlunos.class);
		System.out.println(fetcher.fetchAlunos());
	}
}
