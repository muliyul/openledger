@file:Suppress("unused")

package com.muliyul.kropwizard.core

import com.google.inject.*
import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.user.*
import de.ahus1.keycloak.dropwizard.*
import org.keycloak.*
import org.keycloak.admin.client.*
import ru.vyarus.dropwizard.guice.module.support.*
import java.util.*

object CoreModule : DropwizardAwareModule<KropwizardConfiguration>() {
	override fun configure() {
		bind(UserRepository::class.java).to(KeycloakUserClient::class.java).`in`(Singleton::class.java)
	}

	@Provides
	fun uuid(): UUID = UUID.randomUUID()

	@Provides
	@Singleton
	fun keycloakAdminClient(): Keycloak {
		val kcConfig: KeycloakConfiguration = configuration(KeycloakConfiguration::class.java)
		return KeycloakBuilder.builder()
			.serverUrl(kcConfig.authServerUrl)
			.realm(kcConfig.realm)
			.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
			.clientId(kcConfig.resource)
			.clientSecret(kcConfig.credentials["secret"].toString())
			.build()
	}
}
