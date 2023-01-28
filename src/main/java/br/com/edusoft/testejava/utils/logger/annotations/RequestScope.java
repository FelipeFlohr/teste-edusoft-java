package br.com.edusoft.testejava.utils.logger.annotations;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

public class RequestScope implements Scope {
	/**
	 * Container para dados da scope "Request". Isso garante que cada
	 * Thread tenha seu container separado uma das outras.
	 */
	private final ThreadLocal<Object> request = new ThreadLocal<Object>();

	public <T> Provider<T> scope(final Key<T> key, final Provider<T> unscoped) {
		return new Provider<T>() {
			@SuppressWarnings("unchecked")
			public T get() {
				Object requestData = request.get();

				// Cria um novo objeto chave-valor caso não exista na
				// Thread atual
				if (requestData == null) {
					requestData = new HashMap<Key<?>, Object>();
					request.set(requestData);
				}

				/**
				 * requestMap = requestData = Container da Thread atual
				 */
				Map<Key<?>, Object> requestMap = (Map<Key<?>, Object>) requestData;

				if (!requestMap.containsKey(key)) {
					requestMap.put(key, unscoped.get());
				}

				return (T) requestMap.get(key);
			}
		};
	}
}
