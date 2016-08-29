/**
 * 
 */
package pt.ec.talkers.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ecartaxo
 *
 */
public class TalkerTokenMissingException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5337458743117991374L;

	/**
	 * @param msg
	 */
	public TalkerTokenMissingException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
