package br.com.edusoft.testejava.modules.http.get.services.impl;

import javax.inject.Provider;

import br.com.edusoft.testejava.modules.http.get.services.IHttpGetService;

public class HttpGetServiceProvider implements Provider<IHttpGetService> {
	@Override
	public IHttpGetService get() {
		return new HttpGetServiceImpl();
	}
}
