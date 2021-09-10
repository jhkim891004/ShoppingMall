package com.cndfactory.shoppingmall.utils.exception;

import com.cndfactory.shoppingmall.utils.response.ErrorCode;

public class CustomExpiredJwtException extends BusinessException {
	ErrorCode errorCode;

	public CustomExpiredJwtException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public CustomExpiredJwtException(ErrorCode errorCode) {
		super(errorCode);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
