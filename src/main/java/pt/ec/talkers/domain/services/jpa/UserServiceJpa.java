/**
 * 
 */
package pt.ec.talkers.domain.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.domain.repositories.jpa.TalkerUserRepository;
import pt.ec.talkers.domain.services.UserService;

/**
 * @author ecartaxo
 *
 */
@Service
@Profile(AppProfiles.DB_JPA)
public class UserServiceJpa implements UserService {
	@Autowired
	private TalkerUserRepository userRepository;

	@Override
	public TalkerUser create(TalkerUser entity) {
		return userRepository.save(entity);
	}

	@Override
	public void delete(TalkerUser entity) {
		userRepository.delete(entity);

	}

	@Override
	public TalkerUser getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public TalkerUser getById(Long userId) {
		return userRepository.findOne(userId);
	}

}
