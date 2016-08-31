/**
 * 
 */
package pt.ec.talkers.utils;

import pt.ec.talkers.security.model.domain.AuthenticationRequest;

/**
 * @author ecartaxo
 *
 */
public class TestConfig {
	public static final String ENDPOINT_BASE = "http://localhost:8080";
	public static final String ENDPOINT_ROOMS = ENDPOINT_BASE + "/rooms";
	public static final String ENDPOINT_ROOMS_GET = ENDPOINT_ROOMS + "/{name}";
	public static final String ENDPOINT_ROOMS_JOIN = ENDPOINT_ROOMS + "/{name}/join";
	public static final String ENDPOINT_ROOMS_LEAVE = ENDPOINT_ROOMS + "/{name}/leave";

	public static final AuthenticationRequest USER_AUTHENTICATION_REQUEST = new AuthenticationRequest("test", "abc123");

	public static final AuthenticationRequest ADMIN_AUTHENTICATION_REQUEST = new AuthenticationRequest("admin",
			"abc123");
	public static final AuthenticationRequest JOHNDOE_AUTHENTICATION_REQUEST = new AuthenticationRequest("johndoe",
			"xxxx");

	public static final String ROOM_UNKNOWN = "room-unknown";
	public static final String ROOM_EXISTS_GENERAL_1 = "room-general-1";
	public static final String ROOM_EXISTS_GENERAL_2 = "room-general-2";
	public static final String ROOM_EXISTS_GENERAL_NEW = "room-general-NEW";

}
