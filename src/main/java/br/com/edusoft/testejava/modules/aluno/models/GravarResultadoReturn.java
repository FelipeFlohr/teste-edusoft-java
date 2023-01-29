package br.com.edusoft.testejava.modules.aluno.models;

import java.util.Objects;

public class GravarResultadoReturn {
	private String resultado;
	private int linhasAfetadas;

	public GravarResultadoReturn() {}

	public GravarResultadoReturn(String resultado, int linhasAfetadas) {
		this.resultado = resultado;
		this.linhasAfetadas = linhasAfetadas;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public int getLinhasAfetadas() {
		return linhasAfetadas;
	}

	public void setLinhasAfetadas(int linhasAfetadas) {
		this.linhasAfetadas = linhasAfetadas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(linhasAfetadas, resultado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GravarResultadoReturn other = (GravarResultadoReturn) obj;
		return linhasAfetadas == other.linhasAfetadas && Objects.equals(resultado, other.resultado);
	}

	@Override
	public String toString() {
		return "GravarResultadoReturn [resultado=" + resultado + ", linhasAfetadas=" + linhasAfetadas + "]";
	}
}
