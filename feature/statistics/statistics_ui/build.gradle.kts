import dependencies.Accompanist
import dependencies.Compose
import dependencies.FuturedGraph
import dependencies.ComposableGraphs

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreModel))
    "implementation"(project(Modules.featureStatisticsDomain))
    "implementation"(Accompanist.pager)
    "implementation"(Compose.composeConstraintLayout)
    "implementation"(FuturedGraph.Donut)
    "implementation"(ComposableGraphs.ComposableGraphs)
}