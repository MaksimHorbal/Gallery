pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Gallery"
include(":app")
include(":common-image-domain")
include(":common-image-data")
include(":common-image-wiring")
include(":common-network")
include(":feature-image-list")
include(":common-blurhash")
include(":common-glide")
include(":feature-image-details")
include(":common-android")
