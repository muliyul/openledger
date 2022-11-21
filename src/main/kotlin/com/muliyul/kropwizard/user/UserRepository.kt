package com.muliyul.kropwizard.user

import java.util.*

interface UserRepository {

	suspend fun getUserByIdOrNull(id: UUID): KropwizardUser?

	suspend fun getUserById(userId: UUID): KropwizardUser =
		getUserByIdOrNull(userId) ?: error("Unable to find user '$userId'.")

	suspend fun list(query: String?, limit: Int): List<KropwizardUser>
	suspend fun getUsers(userIds: List<UUID>): List<KropwizardUser>

}
