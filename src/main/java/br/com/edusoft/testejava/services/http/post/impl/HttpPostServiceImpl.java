package br.com.edusoft.testejava.services.http.post.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.com.edusoft.testejava.services.http.exceptions.CalledOnFinishedRequestException;
import br.com.edusoft.testejava.services.http.exceptions.FailedHttpRequestException;
import br.com.edusoft.testejava.services.http.exceptions.FailedToParseBodyException;
import br.com.edusoft.testejava.services.http.post.IHttpPostService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpPostServiceImpl implements IHttpPostService {
	private final OkHttpClient client;
	private final Builder request;
	private RequestBody body;
	private boolean finished;

	HttpPostServiceImpl() {
		this.finished = false;
		this.client = new OkHttpClient();
		this.request = new Request.Builder();
	}

	@Override
	public IHttpPostService addHeader(String key, String value) {
		request.addHeader(key, value);
		return this;
	}

	@Override
	public IHttpPostService addHeaders(Map<String, String> headers) {
		headers.forEach((k, v) -> {
			this.addHeader(k, v);
		});
		return this;
	}

	@Override
	public IHttpPostService setBody(Map<String, Object> body) {
		Gson gson = new Gson();
		String json = gson.toJson(body);

		@SuppressWarnings("deprecation")
		RequestBody reqBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
		this.body = reqBody;

		return this;
	}

	@Override
	public void call(String url) {
		callHandler(url);
	}

	@Override
	public Map<String, Object> callJsonResponse(String url) {
		final var response = callHandler(url);

		final var body = response.body();
		try {
			final String stringJson = body.string();

			Gson gson = new Gson();
			final Type type = new TypeToken<Map<String, Object>>() {
				private static final long serialVersionUID = 1L;
			}.getType();

			Map<String, Object> map = gson.fromJson(stringJson, type);
			return map;
		} catch (IOException e) {
			throw new FailedToParseBodyException(url, body);
		}
	}

	@Override
	public String callStringResponse(String url) {
		final var response = callHandler(url);

		final var body = response.body();
		try {
			final String string = body.string();

			return string;
		} catch (IOException e) {
			throw new FailedToParseBodyException(url, body);
		}
	}

	private Response callHandler(String url) {
		if (finished) {
			throw new CalledOnFinishedRequestException(url);
		}

		@SuppressWarnings("deprecation")
		final var finalRequest = this.body == null
				? this.request.url(url).post(RequestBody.create(null, new byte[] {})).build()
				: this.request.url(url).post(this.body).build();

		try {
			Response response = client.newCall(finalRequest).execute();
			if (!response.isSuccessful()) {
				throw new FailedHttpRequestException(url, response.code());
			}

			return response;
		} catch (IOException e) {
			throw new FailedHttpRequestException(url);
		} finally {
			this.finished = true;
		}
	}
}
