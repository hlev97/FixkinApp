package dependencies

object Retrofit {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"

    private const val okHttpVersion = "5.0.0-alpha.2"
    const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    const val okHttpMockWebServer = "com.squareup.okhttp3:mockwebserver:$okHttpVersion"

    private const val moshiKotlinVersion = "1.14.0"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$moshiKotlinVersion"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:$moshiKotlinVersion"

}