package com.muliyul.kropwizard.ext

import java.util.*

fun <T> T?.wrap() = Optional.ofNullable(this)
