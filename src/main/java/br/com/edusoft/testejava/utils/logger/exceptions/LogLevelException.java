package br.com.edusoft.testejava.utils.logger.exceptions;

import br.com.edusoft.testejava.utils.logger.LogLevel;

public class LogLevelException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LogLevelException() {
		super("Um LogLevel que não existe foi chamado.");
	}

	public LogLevelException(LogLevel logLevel) {
		super("O LogLevel " + logLevel + " não existe no método \"getDisplayString()\".");
	}
}
