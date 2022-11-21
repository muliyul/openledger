package com.muliyul.kropwizard.user.api

import javax.validation.constraints.*
import javax.ws.rs.*

data class SearchUserParams(
	@QueryParam("q")
	val query: String?,

	@QueryParam("limit")
	@Max(10)
	@DefaultValue("10")
	val limit: Int
)
