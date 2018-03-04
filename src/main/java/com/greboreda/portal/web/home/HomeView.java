package com.greboreda.portal.web.home;

public class HomeView {

	public final String loggedUser;
	public final String logoutUrl;

	private HomeView(String loggedUser, String logoutUrl) {
		this.loggedUser = loggedUser;
		this.logoutUrl = logoutUrl;
	}

	public static HomeViewBuilder create() {
		return new HomeViewBuilder();
	}

	public static class HomeViewBuilder {
		private String loggedUser;
		private String logoutUrl;

		private HomeViewBuilder() {}

		public HomeViewBuilder withLoggedUser(String loggedUser) {
			this.loggedUser = loggedUser;
			return this;
		}

		public HomeViewBuilder withLogoutUrl(String logoutUrl) {
			this.logoutUrl = logoutUrl;
			return this;
		}

		public HomeView build() {
			return new HomeView(loggedUser, logoutUrl);
		}

	}
}
