package com.adel.quranapprxjavaversion.data.utils

import com.adel.quranapprxjavaversion.domian.utils.ErrorHandler
import com.adel.quranapprxjavaversion.domian.entities.ErrorEntity
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun getError(throwable: Throwable): ErrorEntity {
        return when (throwable) {
            is IOException -> ErrorEntity.Network
            is UnknownHostException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied
                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnAvailable
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}
