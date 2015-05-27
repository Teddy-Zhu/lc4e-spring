package com.jcos.lc4e.core.entity;

public class Message {

	private boolean result;

	private String message;

	private Object data;

	public Message(boolean result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}

	public Message(boolean result, String message) {
		this.result = result;
		this.message = message;
	}

	public Message(boolean result, Object data) {
		this.result = result;
		this.data = data;
	}

	public Message(String message) {
		this.result = false;
		this.message = message;
		this.data = null;
	}

	public Message() {
		this.result = false;
		this.message = "";
		this.data = null;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
