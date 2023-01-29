package br.com.edusoft.testejava.modules.aluno.models;

import java.util.Objects;

public class Nota {
	private int faltas;
	private double nota;

	public Nota(int faltas, double nota) {
		this.faltas = faltas;
		this.nota = nota;
	}

	public int getFaltas() {
		return faltas;
	}

	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(faltas, nota);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nota other = (Nota) obj;
		return faltas == other.faltas && Double.doubleToLongBits(nota) == Double.doubleToLongBits(other.nota);
	}

	@Override
	public String toString() {
		return "Nota [faltas=" + faltas + ", nota=" + nota + "]";
	}
}
