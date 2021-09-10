package com.cndfactory.shoppingmall.utils.exception;

import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;

public class NoSuchJwtTokenException extends BusinessException {
	ErrorCode errorCode;
	public NoSuchJwtTokenException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public NoSuchJwtTokenException(ErrorCode errorCode) {
		super(errorCode);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
