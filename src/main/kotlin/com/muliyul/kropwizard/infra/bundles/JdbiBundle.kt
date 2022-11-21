package com.muliyul.kropwizard.infra.bundles

import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.infra.dao.*
import org.jdbi.v3.jackson2.*
import org.jdbi.v3.sqlobject.kotlin.*
import ru.vyarus.dropwizard.guice.module.installer.bundle.*
import ru.vyarus.guicey.jdbi3.JdbiBundle

object JdbiBundle : GuiceyBundle {
	override fun initialize(bootstrap: GuiceyBootstrap) {
		val dwb = bootstrap.bootstrap<KropwizardConfiguration>()
		val jdbiBundle = JdbiBundle.forDatabase { configuration: KropwizardConfiguration, _ -> configuration.database }
			.withConfig {
				it.getConfig(Jackson2Config::class.java).apply { mapper = dwb.objectMapper }
				it.registerArgument(BinaryUUIDArgumentFactory)
				it.registerColumnMapper(BinaryUUIDColumnMapper)
			}
			.withPlugins(
				KotlinSqlObjectPlugin(),
				Jackson2Plugin()
			)

		bootstrap
			.bundles(jdbiBundle, MigrationBundle)
	}
}
