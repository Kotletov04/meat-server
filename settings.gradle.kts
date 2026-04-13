plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
/*

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}
*/

rootProject.name = "meat-server"
include("core")
include("core:database")
findProject(":core:database")?.name = "database"
include("core:routing")
findProject(":core:routing")?.name = "routing"
include("core:database")
findProject(":core:database")?.name = "database"
include("core:common")
findProject(":core:common")?.name = "common"
include("core:common")
findProject(":core:common")?.name = "common"
include("core:domain")
findProject(":core:domain")?.name = "domain"
include("core:security")
findProject(":core:security")?.name = "security"
include("core:security")
findProject(":core:security")?.name = "security"
include("server")
include("core:caching")
findProject(":core:caching")?.name = "caching"
