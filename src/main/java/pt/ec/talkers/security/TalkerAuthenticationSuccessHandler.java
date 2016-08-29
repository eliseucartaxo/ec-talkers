/**
 * 
 */
package pt.ec.talkers.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author ecartaxo
 *
 */
public class TalkerAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	/*
	 * Overrid default behavior, avoid redirect to other url or something
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

	}
}
