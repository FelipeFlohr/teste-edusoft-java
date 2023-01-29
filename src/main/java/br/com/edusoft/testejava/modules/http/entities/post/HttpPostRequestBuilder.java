package br.com.edusoft.testejava.modules.http.entities.post;

import java.util.Map;

import com.google.gson.Gson;

import br.com.edusoft.testejava.modules.http.entities.AHttpRequestBuilder;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpPostRequestBuilder extends AHttpRequestBuilder {
	private RequestBody body;

	public HttpPostRequestBuilder(String url) {
		super(url);
	}

	public HttpPostRequestBuilder setBody(Map<String, Object> body) {
		Gson gson = new Gson();
		String json = gson.toJson(body);

		@SuppressWarnings("deprecation")
		RequestBody reqBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		this.body = reqBody;

		return this;
	}

	@Override
	protected Request buildRequest() {
		@SuppressWarnings("deprecation")
		final var finalRequest = this.body == null
				? this.request.url(url).post(RequestBody.create(null, new byte[] {})).build()
				: this.request.url(url).post(this.body).build();

		return finalRequest;
	}
}
