package com.muliyul.kropwizard.user.api

import com.muliyul.kropwizard.user.*
import io.swagger.v3.oas.annotations.tags.*
import java.util.*
import javax.ws.rs.*
import javax.ws.rs.core.*

@Tag(name = "User")
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
interface UserApi {
	@GET
	@Path("/{id}")
	suspend fun getUser(@PathParam("id") id: UUID): Optional<KropwizardUser>

	@GET
	suspend fun searchUsers(@BeanParam searchParams: SearchUserParams): List<KropwizardUser>
}
