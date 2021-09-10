package com.cndfactory.shoppingmall.utils.security.handler;

import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class WebAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		String exception = (String) request.getAttribute("exception");
		ErrorCode errorCode;

		if(exception == null) {
			errorCode =  ErrorCode.NOT_FOUND_TOKEN;
			this.setResponse(response, errorCode);
			return;
		}

		if(exception.equals("E007")) {
			errorCode =  ErrorCode.EXPIRED_TOKEN;
		}
		else {
			errorCode =  ErrorCode.INVALID_SIGNATURE;
		}
		this.setResponse(response, errorCode);

	}

	private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().println(
				"{" +
				"	\"message\" : \"" + errorCode.getMessage() + "\"," +
				"	\"code\" : \"" +  errorCode.getCode() + "\"," +
				"	\"status\" : " + errorCode.getStatus() + "\",\n" +
				"	\"errors\" : []\n" +
				"}"
		);
	}
}
