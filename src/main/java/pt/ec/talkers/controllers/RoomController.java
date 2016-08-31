/**
 * 
 */
package pt.ec.talkers.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ec.talkers.config.MessageDestinations;
import pt.ec.talkers.domain.entities.TalkerRoom;
import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.domain.services.RoomService;
import pt.ec.talkers.domain.services.UserService;
import pt.ec.talkers.security.model.domain.TalkerAuthenticatedUser;

/**
 * @author ecartaxo
 *
 */
@RestController
@RequestMapping("rooms")
public class RoomController {
	@Autowired
	private SimpMessagingTemplate messageTemplate;
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createRoom(@RequestBody TalkerRoom room) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TalkerUser talkerUser = userService.getByUsername(user.getUsername());
		try {
			room.setOwner(talkerUser);
			roomService.create(room);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// @RequestMapping(path = "{id}", method = GET, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public ResponseEntity<TalkerRoom> getRoomById(@PathVariable Long id) {
	//
	// TalkerRoom room = roomService.getById(id);
	//
	// return new ResponseEntity<TalkerRoom>(room, HttpStatus.OK);
	// }

	@RequestMapping(path = "{name}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TalkerRoom> getRoomByName(@PathVariable String name) {
		TalkerRoom room = roomService.getByName(name);
		if (room != null) {
			return new ResponseEntity<>(room, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "{name}", method = DELETE)
	public ResponseEntity<Void> deleteRoom(@PathVariable final String roomName) {
		roomService.delete(roomName);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(path = "{roomName}/join", method = PUT)
	public ResponseEntity<Void> join(@PathVariable final String roomName) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TalkerUser talkerUser = userService.getByUsername(user.getUsername());
		roomService.addParticipant(roomName, talkerUser);
		messageTemplate.convertAndSend(MessageDestinations.ROOM_USERS(roomName), user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(path = "{roomName}/leave", method = PUT)
	public ResponseEntity<Void> leave(@PathVariable final String roomName,
			@AuthenticationPrincipal TalkerAuthenticatedUser user) {
		TalkerUser talkerUser = userService.getByUsername(user.getUsername());
		roomService.removeParticipant(roomName, talkerUser);
		messageTemplate.convertAndSend(MessageDestinations.ROOM_USERS(roomName), user);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
