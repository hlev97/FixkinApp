import dependencies.Accompanist
import dependencies.Joda

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"( Joda.joda)
    "implementation"(Accompanist.pager)

}