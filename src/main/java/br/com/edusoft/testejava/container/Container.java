package br.com.edusoft.testejava.container;

import com.google.inject.AbstractModule;

import br.com.edusoft.testejava.env.IEnvironmentSettings;
import br.com.edusoft.testejava.env.impl.EnvironmentSettingsImpl;
import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.annotations.Request;
import br.com.edusoft.testejava.utils.logger.annotations.RequestScope;
import br.com.edusoft.testejava.utils.logger.impl.LoggerImpl;

public class Container extends AbstractModule {
	@Override
	protected void configure() {
		// Scope binding
		bindScope(Request.class, new RequestScope());

		// Logger
		bind(ILogger.class)
			.to(LoggerImpl.class);

		// Environment Settings
		bind(IEnvironmentSettings.class)
			.to(EnvironmentSettingsImpl.class);

	}
}
