import dependencies.Accompanist

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreModel))
    "implementation"(project(Modules.featureHistoryDomain))

    "implementation"(Accompanist.swipeRefresh)
}