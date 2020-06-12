package com.revature.exceptions;

public class HttpStatusException extends RuntimeException {
	private int statusCode;

	public HttpStatusException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
