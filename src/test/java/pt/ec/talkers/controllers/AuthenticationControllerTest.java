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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.config.TalkerTokenKeys;
import pt.ec.talkers.security.model.domain.AuthenticationResponse;
import pt.ec.talkers.security.token.TalkerTokenHandler;
import pt.ec.talkers.utils.RequestBuilder;
import pt.ec.talkers.utils.TestConfig;

/**
 * @author ecartaxo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles(profiles = { AppProfiles.TEST })
public class AuthenticationControllerTest extends BaseControllerTests {
	@Autowired
	private TalkerTokenHandler tokenHandler;

	@Test
	public void authenticationWithInvalidCredencials() throws Exception {
		try {
			client.exchange("http://localhost:8080/auth/login", HttpMethod.POST,
					requestBuilder.buildRequestEntity(TestConfig.JOHNDOE_AUTHENTICATION_REQUEST), Void.class);
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
		}
	}

	@Test
	public void authenticationWithValidCredencials() throws Exception {

		try {
			ResponseEntity<AuthenticationResponse> responseEntity = client.exchange("http://localhost:8080/auth/login",
					HttpMethod.POST, requestBuilder.buildRequestEntity(TestConfig.USER_AUTHENTICATION_REQUEST),
					AuthenticationResponse.class);

			AuthenticationResponse authResponse = responseEntity.getBody();

			try {
				assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
			} catch (Exception e) {
				fail("Should returned Http 400 : Ok");
			}

			try {
				String token = authResponse.getToken();
				assertEquals("Invalid username in token response", TestConfig.USER_AUTHENTICATION_REQUEST.getUsername(),
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
		initializeToken();

		try {
			ResponseEntity<AuthenticationResponse> responseEntity = client.exchange(
					"http://localhost:8080/auth/refresh", HttpMethod.GET,
					requestBuilder.buildRequestEntityWithoutBody(token.getToken()), AuthenticationResponse.class);

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

}
