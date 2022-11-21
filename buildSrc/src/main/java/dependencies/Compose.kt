package dependencies

object Compose {
    const val composeVersion = "1.3.0-rc01"
    const val composeCompilerVersion = "1.3.2"

    private const val material3Version = "1.0.0-beta02"
    const val material3 = "androidx.compose.material3:material3:$material3Version"

    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"

    private const val activityComposeVersion = "1.5.1"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"

    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVersion"

    private const val lifecycleVersion = "2.6.0-alpha02"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion"

    private const val hiltNavigationVersion = "1.0.0"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationVersion"

    private const val navigationVersion = "2.5.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    const val materialIconsExt = "androidx.compose.material:material-icons-extended:$composeVersion"

    private const val composeConstraintLayoutVersion = "1.0.1"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayoutVersion"

    private const val runtimeVersion = "1.2.1"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$runtimeVersion"
}