package com.muliyul.kropwizard.auth

import com.muliyul.kropwizard.user.*
import de.ahus1.keycloak.dropwizard.*
import org.keycloak.*
import javax.servlet.http.*

class UserAuthenticator(
	keycloakConfiguration: KeycloakConfiguration
) : KeycloakAuthenticator(keycloakConfiguration) {
	override fun prepareAuthentication(
		securityContext: KeycloakSecurityContext,
		request: HttpServletRequest,
		configuration: KeycloakConfiguration
	): KropwizardUserImpl = KropwizardUserImpl(securityContext, request, configuration)
}
