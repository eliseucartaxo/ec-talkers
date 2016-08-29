/**
 * 
 */
package pt.ec.talkers.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import pt.ec.talkers.config.TalkerTokenKeys;
import pt.ec.talkers.security.exceptions.TalkerTokenMissingException;
import pt.ec.talkers.security.token.TalkerAuthenticationToken;
import pt.ec.talkers.security.token.TalkerTokenHandler;

/**
 * @author ecartaxo
 *
 */
public class TalkerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private TalkerTokenKeys tokenKeys;
	
	@Autowired
	private TalkerTokenHandler tokenHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String authToken = request.getHeader(tokenKeys.getTokenHeader());

		if (StringUtils.isEmpty(authToken)) {
			throw new TalkerTokenMissingException("No authorization token detected");
		}

		TalkerAuthenticationToken authRequest = new TalkerAuthenticationToken(authToken);

		return getAuthenticationManager().authenticate(authRequest);
	}

	/*
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String authToken = httpRequest.getHeader(tokenKeys.getTokenHeader());
	    String username = this.tokenHandler.getUsernameFromToken(authToken);

	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	      if (this.tokenHandler.validateToken(authToken, userDetails)) {
	        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	      }
	    }

	    chain.doFilter(request, response);
	}

	
}
