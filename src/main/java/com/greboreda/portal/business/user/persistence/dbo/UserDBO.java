package com.greboreda.portal.business.user.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USER_")
public class UserDBO implements DataBaseObject {

	private static final long serialVersionUID = -6586598193475003115L;

	@Id
	private UUID id;

	@Column(updatable = false)
	private LocalDateTime creationDate;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_ROLES",
			joinColumns = {@JoinColumn(name = "ROLEID", referencedColumnName = "ID", nullable = false, updatable = false)},
			inverseJoinColumns = {@JoinColumn(name = "USERID", referencedColumnName = "ID")})
	private Set<RoleDBO> roles;

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

	public Set<RoleDBO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDBO> roles) {
		this.roles = roles;
	}
}
