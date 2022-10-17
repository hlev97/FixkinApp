import dependencies.Accompanist
import dependencies.Joda
import dependencies.Compose

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreModel))
    "implementation"(project(Modules.featureHomeDomain))
    "implementation"(project(Modules.customUiCheckLogCalendar))
    "implementation"(Accompanist.pager)
    "implementation"(Accompanist.flowLayout)
    "implementation"(Joda.joda)
    "implementation"(Compose.composeConstraintLayout)
}