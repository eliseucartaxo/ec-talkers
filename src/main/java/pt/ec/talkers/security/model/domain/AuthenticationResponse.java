/**
 * 
 */
package pt.ec.talkers.security.model.domain;

import pt.ec.talkers.domain.BaseEntity;

/**
 * @author ecartaxo
 *
 */
public class AuthenticationResponse extends BaseEntity {

	private String token;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(final String token) {
		this.token = token;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
}
