/**
 * 
 */
package pt.ec.talkers.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author ecartaxo
 *
 */
public class TalkerAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -4546556965322892853L;
	private String token;

	public TalkerAuthenticationToken(String token) {
		super(null, null);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
