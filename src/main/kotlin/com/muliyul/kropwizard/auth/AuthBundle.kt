package com.muliyul.kropwizard.auth

import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.user.*
import de.ahus1.keycloak.dropwizard.*


object AuthBundle : KeycloakBundle<KropwizardConfiguration>() {
	override fun getKeycloakConfiguration(configuration: KropwizardConfiguration): KeycloakConfiguration =
		configuration.keycloak

	override fun getUserClass() =
		KropwizardUser::class.java

	override fun createAuthenticator(configuration: KeycloakConfiguration) =
		UserAuthenticator(configuration)
}
