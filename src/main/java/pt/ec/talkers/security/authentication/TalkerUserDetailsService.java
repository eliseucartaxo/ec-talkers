/**
 * 
 */
package pt.ec.talkers.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.domain.repositories.TalkerUserRepository;
import pt.ec.talkers.security.model.factory.TalkerAuthenticatedBuilder;

/**
 * @author ecartaxo
 *
 */
@Service
public class TalkerUserDetailsService implements UserDetailsService {
	@Autowired
	TalkerUserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TalkerUser talkerUser = repository.findByUsername(username);
		if (talkerUser == null) {
			throw new UsernameNotFoundException(String.format("User [%s] not found", username));
		}

		return TalkerAuthenticatedBuilder.get(talkerUser);
	}

}
