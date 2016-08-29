/**
 * 
 */
package pt.ec.talkers.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.config.TalkerTokenKeys;
import pt.ec.talkers.security.model.domain.AuthenticationRequest;
import pt.ec.talkers.security.model.domain.AuthenticationResponse;
import pt.ec.talkers.security.token.TalkerTokenHandler;

/**
 * @author ecartaxo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles(profiles = { AppProfiles.TEST })
public class AuthenticationControllerTest {
	@Autowired
	private TalkerTokenHandler tokenHandler;
	@Autowired
	private TalkerTokenKeys tokenKeys;

	private RestTemplate client;
	private String authenticationToken;

	@Before
	public void setup() {
		client = new RestTemplate();
	}

	@After
	public void shutdown() {
		client = null;
	}

	@Test
	public void authenticationWithInvalidCredencials() throws Exception {
		AuthenticationRequest authRequest = new AuthenticationRequest("johndoe", "johndoe");

		try {
			client.exchange("http://localhost:8080/auth/login", HttpMethod.POST, buildRequestEntity(authRequest),
					Void.class);
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
		}
	}

	@Test
	public void authenticationWithValidCredencials() throws Exception {
		AuthenticationRequest authRequest = new AuthenticationRequest("test", "abc123");

		try {
			ResponseEntity<AuthenticationResponse> responseEntity = client.exchange("http://localhost:8080/auth/login",
					HttpMethod.POST, buildRequestEntity(authRequest), AuthenticationResponse.class);

			AuthenticationResponse authResponse = responseEntity.getBody();

			try {
				assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			} catch (Exception e) {
				fail("Should returned Http 400 : Ok");
			}

			try {
				String token = authResponse.getToken();
				assertEquals("Invalid username in token response", authRequest.getUsername(),
						tokenHandler.getUsernameFromToken(token));
			} catch (Exception e) {
				fail("Invalid username in token response");
			}

		} catch (HttpClientErrorException e) {
			fail("Should returned Http 400 : Ok");

		}
	}

	@Test
	public void refreshToken() {
		initializeStateForMakingValidAuthenticationRefreshRequest();

		try {
			ResponseEntity<AuthenticationResponse> responseEntity = client.exchange(
					"http://localhost:8080/auth/refresh", HttpMethod.GET,
					buildRequestEntityWithoutBody(authenticationToken), AuthenticationResponse.class);

			AuthenticationResponse authResponse = responseEntity.getBody();

			try {
				assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			} catch (Exception e) {
				fail("Should returned Http 400 : Ok");
			}
		} catch (HttpClientErrorException e) {
			fail("Should returned Http 400 : Ok");

		}
	}

	private void initializeStateForMakingValidAuthenticationRefreshRequest() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("test", "abc123");

		ResponseEntity<AuthenticationResponse> authenticationResponse = client
				.postForEntity("http://localhost:8080/auth/login", authenticationRequest, AuthenticationResponse.class);

		authenticationToken = authenticationResponse.getBody().getToken();
	}

	private HttpEntity<?> buildRequestEntity(String authenticationToken, Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(tokenKeys.getTokenHeader(), authenticationToken);
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		return entity;
	}

	private HttpEntity<Object> buildRequestEntityWithoutBody(String authenticationToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(tokenKeys.getTokenHeader(), authenticationToken);
		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		return entity;
	}

	private HttpEntity<?> buildRequestEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		return entity;
	}
}
