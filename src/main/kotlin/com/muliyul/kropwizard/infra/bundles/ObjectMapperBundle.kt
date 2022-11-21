package com.muliyul.kropwizard.infra.bundles

import com.fasterxml.jackson.databind.*
import com.google.inject.Module
import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.ext.*
import ru.vyarus.dropwizard.guice.module.installer.bundle.*

object ObjectMapperBundle : GuiceyBundle {
	override fun initialize(bootstrap: GuiceyBootstrap) {
		val guiceyBootstrap = bootstrap.bootstrap<KropwizardConfiguration>()

		guiceyBootstrap.objectMapper.applyKropwizardConfiguration()

		bootstrap.modules(Module { it.bind(ObjectMapper::class.java).toInstance(guiceyBootstrap.objectMapper) })
	}
}
