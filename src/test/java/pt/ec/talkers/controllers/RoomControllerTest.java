/**
 * 
 */
package pt.ec.talkers.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.http.HttpMethod.*;
import static pt.ec.talkers.utils.TestConfig.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.domain.entities.TalkerRoom;
import pt.ec.talkers.domain.services.RoomService;
import pt.ec.talkers.domain.services.UserService;

/**
 * @author ecartaxo
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles(profiles = { AppProfiles.TEST, AppProfiles.DB_JPA })
public class RoomControllerTest extends BaseControllerTests {
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;

	@Override
	public void setUp() {
		super.setUp();
		initializeToken();
	}

	private TalkerRoom getNewRoom() {
		TalkerRoom room = new TalkerRoom();
		room.setName(ROOM_EXISTS_GENERAL_NEW);
		room.setDescription("None");
		room.setOwner(userService.getByUsername(USER_AUTHENTICATION_REQUEST.getUsername()));

		return room;
	}

	@Test
	public void test_createRoom_Unauthenticated() {

		try {
			client.exchange(ENDPOINT_ROOMS, POST, requestBuilder.buildRequestEntity(getNewRoom()), Void.class);
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode(), is(HttpStatus.UNAUTHORIZED));
		}
	}

	@Test
	public void testCreateRoom_Authenticated() {

		try {
			ResponseEntity<Void> responseEntity = client.exchange(ENDPOINT_ROOMS, POST,
					requestBuilder.buildRequestEntity(token.getToken(), getNewRoom()), Void.class);

			assertThat(responseEntity.getStatusCode(), is(HttpStatus.CREATED));

		} catch (HttpClientErrorException e) {
			fail("Room must be created");
		}
	}

	@Test
	public void testExistsRoom() {

		try {
			ResponseEntity<TalkerRoom> responseEntity = client.exchange(ENDPOINT_ROOMS_GET, GET,
					requestBuilder.buildRequestEntityWithoutBody(token.getToken()), TalkerRoom.class,
					new Object[] { ROOM_EXISTS_GENERAL_1 });

			TalkerRoom room = responseEntity.getBody();
			assertThat(room.getName(), is(ROOM_EXISTS_GENERAL_1));

		} catch (HttpClientErrorException e) {
			fail("Room not exists " + e.getMessage());
		}

	}

	@Test
	public void getRoomNotExists() {

		try {
			client.exchange(ENDPOINT_ROOMS_GET, GET, requestBuilder.buildRequestEntityWithoutBody(token.getToken()),
					Void.class, new Object[] { ROOM_UNKNOWN });

		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
		}
	}

	@Test
	public void join_Room_Unknow() {

		try {
			ResponseEntity<Void> responseEntity = client.exchange(ENDPOINT_ROOMS_JOIN, PUT,
					requestBuilder.buildRequestEntityWithoutBody(token.getToken()), Void.class,
					new Object[] { ROOM_UNKNOWN });

			HttpStatus status = responseEntity.getStatusCode();
			assertThat(status, is(HttpStatus.OK));

		} catch (HttpClientErrorException e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void join_Room_General() {

		try {
			ResponseEntity<Void> responseEntity = client.exchange(ENDPOINT_ROOMS_JOIN, PUT,
					requestBuilder.buildRequestEntityWithoutBody(token.getToken()), Void.class,
					new Object[] { ROOM_EXISTS_GENERAL_1 });

			HttpStatus status = responseEntity.getStatusCode();
			assertThat(status, is(HttpStatus.OK));

			TalkerRoom room = roomService.getByName(ROOM_EXISTS_GENERAL_1);

			assertTrue(room.getParticipants()
					.contains(userService.getByUsername(USER_AUTHENTICATION_REQUEST.getUsername())));

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
