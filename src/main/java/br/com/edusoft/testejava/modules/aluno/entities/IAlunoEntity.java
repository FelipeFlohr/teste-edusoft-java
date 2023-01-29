package br.com.edusoft.testejava.modules.aluno.entities;

import br.com.edusoft.testejava.modules.aluno.enums.AlunoStatus;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;

public interface IAlunoEntity {
	public AlunoStatus getAlunoStatus(Aluno aluno);
	public double getMedia(Aluno aluno);
	public double getFrequencia(Aluno aluno);
}
