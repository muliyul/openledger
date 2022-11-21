package com.muliyul.kropwizard.infra.dao

import org.jdbi.v3.core.mapper.*
import org.jdbi.v3.core.statement.*
import java.nio.*
import java.sql.*
import java.util.*

internal object BinaryUUIDColumnMapper : ColumnMapper<UUID?> {
	override fun map(rs: ResultSet, columnNumber: Int, ctx: StatementContext): UUID? {
		val ba = rs.getBytes(columnNumber)?.let { ByteBuffer.wrap(it) }

		return ba?.let { UUID(it.long, it.long) }
	}
}
