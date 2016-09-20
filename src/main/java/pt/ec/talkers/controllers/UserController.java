/**
 * 
 */
package pt.ec.talkers.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.domain.services.UserService;

/**
 * @author ecartaxo
 *
 */
@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody @Valid @NotNull TalkerUser user) {
		userService.create(user);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TalkerUser> getUser(@PathVariable final Long id) {
		TalkerUser user = userService.getById(id);

		return new ResponseEntity<TalkerUser>(user, HttpStatus.OK);
	}

	@RequestMapping(path = "{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TalkerUser> getUser(@PathVariable final String username) {
		TalkerUser user = userService.getByUsername(username);

		return new ResponseEntity<TalkerUser>(user, HttpStatus.OK);
	}
}