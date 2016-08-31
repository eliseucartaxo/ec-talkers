/**
 * 
 */
package pt.ec.talkers.domain.services;

import pt.ec.talkers.domain.entities.TalkerUser;

/**
 * @author ecartaxo
 *
 */

public interface UserService extends TalkerService<TalkerUser> {
	public TalkerUser getByUsername(final String username);

	public TalkerUser getById(final Long userId);

}
