package br.com.edusoft.testejava.modules.aluno.models;

import java.util.List;
import java.util.Objects;

public class GravarResultado {
	public List<ResultadoAluno> resultadoAluno;

	public GravarResultado(List<ResultadoAluno> resultadoAluno) {
		this.resultadoAluno = resultadoAluno;
	}

	public List<ResultadoAluno> getResultadoAluno() {
		return resultadoAluno;
	}

	public void setResultadoAluno(List<ResultadoAluno> resultadoAluno) {
		this.resultadoAluno = resultadoAluno;
	}

	@Override
	public int hashCode() {
		return Objects.hash(resultadoAluno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GravarResultado other = (GravarResultado) obj;
		return Objects.equals(resultadoAluno, other.resultadoAluno);
	}

	@Override
	public String toString() {
		return "GravarResultado [resultadoAluno=" + resultadoAluno + "]";
	}
}
