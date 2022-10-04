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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation(Core.coreKtx)
    implementation(Lifecycle.runtimeKtx)
    implementation(Compose.activity)
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.material3)
    implementation(Compose.navigation)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.jUnitExt)
    androidTestImplementation(Testing.espressoCore)

    androidTestImplementation(Compose.uiTestJunit4)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.uiTestManifest)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.moshiKotlin)
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)

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

}