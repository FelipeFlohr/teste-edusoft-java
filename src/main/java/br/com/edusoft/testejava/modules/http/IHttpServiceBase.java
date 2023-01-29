package br.com.edusoft.testejava.modules.http;

import java.util.Map;

public interface IHttpServiceBase {
	public IHttpServiceBase addHeader(String key, String value);
	public IHttpServiceBase addHeaders(Map<String, String> headers);
	public void call(String url);
	public Map<String, Object> callJsonResponse(String url);
	public String callStringResponse(String url);
}
