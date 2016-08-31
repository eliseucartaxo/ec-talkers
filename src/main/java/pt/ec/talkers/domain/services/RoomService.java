/**
 * 
 */
package pt.ec.talkers.domain.services;

import pt.ec.talkers.domain.entities.TalkerRoom;
import pt.ec.talkers.domain.entities.TalkerUser;

/**
 * @author ecartaxo
 *
 */
public interface RoomService extends TalkerService<TalkerRoom> {
	public void addParticipant(String roomName, TalkerUser user);

	public void removeParticipant(String roomName, TalkerUser user);

	public TalkerRoom getByName(final String name);

	/**
	 * Find room by name and if found delete him.
	 * 
	 * @param name
	 */
	public void delete(final String name);

}
