package br.com.edusoft.testejava.services.http.get.impl;

import javax.inject.Provider;

import br.com.edusoft.testejava.services.http.get.IHttpGetService;

public class HttpGetServiceProvider implements Provider<IHttpGetService> {
	@Override
	public IHttpGetService get() {
		return new HttpGetServiceImpl();
	}
}
