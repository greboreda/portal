package com.greboreda.portal.web.user;

public class UserView {

	public final String userName;
	public final String logoutUrl;

	private UserView(String userName, String logoutUrl) {
		this.userName = userName;
		this.logoutUrl = logoutUrl;
	}

	public static UserViewBuilder create() {
		return new UserViewBuilder();
	}

	public static class UserViewBuilder {
		private String userName;
		private String logoutUrl;

		private UserViewBuilder() {}

		public UserViewBuilder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public UserViewBuilder withLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
			return this;
		}

		public UserView build() {
			return new UserView(userName, logoutUrl);
		}

	}
}
