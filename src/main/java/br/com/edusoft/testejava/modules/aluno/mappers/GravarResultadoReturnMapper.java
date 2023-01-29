package br.com.edusoft.testejava.modules.aluno.mappers;

import java.util.Map;

import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;

public class GravarResultadoReturnMapper {
	public static GravarResultadoReturn fromMap(Map<String, Object> map) {
		final String resultado = (String) map.get("resultado");
		final int linhasAfetadas = ((Double) map.get("linhasAfetadas")).intValue();

		final GravarResultadoReturn gravarResultadoReturn = new GravarResultadoReturn();
		gravarResultadoReturn.setLinhasAfetadas(linhasAfetadas);
		gravarResultadoReturn.setResultado(resultado);
		return gravarResultadoReturn;
	}
}
