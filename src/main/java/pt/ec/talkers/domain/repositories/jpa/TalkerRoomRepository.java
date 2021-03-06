/**
 * 
 */
package pt.ec.talkers.domain.repositories.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.domain.entities.TalkerRoom;

/**
 * @author ecartaxo
 *
 */
@Profile(AppProfiles.DB_JPA)
public interface TalkerRoomRepository extends JpaRepository<TalkerRoom, Long> {
	public TalkerRoom findByName(final String name);
}
