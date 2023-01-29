package br.com.edusoft.testejava.modules.aluno.entities.impl;

import br.com.edusoft.testejava.modules.aluno.entities.IAlunoEntity;
import br.com.edusoft.testejava.modules.aluno.enums.AlunoStatus;
import br.com.edusoft.testejava.modules.aluno.models.Aluno;
import br.com.edusoft.testejava.modules.aluno.models.Nota;

public class AlunoEntityImpl implements IAlunoEntity {
	@Override
	public AlunoStatus getAlunoStatus(Aluno aluno) {
		final double frequencia = getFrequencia(aluno);

		if (frequencia < 70) {
			return AlunoStatus.REPROVADO_FREQUENCIA;
		}

		final double media = getMedia(aluno);
		return media < 7 ? AlunoStatus.REPROVADO_MEDIA : AlunoStatus.APROVADO;
	}

	@Override
	public double getMedia(Aluno aluno) {
		final int totalAulas = aluno.getTotalAulas();
		final double notasAgregadas = aluno.getNota()
				.stream()
				.map(nota -> nota.getNota())
				.reduce((prev, curr) -> prev + curr)
				.get();

		final double media = notasAgregadas / totalAulas;
		return media;
	}

	@Override
	public double getFrequencia(Aluno aluno) {
		final int totalAulas = aluno.getTotalAulas();
		int totalFaltas = 0;

		// Obtém o total de faltas
		for (Nota nota : aluno.getNota()) {
			totalFaltas += nota.getFaltas();
		}

		final double frequencia = 100 - ((100 * totalFaltas) / totalAulas);
		return frequencia;
	}
}
