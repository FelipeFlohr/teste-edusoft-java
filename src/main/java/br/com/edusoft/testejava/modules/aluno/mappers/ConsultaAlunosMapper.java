package br.com.edusoft.testejava.modules.aluno.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.ConsultaAlunos;

public class ConsultaAlunosMapper {
	public static ConsultaAlunos fromMap(Map<String, Object> map) {
		// Dados para persistir
		final String resultado = (String) map.get("resultado");
		final List<Aluno> alunos = new ArrayList<>();

		@SuppressWarnings("unchecked")
		final List<Map<String, Object>> rawAlunos = (List<Map<String, Object>>) map.get("alunos");
		rawAlunos.forEach(rawAluno -> alunos.add(AlunoMapper.fromMap(rawAluno)));

		final ConsultaAlunos instance = new ConsultaAlunos();
		instance.setResultado(resultado);
		instance.setAlunos(alunos);

		return instance;
	}
}
