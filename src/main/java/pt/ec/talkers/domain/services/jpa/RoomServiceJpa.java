/**
 * 
 */
package pt.ec.talkers.domain.services.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import pt.ec.talkers.config.AppProfiles;
import pt.ec.talkers.domain.entities.TalkerRoom;
import pt.ec.talkers.domain.entities.TalkerUser;
import pt.ec.talkers.domain.repositories.jpa.TalkerRoomRepository;
import pt.ec.talkers.domain.services.RoomService;

/**
 * @author ecartaxo
 *
 */
@Service
@Profile(AppProfiles.DB_JPA)
public class RoomServiceJpa implements RoomService {
	@Autowired
	private TalkerRoomRepository roomRepository;

	@Override
	public void create(TalkerRoom entity) {
		roomRepository.save(entity);
	}

	@Override
	public void delete(TalkerRoom entity) {
		roomRepository.delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pt.ec.talkers.domain.services.RoomService#delete(java.lang.String)
	 */
	@Override
	public void delete(String name) {
		TalkerRoom room = getByName(name);
		if (room != null) {
			delete(room);
		}
	}

	@Override
	public TalkerRoom getById(Long id) {
		return roomRepository.findOne(id);
	}

	@Override
	public TalkerRoom getByName(String name) {
		return roomRepository.findByName(name);
	}

	@Override
	public void addParticipant(String roomName, TalkerUser user) {
		TalkerRoom room = roomRepository.findByName(roomName);

		if (room != null) {
			room.getParticipants().add(user);
			roomRepository.save(room);
		}
	}

	@Override
	public void removeParticipant(String roomName, TalkerUser user) {
		TalkerRoom room = roomRepository.findByName(roomName);

		if (room != null) {
			room.getParticipants().remove(user);
			roomRepository.save(room);
		}

	}

}
