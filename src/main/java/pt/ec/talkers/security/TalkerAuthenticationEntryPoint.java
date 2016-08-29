/**
 * 
 */
package pt.ec.talkers.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author ecartaxo
 *
 */
@Component
public class TalkerAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// Invoked when user tries to access a secured REST resource without
		// credencials

		// Just send a 401 Unauthorized - client must know what to do
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorizes");

	}

}
