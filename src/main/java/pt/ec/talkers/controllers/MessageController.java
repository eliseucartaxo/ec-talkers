/**
 * 
 */
package pt.ec.talkers.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.ec.talkers.config.MessageDestinations;
import pt.ec.talkers.security.model.domain.TalkerAuthenticatedUser;

/**
 * @author ecartaxo
 *
 */
@RestController
@RequestMapping("messages")
public class MessageController {
	@Autowired
	private SimpMessagingTemplate messageTemplate;

	@RequestMapping(value = "/{roomName}", method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> send(@PathVariable String roomName, @RequestBody String message,
			@AuthenticationPrincipal TalkerAuthenticatedUser user) {
		messageTemplate.convertAndSend(MessageDestinations.ROOM_MESSAGES(roomName), message);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}