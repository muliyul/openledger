package com.muliyul.kropwizard.user

import com.muliyul.kropwizard.*
import kotlinx.coroutines.*
import org.keycloak.admin.client.*
import org.keycloak.representations.idm.*
import java.util.*
import javax.inject.*

class KeycloakUserClient @Inject constructor(
	keycloak: Keycloak,
	configuration: KropwizardConfiguration
) : UserRepository {
	private val resource = keycloak
		.realm(configuration.keycloak.realm)
		.users()

	override suspend fun getUserByIdOrNull(id: UUID) = withContext(Dispatchers.IO) {
		resource[id.toString()].toRepresentation()?.toUser()
	}

	override suspend fun getUsers(userIds: List<UUID>) = withContext(Dispatchers.IO) {
		resource.search("id:${userIds.joinToString(separator = ",")}", 0, userIds.size).map { it.toUser() }
	}

	override suspend fun list(query: String?, limit: Int) = withContext(Dispatchers.IO) {
		resource.search(query, 0, limit).map { it.toUser() }
	}
}

class KeycloakUserAdapter(
	representation: UserRepresentation
) : KropwizardUser {
	override val id: UUID = representation.id.toUUID()
	override val username: String = representation.username
}

private fun String.toUUID(): UUID = UUID.fromString(this)

private fun UserRepresentation.toUser(): KropwizardUser = KeycloakUserAdapter(this)
