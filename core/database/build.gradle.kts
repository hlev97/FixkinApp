import dependencies.Room

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Room.roomKtx)
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomRuntime)

    "implementation"(project(Modules.coreModel))
}