package com.muliyul.kropwizard.infra.bundles

import io.dropwizard.jobs.*
import ru.vyarus.dropwizard.guice.module.installer.bundle.*

object JobsBundle : GuiceyBundle {

	override fun run(environment: GuiceyEnvironment) {
		environment.onGuiceyStartup { config, env, injector ->
			GuiceJobsBundle(injector).run(config as JobConfiguration, env)
		}
	}

}
