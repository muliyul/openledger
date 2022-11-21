package com.muliyul.kropwizard.infra

import com.google.inject.*
import com.muliyul.kropwizard.ext.*

enum class KropwizardEnvironment {
	Development,
	Test,
	Production;

	companion object {
		val Current = fromString(System.getenv("env") ?: "")

		private fun fromString(raw: String) = when (raw.lowercase()) {
			"prod" -> Production
			"test" -> Test
			else -> Development
		}

		fun runIfCurrent(vararg environments: KropwizardEnvironment, block: () -> Unit) {
			runIf(Current in environments) { block() }
		}
	}
}

fun KropwizardEnvironment.toStage() = when (this) {
	KropwizardEnvironment.Development -> Stage.DEVELOPMENT

	KropwizardEnvironment.Test,
	KropwizardEnvironment.Production -> Stage.PRODUCTION
}
