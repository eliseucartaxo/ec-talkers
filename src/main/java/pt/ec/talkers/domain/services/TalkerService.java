/**
 * 
 */
package pt.ec.talkers.domain.services;

/**
 * @author ecartaxo
 *
 */
public interface TalkerService<T> {

	public void create(T entity);

	public void delete(T entity);

	public T getById(Long id);
}
