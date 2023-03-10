package br.com.edusoft.testejava.modules.http.entities;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.com.edusoft.testejava.modules.http.exceptions.CalledOnFinishedRequestException;
import br.com.edusoft.testejava.modules.http.exceptions.FailedHttpRequestException;
import br.com.edusoft.testejava.modules.http.exceptions.FailedToParseBodyException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public abstract class AHttpRequestBuilder implements IHttpRequestBuilder {
	protected final Map<String, String> headers;
	protected final Builder request;
	protected final String url;
	private final OkHttpClient client;
	private boolean finished;

	public AHttpRequestBuilder(String url) {
		this.headers = new HashMap<>();
		this.url = url;
		this.client = new OkHttpClient();
		this.request = new Request.Builder();
		this.finished = false;
	}

	@Override
	public AHttpRequestBuilder addHeader(String key, String value) {
		this.headers.put(key, value);
		return this;
	}

	@Override
	public AHttpRequestBuilder addHeaders(Map<String, String> headers) {
		this.headers.putAll(headers);
		return this;
	}

	@Override
	public void call() {
		callHandler();
	}

	@Override
	public Map<String, Object> callJsonResponse() {
		final var response = callHandler();
		final var body = response.body();

		try {
			final String stringJson = body.string();

			Gson gson = new Gson();

			if (stringJson.startsWith("[")) { // Handler para arrays
				final Type type = new TypeToken<List<Map<String, Object>>>(){
					private static final long serialVersionUID = 1L;
				}.getType();

				List<Map<String, Object>> map = gson.fromJson(stringJson, type);
				return map.get(0);
			} else {
				final Type type = new TypeToken<Map<String, Object>>() {
					private static final long serialVersionUID = 1L;
				}.getType();

				Map<String, Object> map = gson.fromJson(stringJson, type);
				return map;
			}
		} catch (IOException e) {
			throw new FailedToParseBodyException(url, body);
		}
	}

	@Override
	public String callStringResponse() {
		final var response = callHandler();

		final var body = response.body();
		try {
			final String string = body.string();

			return string;
		} catch (IOException e) {
			throw new FailedToParseBodyException(url, body);
		}
	}

	protected abstract Request buildRequest();

	private Response callHandler() {
		if (finished) {
			throw new CalledOnFinishedRequestException(url);
		}

		this.headers.forEach((k, v) -> {
			this.request.addHeader(k, v);
		});

		final var finalRequest = this.buildRequest();

		try {
			Response response = this.client.newCall(finalRequest).execute();
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
