/**
 * 
 */
package pt.ec.talkers.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ec.talkers.config.TalkerTokenKeys;
import pt.ec.talkers.security.model.domain.AuthenticationRequest;
import pt.ec.talkers.security.model.domain.AuthenticationResponse;
import pt.ec.talkers.security.token.TalkerTokenHandler;

/**
 * @author ecartaxo
 *
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TalkerTokenHandler talkerTokenHandler;

	@Autowired
	private TalkerTokenKeys tokenKeys;

	@RequestMapping(path = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)
			throws AuthenticationException {
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails user = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = talkerTokenHandler.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@RequestMapping(path = "refresh", method = RequestMethod.GET)
	public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request) {
		// TODO code to refresh token
		String token = request.getHeader(tokenKeys.getTokenHeader());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
}
