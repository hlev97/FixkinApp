import dependencies.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "hu.bme.aut.it9p0z.fixkin.di.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {

    implementation(Core.coreKtx)
    implementation(Lifecycle.runtimeKtx)
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material3)
    implementation(Compose.materialIconsExt)
    implementation(Compose.navigation)
    implementation(Compose.hiltNavigation)

    implementation(Accompanist.pager)
    implementation(Accompanist.flowLayout)

    implementation(Core.splash)

    implementation(Joda.joda)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.jUnitExt)
    androidTestImplementation(Testing.espressoCore)

    androidTestImplementation(Compose.uiTestJunit4)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.uiTestManifest)

    implementation(Lifecycle.runtimeCompose)


            implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.moshiKotlin)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)
    kapt(Retrofit.moshiCodeGen)

    implementation(DataStore.dataStorePreferences)

    implementation(Room.roomKtx)
    kapt(Room.roomCompiler)
    implementation(Room.roomRuntime)

    implementation(project(Modules.coreUi))
    implementation(project(Modules.coreModel))
    implementation(project(Modules.coreNetwork))
    implementation(project(Modules.corePreferences))
    implementation(project(Modules.coreDatabase))

    implementation(project(Modules.featureAuthenticationData))
    implementation(project(Modules.featureAuthenticationDomain))
    implementation(project(Modules.featureAuthenticationUi))

    implementation(project(Modules.featureConditionLogData))
    implementation(project(Modules.featureConditionLogDomain))
    implementation(project(Modules.featureConditionLogUi))

    implementation(project(Modules.featureHomeData))
    implementation(project(Modules.featureHomeDomain))
    implementation(project(Modules.featureHomeUi))

    implementation(project(Modules.featureHistoryData))
    implementation(project(Modules.featureHistoryDomain))
    implementation(project(Modules.featureHistoryUi))

    implementation(project(Modules.featureSurveyData))
    implementation(project(Modules.featureSurveyDomain))
    implementation(project(Modules.featureSurveyUi))

    implementation(project(Modules.featureStatisticsData))
    implementation(project(Modules.featureStatisticsDomain))
    implementation(project(Modules.featureStatisticsUi))

    implementation(project((Modules.customUiFabMenu)))
    implementation(project((Modules.customUiCheckLogCalendar)))

    testImplementation(Testing.jUnit)
    testImplementation(Testing.jUnitExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockWebServer)

    androidTestImplementation(Testing.jUnit)
    androidTestImplementation(Testing.jUnitExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.mockkAndroid)
    androidTestImplementation(Testing.mockWebServer)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)

    androidTestImplementation("io.mockk:mockk-android:1.10.0")
    androidTestImplementation("com.linkedin.dexmaker:dexmaker-mockito:2.28.3")
    androidTestImplementation(Testing.androidXTestCore)
}