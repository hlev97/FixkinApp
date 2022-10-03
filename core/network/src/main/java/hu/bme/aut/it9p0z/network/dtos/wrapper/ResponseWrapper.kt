package hu.bme.aut.it9p0z.network.dtos.wrapper

data class ResponseWrapper<T>(
    val data: T?,
    val message: String
)
