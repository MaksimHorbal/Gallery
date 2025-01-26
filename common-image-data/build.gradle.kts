plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.horbal.common.image.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "IMAGE_BASE_URL", "\"https://api.unsplash.com/\"")
        buildConfigField("String", "IMAGE_API_KEY", "\"Client-ID -BVoJZ1PxAibOIA3pUgLVHqU862J83lBW7WYkLKw4mY\"")
        buildConfigField("String", "IMAGE_MEDIA_TYPE", "\"application/json\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common-image-domain"))

    implementation(libs.paging.common)
    implementation(libs.retrofit.core)
    implementation(libs.kotlinx.serialization.json)
}