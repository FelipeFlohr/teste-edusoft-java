package br.com.edusoft.testejava.modules.aluno.models;

import java.util.List;
import java.util.Objects;

public class ConsultaAlunos {
	private String resultado;
	private List<Aluno> alunos;

	public ConsultaAlunos() {}

	public ConsultaAlunos(String resultado, List<Aluno> alunos) {
		this.resultado = resultado;
		this.alunos = alunos;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alunos, resultado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultaAlunos other = (ConsultaAlunos) obj;
		return Objects.equals(alunos, other.alunos) && Objects.equals(resultado, other.resultado);
	}

	@Override
	public String toString() {
		return "ConsultaAlunos [resultado=" + resultado + ", alunos=" + alunos + "]";
	}
}
