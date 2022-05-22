package com.adel.quranapprxjavaversion.data.models

data class ApiResponse<T>(var code: Int, var status: String, var data: T)
