package br.com.edusoft.testejava.modules.http.entities;

import java.util.Map;

public interface IHttpRequestBuilder {
	public IHttpRequestBuilder addHeader(String key, String value);
	public IHttpRequestBuilder addHeaders(Map<String, String> headers);
	public void call();
	public Map<String, Object> callJsonResponse();
	public String callStringResponse();
}
