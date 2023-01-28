package br.com.edusoft.testejava.utils.logger;

public interface ILogger {
	/**
	 * Faz um Log de uma mensagem/objeto com o nível "INFO"
	 * @param msg Mensangem/objeto para fazer o log
	 */
	public void log(Object msg);
	/**
	 * Faz um log de uma mensagem/objeto com o nível passado
	 * @param msg Mensagem/objeto para se fazer o log
	 * @param level Nível do log
	 */
	public void log(Object msg, LogLevel level);
	/**
	 * Faz um log de uma mensagem no nível "OKAY"
	 * @param msg Mensagem/objeto para se fazer o log
	 */
	public void okay(Object msg);
	/**
	 * Faz um log de uma mensagem no nível "INFO"
	 * @param msg Mensagem/objeto para se fazer o log
	 */
	public void info(Object msg);
	/**
	 * Faz um log de uma mensagem no nível "WARN"
	 * @param msg Mensagem/objeto para se fazer o log
	 */
	public void warn(Object msg);
	/**
	 * Faz um log de uma mensagem no nível "ERROR"
	 * @param msg Mensagem/objeto para se fazer o log
	 */
	public void error(Object msg);
	/**
	 * Faz um log de uma mensagem no nível "FATAL"
	 * @param msg Mensagem/objeto para se fazer o log
	 */
	public void fatal(Object msg);
}
