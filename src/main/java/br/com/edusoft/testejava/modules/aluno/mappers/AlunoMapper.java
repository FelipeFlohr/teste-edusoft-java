package br.com.edusoft.testejava.modules.aluno.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.Nota;

public class AlunoMapper {
	public static Aluno fromMap(Map<String, Object> map) {
		final int cod = ((Double) map.get("COD")).intValue();
		final String nome = (String) map.get("NOME");
		final int totalAulas = ((Double) map.get("TOTAL_AULAS")).intValue();
		final List<Nota> notas = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final List<Map<String, Object>> rawNotas = (List<Map<String, Object>>) map.get("nota");
		rawNotas.forEach(nota -> notas.add(NotaMapper.fromMap(nota)));

		final Aluno alunoInstance = new Aluno();
		alunoInstance.setCod(cod);
		alunoInstance.setNome(nome);
		alunoInstance.setTotalAulas(totalAulas);
		alunoInstance.setNota(notas);

		return alunoInstance;
	}
}
