/**
 * 
 */
package pt.ec.talkers.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pt.ec.talkers.domain.BaseEntity;

/**
 * @author ecartaxo
 *
 */
@Entity
@Table(name = "talker_user")
public class TalkerUser extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	private String password;

	@Column(nullable = false)
	private String nickname;

	public TalkerUser() {
		super();
	}

	public TalkerUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
