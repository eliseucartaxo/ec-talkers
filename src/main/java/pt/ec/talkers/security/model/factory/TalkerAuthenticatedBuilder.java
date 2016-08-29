/**
 * 
 */
package pt.ec.talkers.security.model.factory;

import org.springframework.security.core.authority.AuthorityUtils;

import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.security.model.domain.TalkerAuthenticatedUser;

/**
 * @author ecartaxo
 *
 */
public class TalkerAuthenticatedBuilder {
	public static TalkerAuthenticatedUser get(TalkerUser user) {
		return new TalkerAuthenticatedUser(user.getUsername(), user.getPassword(), true, true, true, true,
				AuthorityUtils.NO_AUTHORITIES);
	}

}
