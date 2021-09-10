package com.cndfactory.shoppingmall.utils.security.handler;

import com.cndfactory.shoppingmall.utils.response.ErrorCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ErrorCode errorCode =  ErrorCode.ACCESS_DENIED;
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
