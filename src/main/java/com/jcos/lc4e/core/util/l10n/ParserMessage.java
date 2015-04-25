package com.jcos.lc4e.core.util.l10n;

import org.apache.log4j.Logger;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

public class ParserMessage {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ParserMessage.class);

	private MessageSource messages;

	public MessageSource getMessages() {
		return messages;
	}

	public void setMessages(MessageSource messages) {
		this.messages = messages;
	}

	public String L18N(String code, Locale locale, String... args) {
		String message = messages.getMessage(code, args, code, locale);
		logger.info(message);
		return message;
	}
}
