apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreModel))
    "implementation"(project(Modules.coreNetwork))
    "implementation"(project(Modules.corePreferences))
    "implementation"(project(Modules.featureAuthenticationData))
}