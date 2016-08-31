/**
 * 
 */
package pt.ec.talkers.domain.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author ecartaxo
 *
 */
@Entity
public class TalkerRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	private TalkerUser owner;

	@OneToMany
	private Set<TalkerUser> participants;

	public TalkerRoom() {

	}

	public TalkerRoom(String name, String description, TalkerUser owner) {
		this.name = name;
		this.description = description;
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TalkerUser getOwner() {
		return owner;
	}

	public void setOwner(TalkerUser owner) {
		this.owner = owner;
	}

	public Set<TalkerUser> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<TalkerUser> participants) {
		this.participants = participants;
	}

	public Long getId() {
		return id;
	}

}
