package com.muliyul.kropwizard.user

import java.util.*
import javax.inject.*

class UserHandler @Inject constructor(
	private val userRepository: UserRepository
) {
	suspend fun listUsers(query: String? = null, limit: Int): List<KropwizardUser> =
		userRepository.list(query, limit)

	suspend fun getUserById(id: UUID): KropwizardUser =
		userRepository.getUserById(id)

}
