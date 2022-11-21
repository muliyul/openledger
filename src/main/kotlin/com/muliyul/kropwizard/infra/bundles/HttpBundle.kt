package com.muliyul.kropwizard.infra.bundles

import com.muliyul.kropwizard.*
import com.muliyul.kropwizard.infra.*
import io.dropwizard.*
import io.dropwizard.setup.*
import org.eclipse.jetty.servlets.*
import java.util.*
import javax.servlet.*

object HttpBundle : ConfiguredBundle<KropwizardConfiguration> {
	override fun run(configuration: KropwizardConfiguration, environment: Environment) {
		setupCors(environment)
	}

	private fun setupCors(environment: Environment) {
		KropwizardEnvironment.runIfCurrent(KropwizardEnvironment.Development) {

			val cors = environment.servlets().addFilter("CORS", CrossOriginFilter::class.java)

			// Configure CORS parameters
			cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*")
			cors.setInitParameter(
				CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"X-Requested-With,Content-Type,Accept,Origin,Authorization"
			)
			cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD")

			cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, true.toString())
			cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, false.toString())


			// Add URL mapping
			cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType::class.java), true, "/*")
		}

	}
}
