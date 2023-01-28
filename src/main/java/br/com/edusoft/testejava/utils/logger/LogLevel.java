package br.com.edusoft.testejava.utils.logger;

import br.com.edusoft.testejava.utils.logger.exceptions.LogLevelException;

public enum LogLevel {
	OKAY,
	INFO,
	WARN,
	ERROR,
	FATAL;

	/**
	 * Obtém o texto para amostra no System.out.print
	 * @return Texto de amostra para ser printado
	 */
	public String getDisplayText() {
		switch (this) {
			case OKAY:
				return "OKAY";
			case INFO:
				return "INFO";
			case WARN:
				return "WARN";
			case ERROR:
				return "ERROR";
			case FATAL:
				return "FATAL";
			default:
				throw new LogLevelException(this);
		}
	}
}
