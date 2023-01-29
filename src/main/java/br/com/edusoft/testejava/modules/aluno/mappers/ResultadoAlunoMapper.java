package br.com.edusoft.testejava.modules.aluno.mappers;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import br.com.edusoft.testejava.modules.aluno.models.ResultadoAluno;

public class ResultadoAlunoMapper {
	public static Map<String, Object> toMap(ResultadoAluno resultadoAluno) {
		final Map<String, Object> map = new HashMap<>();

		final DecimalFormat decimalFormat = new DecimalFormat("#.00");
		final String parsedMedia = decimalFormat.format(resultadoAluno.getMedia()).replace(',', '.');
		final double media = Double.parseDouble(parsedMedia);

		map.put("MEDIA", media);
		map.put("RESULTADO", resultadoAluno.getResultado().getApiAliasText());
		map.put("COD_ALUNO", resultadoAluno.getCodAluno());
		map.put("SEU_NOME", resultadoAluno.getSeuNome());

		return map;
	}

	public static String toStringJson(ResultadoAluno resultadoAluno) {
		final Map<String, Object> map = toMap(resultadoAluno);
		Gson gson = new Gson();

		final String json = gson.toJson(map);
		return json;
	}
}
