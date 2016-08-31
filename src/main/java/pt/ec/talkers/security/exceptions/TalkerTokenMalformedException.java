/**
 * 
 */
package pt.ec.talkers.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ecartaxo
 *
 */
public class TalkerTokenMalformedException extends AuthenticationException {

	private static final long serialVersionUID = 5699985585654245929L;

	public TalkerTokenMalformedException(String message) {
		super(message);
	}
}
