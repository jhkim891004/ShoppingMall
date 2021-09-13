package com.cndfactory.shoppingmall.utils.response;

import lombok.Getter;

@Getter
public enum ErrorCode {
	/**
	 * CODE
	 * 400: 문법상 오류
	 * 403: 인증/인가 오류
	 * 404: Not Found 오류
	 * 405: 요청 메서드 오류
	 * 408: timeout
	 * 500: 서버 오류
	 */
	// Common
	INVALID_INPUT_VALUE(400, "E001", " Invalid Input Value"),
	INVALID_TYPE_VALUE(400, "E002", " Invalid Type Value"),
	METHOD_NOT_ALLOWED(405, "E003", " Invalid Method"),
	ENTITY_NOT_FOUND(400, "E004", " Entity Not Found"),
	INTERNAL_SERVER_ERROR(500, "E005", "Server Error"),
	MISSING_REQUEST_PARAM(400, "E006", "Missing Request Param"),
	INVALID_VALID_ANNOTATION(400, "E007", "Invalid Valid Annotation"),

	// Access
	ACCESS_DENIED(403, "S001", "Access is Denied"),
	EXPIRED_TOKEN(403, "S002", "Token is Expired"),
	NOT_FOUND_TOKEN(403, "S003", "Token Not Found"),
	INVALID_SIGNATURE(403, "S004", "Token Do Not Match"),


	// Validation
	USERNAME_NOT_FOUND(400, "M001", "Member Not Found"),
	USERNAME_IS_EXISTS(400, "M002", "MemberId is Exists"),
	PASSWORD_NOT_MATCH(400, "M003", "Passwords Do Not Match"),


	;

	private final int status;
	private final String code;
	private final String message;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
