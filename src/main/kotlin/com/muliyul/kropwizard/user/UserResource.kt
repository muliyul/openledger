package com.muliyul.kropwizard.user

import com.muliyul.kropwizard.ext.*
import com.muliyul.kropwizard.user.api.*
import java.util.*
import javax.inject.*
import javax.ws.rs.*

class UserResource @Inject constructor(
	private val userHandler: UserHandler
) : UserApi {

	override suspend fun getUser(id: UUID): Optional<KropwizardUser> =
		userHandler.getUserById(id).wrap()

	override suspend fun searchUsers(@BeanParam searchParams: SearchUserParams): List<KropwizardUser> =
		userHandler.listUsers(searchParams.query, searchParams.limit)
}
