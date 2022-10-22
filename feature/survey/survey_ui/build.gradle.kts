import dependencies.Compose

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreModel))
    "implementation"(project(Modules.featureSurveyDomain))
    "implementation"(Compose.composeConstraintLayout)
}