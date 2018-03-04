package com.greboreda.portal.web.admin;

import com.greboreda.portal.web.View;
import com.greboreda.portal.web.admin.dto.ManageableUserDTO;

import java.util.List;

public class AdminView implements View {

	private static final long serialVersionUID = 4023909673658674667L;

	public final List<ManageableUserDTO> manageableUsers;

	private AdminView(List<ManageableUserDTO> manageableUsers) {
		this.manageableUsers = manageableUsers;
	}

	public static AdminViewBuilder create() {
		return new AdminViewBuilder();
	}

	public static final class AdminViewBuilder {
		private List<ManageableUserDTO> manageableUsers;

		private AdminViewBuilder() {
		}

		public AdminViewBuilder withManageableUsers(List<ManageableUserDTO> manageableUsers) {
			this.manageableUsers = manageableUsers;
			return this;
		}

		public AdminView build() {
			return new AdminView(manageableUsers);
		}
	}
}
