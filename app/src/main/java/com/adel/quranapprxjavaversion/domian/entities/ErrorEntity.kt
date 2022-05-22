package com.adel.quranapprxjavaversion.domian.entities

sealed class ErrorEntity {
    object Network : ErrorEntity()
    object NotFound : ErrorEntity()
    object AccessDenied : ErrorEntity()
    object ServiceUnAvailable : ErrorEntity()
    object Unknown : ErrorEntity()
}
