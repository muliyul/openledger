package com.muliyul.kropwizard

import de.ahus1.keycloak.dropwizard.*
import io.dropwizard.*
import io.dropwizard.db.*
import io.dropwizard.jobs.*

data class KropwizardConfiguration(
	val database: DataSourceFactory = DataSourceFactory(),
	val keycloak: KeycloakConfiguration = KeycloakConfiguration()
) : Configuration(), JobConfiguration
