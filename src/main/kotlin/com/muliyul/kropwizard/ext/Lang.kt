package com.muliyul.kropwizard.ext

fun <T> T.runIf(condition: Boolean, block: T.() -> Unit): T = if (condition) {
	block()
	this
} else this

fun <T> T.runIfNotNull(any: Any?, block: T.() -> Unit) = runIf(any != null, block)
