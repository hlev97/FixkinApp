import dependencies.Retrofit

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.moshiKotlin)
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "kapt"(Retrofit.moshiCodeGen)

    "implementation"(project(Modules.coreModel))
}