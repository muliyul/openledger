package com.muliyul.kropwizard.ext

import org.slf4j.*

@Suppress("unused")
inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)
