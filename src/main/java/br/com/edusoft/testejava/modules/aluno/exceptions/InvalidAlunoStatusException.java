package br.com.edusoft.testejava.modules.aluno.exceptions;

import br.com.edusoft.testejava.modules.aluno.enums.AlunoStatus;

public class InvalidAlunoStatusException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidAlunoStatusException() {
		super("Status do Aluno inválido");
	}

	public InvalidAlunoStatusException(AlunoStatus status) {
		super("Status do Aluno inválido. Status: " + status);
	}
}
