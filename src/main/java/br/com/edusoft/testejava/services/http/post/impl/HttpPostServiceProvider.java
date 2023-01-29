package br.com.edusoft.testejava.services.http.post.impl;

import javax.inject.Provider;

import br.com.edusoft.testejava.services.http.post.IHttpPostService;

public class HttpPostServiceProvider implements Provider<IHttpPostService> {
	@Override
	public IHttpPostService get() {
		return new HttpPostServiceImpl();
	}
}
