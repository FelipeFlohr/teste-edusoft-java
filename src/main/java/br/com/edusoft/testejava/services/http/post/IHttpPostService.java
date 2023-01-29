package br.com.edusoft.testejava.services.http.post;

import java.util.Map;

import br.com.edusoft.testejava.services.http.IHttpServiceBase;

public interface IHttpPostService extends IHttpServiceBase {
	public IHttpPostService setBody(Map<String, Object> body);
}
