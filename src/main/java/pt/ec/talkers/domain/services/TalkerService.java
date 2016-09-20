/**
 * 
 */
package pt.ec.talkers.domain.services;

import pt.ec.talkers.domain.entities.TalkerRoom;

/**
 * @author ecartaxo
 *
 */
public interface TalkerService<T> {

	public T create(T entity);

	public void delete(T entity);

	public T getById(Long id);
}
