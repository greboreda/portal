package com.greboreda.portal.business.user.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;
import com.greboreda.portal.business.login.persistence.dbo.LoginServiceDBO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_")
public class UserDBO implements DataBaseObject {

	private static final long serialVersionUID = -6586598193475003115L;

	@Id
	private String id;

	@Column(updatable = false)
	private LocalDateTime creationDate;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_roles",
			joinColumns = {@JoinColumn(
					name = "userid",
					referencedColumnName = "id",
					nullable = false,
					updatable = false)},
			inverseJoinColumns = {@JoinColumn(
					name = "roleid",
					referencedColumnName = "id",
					nullable = false,
					updatable = false)})
	private List<RoleDBO> roles;

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

	public List<RoleDBO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDBO> roles) {
		this.roles = roles;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
