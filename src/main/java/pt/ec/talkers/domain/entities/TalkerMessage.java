/**
 * 
 */
package pt.ec.talkers.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import pt.ec.talkers.domain.BaseEntity;

/**
 * @author ecartaxo
 *
 */
@Entity
public class TalkerMessage extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String message;

	@ManyToOne(optional = false)
	@JoinColumn(name = "author")
	private TalkerUser author;

	@ManyToOne(optional = false)
	@JoinColumn(name = "room")
	private TalkerRoom room;

	public TalkerMessage() {

	}

	public TalkerMessage(TalkerUser author, TalkerRoom room, String message) {
		this.author = author;
		this.message = message;
		this.room = room;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the author
	 */
	public TalkerUser getAuthor() {
		return author;
	}

	/**
	 * @return the room
	 */
	public TalkerRoom getRoom() {
		return room;
	}
}
