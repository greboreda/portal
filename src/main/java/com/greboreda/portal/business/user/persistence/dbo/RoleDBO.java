package com.greboreda.portal.business.user.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "ROLE_")
public class RoleDBO implements DataBaseObject {

	private static final long serialVersionUID = 7447148392534752594L;

	@Id
	private UUID id;

	@Column(nullable = false, updatable = false)
	private String name;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
