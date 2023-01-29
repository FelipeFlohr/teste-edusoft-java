package br.com.edusoft.testejava.modules.aluno.services;

import java.util.List;

import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultado;
import br.com.edusoft.testejava.modules.aluno.models.GravarResultadoReturn;

public interface IAlunoService {
	public GravarResultado gerarGravarResultado(List<Aluno> alunos);
	public GravarResultadoReturn postGravarResultado(GravarResultado gravarResultado);
	public String generateAlunoRelatorio(Aluno aluno);
	public String generateAlunosRelatorio(List<Aluno> alunos);
}
