package com.adel.quranapprxjavaversion.domian.utils

import com.adel.quranapprxjavaversion.domian.entities.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}
