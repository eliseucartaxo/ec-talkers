/**
 * 
 */
package pt.ec.talkers.config;

/**
 * @author ecartaxo
 *
 */
public class MessageDestinations {
	public static final String ENDPOINT = "/talker";
	public static final String BROKER_TARGET = "/topic";
	public static final String APP_TARGET = "/app";

	private final static String ROOM_USERS_UNFORMATED = BROKER_TARGET + "/%s/users";
	private final static String ROOM_MESSAGES_UNFORMATED = BROKER_TARGET + "/%s/messages";

	public static String ROOM_USERS(final String room) {
		return String.format(ROOM_USERS_UNFORMATED, room);
	}

	public static String ROOM_MESSAGES(final String room) {
		return String.format(ROOM_MESSAGES_UNFORMATED, room);
	}

}
