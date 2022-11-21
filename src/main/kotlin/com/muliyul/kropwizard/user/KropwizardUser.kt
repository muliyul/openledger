package com.muliyul.kropwizard.user

import com.fasterxml.jackson.annotation.*
import de.ahus1.keycloak.dropwizard.*
import org.keycloak.*
import java.security.*
import java.util.*
import javax.servlet.http.*

interface KropwizardUser : Principal {
	val id: UUID
	val username: String?

	@JsonIgnore
	override fun getName(): String = id.toString()
}

class KropwizardUserImpl(
	securityContext: KeycloakSecurityContext,
	request: HttpServletRequest,
	keycloakConfiguration: KeycloakConfiguration
) :
	User(securityContext, request, keycloakConfiguration),
	KropwizardUser {

	override val id: UUID by lazy { UUID.fromString(securityContext.token.subject) }

	override val username: String? get() = securityContext.token.preferredUsername

	override fun getName(): String = securityContext.token.name
}
