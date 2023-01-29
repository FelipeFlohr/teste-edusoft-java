package br.com.edusoft.testejava.modules.http.services.impl;

import com.google.inject.Singleton;

import br.com.edusoft.testejava.modules.http.entities.get.HttpGetRequestBuilder;
import br.com.edusoft.testejava.modules.http.entities.post.HttpPostRequestBuilder;
import br.com.edusoft.testejava.modules.http.services.ISyncHttpService;

@Singleton
public class SyncHttpServiceImpl implements ISyncHttpService {
	@Override
	public HttpGetRequestBuilder get(String url) {
		return new HttpGetRequestBuilder(url);
	}

	@Override
	public HttpPostRequestBuilder post(String url) {
		return new HttpPostRequestBuilder(url);
	}
}
