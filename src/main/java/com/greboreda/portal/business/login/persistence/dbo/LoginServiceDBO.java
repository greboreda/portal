package com.greboreda.portal.business.login.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;
import com.greboreda.portal.business.user.persistence.dbo.UserDBO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "LOGINSERVICE")
public class LoginServiceDBO implements DataBaseObject {

	private static final long serialVersionUID = 8070416712001564721L;

	@Id
	private UUID id;

	@Column(nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@Column(nullable = false)
	private LocalDateTime modificationDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private UserDBO user;

	@Column(nullable = false, updatable = false, unique = true)
	private String emailAddress;

	@Column(nullable = false)
	private String password;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(LocalDateTime modificationDate) {
		this.modificationDate = modificationDate;
	}

	public UserDBO getUser() {
		return user;
	}

	public void setUser(UserDBO user) {
		this.user = user;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
