import dependencies.Accompanist

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Accompanist.flowLayout)
    "implementation"(project(Modules.coreModel))
}