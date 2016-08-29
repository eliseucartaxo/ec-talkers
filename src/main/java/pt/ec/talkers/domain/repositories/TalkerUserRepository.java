/**
 * 
 */
package pt.ec.talkers.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pt.ec.talkers.domain.entities.TalkerUser;

/**
 * @author ecartaxo
 */
public interface TalkerUserRepository extends JpaRepository<TalkerUser, Long> {
	public TalkerUser findByUsername(final String username);
}
