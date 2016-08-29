/**
 * 
 */
package pt.ec.talkers.security.model.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pt.ec.talkers.domain.entities.TalkerUser;

/**
 * @author ecartaxo
 *
 */
public class TalkerAuthenticatedUser extends User {

	private static final long serialVersionUID = -1777170048967287929L;

	/**
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public TalkerAuthenticatedUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
