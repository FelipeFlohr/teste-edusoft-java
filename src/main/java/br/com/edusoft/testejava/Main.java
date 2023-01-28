package br.com.edusoft.testejava;

import com.google.inject.Guice;
import com.google.inject.Injector;

import br.com.edusoft.testejava.container.Container;
import br.com.edusoft.testejava.env.IEnvironmentSettings;

public class Main {
	public static void main(String[] args) {
		// Configura o Injector do Guice
		final Injector injector = Guice.createInjector(new Container());

		final IEnvironmentSettings env = injector.getInstance(IEnvironmentSettings.class);
		System.out.println(env.getConfig().getFetchAlunos().getToken());
	}
}
