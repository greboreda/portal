package com.greboreda.portal.web.admin.dto;

import com.greboreda.portal.web.DataTransportObject;

import java.time.LocalDateTime;

public class ManageableUserDTO implements DataTransportObject {

	private static final long serialVersionUID = 2990032901895735104L;

	public static final String dateFormat = "yyyy-MM-dd HH:mm:ss";

	public final String userId;
	public final LocalDateTime creationDate;
	public final String loginEmail;
	public final Boolean isAdmin;

	private ManageableUserDTO(String userId, LocalDateTime creationDate, String loginEmail, Boolean isAdmin) {
		this.userId = userId;
		this.creationDate = creationDate;
		this.loginEmail = loginEmail;
		this.isAdmin = isAdmin;
	}

	public static ManageableUserDTOBuilder create() {
		return new ManageableUserDTOBuilder();
	}

	public static final class ManageableUserDTOBuilder {
		private String userId;
		private LocalDateTime creationDate;
		private String loginEmail;
		private Boolean isAdmin;

		private ManageableUserDTOBuilder() {
		}

		public ManageableUserDTOBuilder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public ManageableUserDTOBuilder withCreationDate(LocalDateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		public ManageableUserDTOBuilder withLoginEmail(String loginEmail) {
			this.loginEmail = loginEmail;
			return this;
		}

		public ManageableUserDTOBuilder withIsAdmin(Boolean isAdmin) {
			this.isAdmin = isAdmin;
			return this;
		}

		public ManageableUserDTO build() {
			return new ManageableUserDTO(userId, creationDate, loginEmail, isAdmin);
		}
	}
}
