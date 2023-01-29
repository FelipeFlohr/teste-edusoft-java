package br.com.edusoft.testejava.modules.http.services;

import br.com.edusoft.testejava.modules.http.entities.get.HttpGetRequestBuilder;
import br.com.edusoft.testejava.modules.http.entities.post.HttpPostRequestBuilder;

public interface ISyncHttpService {
	public HttpGetRequestBuilder get(String url);
	public HttpPostRequestBuilder post(String url);
}
