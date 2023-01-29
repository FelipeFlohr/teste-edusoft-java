package br.com.edusoft.testejava.modules.aluno.models;

import java.util.Objects;

import br.com.edusoft.testejava.modules.aluno.enums.AlunoStatus;

public class ResultadoAluno {
	private double media;
	private AlunoStatus resultado;
	private int codAluno;
	private String seuNome;

	public ResultadoAluno() {}

	public ResultadoAluno(double media, AlunoStatus resultado, int codAluno, String seuNome) {
		this.media = media;
		this.resultado = resultado;
		this.codAluno = codAluno;
		this.seuNome = seuNome;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public AlunoStatus getResultado() {
		return resultado;
	}

	public void setResultado(AlunoStatus resultado) {
		this.resultado = resultado;
	}

	public int getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(int codAluno) {
		this.codAluno = codAluno;
	}

	public String getSeuNome() {
		return seuNome;
	}

	public void setSeuNome(String seuNome) {
		this.seuNome = seuNome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codAluno, media, resultado, seuNome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoAluno other = (ResultadoAluno) obj;
		return codAluno == other.codAluno && Double.doubleToLongBits(media) == Double.doubleToLongBits(other.media)
				&& resultado == other.resultado && Objects.equals(seuNome, other.seuNome);
	}

	@Override
	public String toString() {
		return "ResultadoAluno [media=" + media + ", resultado=" + resultado + ", codAluno=" + codAluno + ", seuNome="
				+ seuNome + "]";
	}
}
