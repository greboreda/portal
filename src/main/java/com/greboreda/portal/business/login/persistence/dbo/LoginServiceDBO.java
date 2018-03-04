package com.greboreda.portal.business.login.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;
import com.greboreda.portal.business.user.persistence.dbo.UserDBO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(name = "loginservice", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"userid"})
})
public class LoginServiceDBO implements DataBaseObject {

	private static final long serialVersionUID = 8070416712001564721L;

	@Id
	private String id;

	@Column(nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@Column(nullable = false)
	private LocalDateTime modificationDate;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, optional = false)
	@JoinColumn(name = "userid")
	private UserDBO user;

	@Column(nullable = false, updatable = false, unique = true)
	private String emailAddress;

	@Column(nullable = false)
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
