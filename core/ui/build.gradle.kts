import dependencies.Accompanist
import dependencies.Saket

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(Accompanist.flowLayout)
    "implementation"(project(Modules.coreModel))
    "implementation"(Saket.swipe)
}