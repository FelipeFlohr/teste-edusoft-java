package br.com.edusoft.testejava.modules.http.post.services;

import java.util.Map;

import br.com.edusoft.testejava.modules.http.IHttpServiceBase;

public interface IHttpPostService extends IHttpServiceBase {
	public IHttpPostService setBody(Map<String, Object> body);

	IHttpPostService addHeader(String key, String value);
}
