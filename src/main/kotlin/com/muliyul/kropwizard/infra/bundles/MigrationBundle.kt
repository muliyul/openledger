package com.muliyul.kropwizard.infra.bundles

import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.infra.*
import io.dropwizard.db.*
import io.dropwizard.flyway.*
import ru.vyarus.dropwizard.guice.module.installer.bundle.*

object MigrationBundle : GuiceyBundle {
	override fun initialize(bootstrap: GuiceyBootstrap) {
		bootstrap.dropwizardBundles(KropwizardFlywayBundle)
	}

	override fun run(environment: GuiceyEnvironment) {
		val configuration = environment.configuration<KropwizardConfiguration>()

		if (KropwizardEnvironment.Current == KropwizardEnvironment.Development) {
			KropwizardFlywayBundle.getFlywayFactory(configuration)
				.build(configuration.database.url, configuration.database.user, configuration.database.password)
				.migrate()
		}
	}
}

private object KropwizardFlywayBundle : FlywayBundle<KropwizardConfiguration>() {
	override fun getDataSourceFactory(configuration: KropwizardConfiguration): PooledDataSourceFactory =
		configuration.database
}
