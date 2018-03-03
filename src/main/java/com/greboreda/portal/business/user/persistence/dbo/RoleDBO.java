package com.greboreda.portal.business.user.persistence.dbo;

import com.greboreda.portal.business.DataBaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_")
public class RoleDBO implements DataBaseObject {

	private static final long serialVersionUID = 7447148392534752594L;

	@Id
	private String id;

	@Column(nullable = false, updatable = false)
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
