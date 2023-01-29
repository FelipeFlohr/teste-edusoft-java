package br.com.edusoft.testejava.modules.aluno.mappers;

import java.util.Map;

import br.com.edusoft.testejava.modules.aluno.models.Nota;

public class NotaMapper {
	public static Nota fromMap(Map<String, Object> map) {
		final int faltas = ((Double) map.get("FALTAS")).intValue();
		final Double nota = (Double) map.get("NOTA");

		final Nota notaInstance = new Nota(faltas, nota);
		return notaInstance;
	}
}
