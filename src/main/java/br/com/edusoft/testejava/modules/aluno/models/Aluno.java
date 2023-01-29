package br.com.edusoft.testejava.modules.aluno.models;

import java.util.List;
import java.util.Objects;

public class Aluno {
	private int cod;
	private String nome;
	private int totalAulas;
	private List<Nota> nota;

	public Aluno() {}

	public Aluno(int cod, String nome, int totalAulas, List<Nota> nota) {
		this.cod = cod;
		this.nome = nome;
		this.totalAulas = totalAulas;
		this.nota = nota;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTotalAulas() {
		return totalAulas;
	}

	public void setTotalAulas(int totalAulas) {
		this.totalAulas = totalAulas;
	}

	public List<Nota> getNota() {
		return nota;
	}

	public void setNota(List<Nota> nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cod, nome, nota, totalAulas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return cod == other.cod && Objects.equals(nome, other.nome) && Objects.equals(nota, other.nota)
				&& totalAulas == other.totalAulas;
	}

	@Override
	public String toString() {
		return "Aluno [cod=" + cod + ", nome=" + nome + ", totalAulas=" + totalAulas + ", nota=" + nota + "]";
	}
}
