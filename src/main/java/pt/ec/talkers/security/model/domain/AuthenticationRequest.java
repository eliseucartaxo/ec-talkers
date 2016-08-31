/**
 * 
 */
package pt.ec.talkers.security.model.domain;

import pt.ec.talkers.domain.BaseEntity;

/**
 * @author ecartaxo
 *
 */
public class AuthenticationRequest extends BaseEntity {

	private String username;
	private String password;
	
	public AuthenticationRequest(){
		super();
	}
	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
}
