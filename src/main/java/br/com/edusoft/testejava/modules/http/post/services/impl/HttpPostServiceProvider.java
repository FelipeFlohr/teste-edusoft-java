package br.com.edusoft.testejava.modules.http.post.services.impl;

import javax.inject.Provider;

import br.com.edusoft.testejava.modules.http.post.services.IHttpPostService;

public class HttpPostServiceProvider implements Provider<IHttpPostService> {
	@Override
	public IHttpPostService get() {
		return new HttpPostServiceImpl();
	}
}
