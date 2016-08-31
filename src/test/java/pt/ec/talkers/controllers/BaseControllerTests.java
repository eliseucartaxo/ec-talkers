/**
 * 
 */
package pt.ec.talkers.controllers;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import pt.ec.talkers.config.TalkerTokenKeys;
import pt.ec.talkers.security.model.domain.AuthenticationResponse;
import pt.ec.talkers.security.token.TalkerAuthenticationToken;
import pt.ec.talkers.utils.RequestBuilder;
import pt.ec.talkers.utils.TestConfig;

/**
 * @author ecartaxo
 *
 */
public class BaseControllerTests {
	protected TalkerAuthenticationToken token;
	protected RestTemplate client;
	protected RequestBuilder requestBuilder;
	@Autowired
	protected TalkerTokenKeys tokenKeys;

	@Before
	public void setUp() {
		client = new RestTemplate();
		requestBuilder = new RequestBuilder(tokenKeys);
	}

	@After
	public void shutdown() {
		client = null;
	}

	/**
	 * Generate new valid token to make authenticated requests Note : request
	 * using {@link}TestConfig.USER_AUTHENTICATION_REQUEST
	 */
	protected void initializeToken() {

		ResponseEntity<AuthenticationResponse> authenticationResponse = client.postForEntity(
				"http://localhost:8080/auth/login", TestConfig.USER_AUTHENTICATION_REQUEST,
				AuthenticationResponse.class);

		token = new TalkerAuthenticationToken(authenticationResponse.getBody().getToken());
	}
}
