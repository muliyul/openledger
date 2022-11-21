package com.muliyul.kropwizard

import com.muliyul.kropwizard.auth.*
import com.muliyul.kropwizard.core.*
import com.muliyul.kropwizard.infra.*
import com.muliyul.kropwizard.infra.bundles.*
import io.dropwizard.*
import io.dropwizard.configuration.*
import io.dropwizard.setup.*
import io.swagger.v3.jaxrs2.integration.resources.*
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import org.apache.commons.text.*
import ru.vyarus.dropwizard.guice.*
import ru.vyarus.guicey.admin.*


fun main(args: Array<String>) = KropwizardServer.run(*args)

@OpenAPIDefinition(info = Info(title = "Kropwizard"))
object KropwizardServer : Application<KropwizardConfiguration>() {

	override fun initialize(bootstrap: Bootstrap<KropwizardConfiguration>) {
		bootstrap.configurationSourceProvider =
			SubstitutingSourceProvider(ResourceConfigurationSourceProvider(), StringSubstitutor.createInterpolator())

		val guiceBundle = GuiceBundle
			.builder()
			.enableAutoConfig(javaClass.packageName)
			.bundles(
				ObjectMapperBundle,
				JdbiBundle,
				JobsBundle,
				AdminRestBundle()
			)
			.dropwizardBundles(
				CoroutineSupportBundle,
				HttpBundle,
				AuthBundle
			)
			.modules(
				CoreModule
			)
			.extensions(
				OpenApiResource::class.java,
				AcceptHeaderOpenApiResource::class.java,
			)
			.build(KropwizardEnvironment.Current.toStage())

		bootstrap.addBundle(guiceBundle)
	}

	override fun run(configuration: KropwizardConfiguration, environment: Environment) {
	}
}
