package com.muliyul.kropwizard.infra.dao

import org.jdbi.v3.core.argument.*
import org.jdbi.v3.core.config.*
import java.nio.*
import java.sql.*
import java.util.*

internal object BinaryUUIDArgumentFactory : AbstractArgumentFactory<UUID>(Types.BINARY) {
	override fun build(uuid: UUID, config: ConfigRegistry): Argument = Argument { idx, stmt, _ ->

		val ba = ByteBuffer.wrap(ByteArray(16)).apply {
			putLong(uuid.mostSignificantBits)
			putLong(uuid.leastSignificantBits)
		}.array()

		stmt.setBytes(idx, ba)
	}
}

