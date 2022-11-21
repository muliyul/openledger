package com.muliyul.kropwizard.infra.bundles

import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.infra.coroutine.*
import io.dropwizard.*
import io.dropwizard.setup.*

object CoroutineSupportBundle : ConfiguredBundle<KropwizardConfiguration> {
	override fun run(configuration: KropwizardConfiguration, environment: Environment) {
		environment.jersey().register(CoroutineModelProcessor::class.java)
	}
}
