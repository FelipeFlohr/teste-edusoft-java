package br.com.edusoft.testejava.modules.http.entities.get;

import br.com.edusoft.testejava.modules.http.entities.AHttpRequestBuilder;
import okhttp3.Request;

public class HttpGetRequestBuilder extends AHttpRequestBuilder {
	public HttpGetRequestBuilder(String url) {
		super(url);
	}

	@Override
	protected Request buildRequest() {
		final var res = this.request
				.url(url)
				.build();
		return res;
	}
}
