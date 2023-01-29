package br.com.edusoft.testejava.modules.aluno.enums;

import br.com.edusoft.testejava.modules.aluno.exceptions.InvalidAlunoStatusException;

public enum AlunoStatus {
	APROVADO, REPROVADO_MEDIA, REPROVADO_FREQUENCIA;

	/**
	 * Retorna o texto para ser enviado para a API
	 * @return APROVADO = AP; REPROVADO_MEDIA = RM; REPROVADO_FREQUENCIA = RF;
	 */
	public String getApiAliasText() {
		switch (this) {
			case APROVADO:
				return "AP";
			case REPROVADO_FREQUENCIA:
				return "RF";
			case REPROVADO_MEDIA:
				return "RM";
			default:
				throw new InvalidAlunoStatusException(this);
		}
	}
}
