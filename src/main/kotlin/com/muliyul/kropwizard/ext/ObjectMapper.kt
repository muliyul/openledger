package com.muliyul.kropwizard.ext

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*

fun ObjectMapper.applyKropwizardConfiguration(): ObjectMapper = registerKotlinModule()
	.setSerializationInclusion(JsonInclude.Include.NON_NULL)
	.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
