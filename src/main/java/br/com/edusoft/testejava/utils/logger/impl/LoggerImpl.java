package br.com.edusoft.testejava.utils.logger.impl;

import java.util.Calendar;
import java.util.Date;

import com.google.inject.Singleton;

import br.com.edusoft.testejava.utils.logger.ILogger;
import br.com.edusoft.testejava.utils.logger.LogLevel;

@Singleton
public class LoggerImpl implements ILogger {
	public void log(Object msg) {
		log(msg, LogLevel.INFO);
	}

	public void log(Object msg, LogLevel level) {
		final String timestamp = this.getTimestampString();
		final String formatted = level.getDisplayText() + " | " + timestamp + " | " + msg.toString();

		System.out.println(formatted);
	}

	public void okay(Object msg) {
		log(msg, LogLevel.OKAY);
	}

	public void info(Object msg) {
		log(msg, LogLevel.INFO);
	}

	public void warn(Object msg) {
		log(msg, LogLevel.WARN);
	}

	public void error(Object msg) {
		log(msg, LogLevel.ERROR);
	}

	public void fatal(Object msg) {
		log(msg, LogLevel.FATAL);
	}

	private String getTimestampString() {
		final Calendar calendar = Calendar
				.getInstance();
		calendar.setTime(new Date());

		final int day = calendar.get(Calendar.DATE);
		final int month = calendar.get(Calendar.MONTH) + 1;
		final int year = calendar.get(Calendar.YEAR);
		final int hours = calendar.get(Calendar.HOUR);
		final int minutes = calendar.get(Calendar.MINUTE);
		final int seconds = calendar.get(Calendar.SECOND);

		final String parsedDay = day < 10 ? "0" + day : Integer.toString(day);
		final String parsedMonth = month < 10 ? "0" + month : Integer.toString(month);
		final String parsedHours = hours < 10 ? "0" + hours : Integer.toString(hours);
		final String parsedMinutes = minutes < 10 ? "0" + minutes : Integer.toString(minutes);
		final String parsedSeconds = seconds < 10 ? "0" + seconds : Integer.toString(seconds);

		final String formatted = new StringBuilder()
				.append(parsedDay)
				.append("/")
				.append(parsedMonth)
				.append("/")
				.append(year)
				.append(" ")
				.append(parsedHours)
				.append(":")
				.append(parsedMinutes)
				.append(":")
				.append(parsedSeconds)
				.toString();

		return formatted;
	}
}
