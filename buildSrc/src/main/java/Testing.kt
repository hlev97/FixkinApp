import dependencies.Compose
import dependencies.DaggerHilt

object Testing {
    private const val jUnit4Version = "4.13.2"
    const val jUnit = "junit:junit:$jUnit4Version"

    private const val jUnitExtVersion = "1.1.3"
    const val jUnitExt = "androidx.test.ext:junit:$jUnitExtVersion"

    private const val espressoVersion = "3.4.0"
    const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"

    private const val kotlinCoroutinesTestVersion = "1.5.1"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutinesTestVersion"

    private const val truthVersion = "1.1.3"
    const val truth = "com.google.truth:truth:$truthVersion"

    private const val mockkVersion = "1.10.0"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"

    private const val turbineVersion = "0.7.0"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"

    private const val mockWebServerVersion = "4.9.3"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"

    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"

    private const val testRunnerVersion = "1.4.0"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"

    private const val androidXTestCoreVersion = "1.5.0-alpha02"
    const val androidXTestCore = "androidx.test:core:$androidXTestCoreVersion"
}