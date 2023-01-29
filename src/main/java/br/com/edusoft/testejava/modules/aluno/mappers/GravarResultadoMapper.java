package br.com.edusoft.testejava.modules.aluno.mappers;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;

public class GravarResultadoMapper {
	public static Map<String, Object> toMap(GravarResultado gravarResultado) {
		final Map<String, Object> map = new HashMap<>();
		final var resultadoAlunosParsed = gravarResultado
				.getResultadoAluno()
				.stream()
				.map(res -> ResultadoAlunoMapper.toMap(res))
				.toList();

		map.put("resultadoAluno", resultadoAlunosParsed);
		return map;
	}

	public static String toStringJson(GravarResultado gravarResultado) {
		final Map<String, Object> map = toMap(gravarResultado);
		Gson gson = new Gson();

		final String json = gson.toJson(map);
		return json;
	}
}
