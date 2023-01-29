package br.com.edusoft.testejava.utils.json;

import java.util.Map;

import com.google.gson.Gson;

public class JsonUtils {
	public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
		Gson gson = new Gson();
		String json = gson.toJson(map);

		T obj = gson.fromJson(json, clazz);
		return obj;
	}
}
