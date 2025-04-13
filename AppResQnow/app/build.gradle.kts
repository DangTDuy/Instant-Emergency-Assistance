plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.resqnow"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.resqnow"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        compose = true
    }
}

dependencies {
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    // AndroidX Core & Lifecycle
=======
>>>>>>> Stashed changes
    // API Google Maps
    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Volley - HTTP
    implementation("com.android.volley:volley:1.2.1")

    // AndroidX Dependencies
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM - Quản lý version cho tất cả thư viện Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    // Compose Material3
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.material:material-icons-extended")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil (hiển thị ảnh)
    implementation("io.coil-kt:coil-compose:2.0.0")

=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
<<<<<<< Updated upstream
=======
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose")

>>>>>>> Stashed changes

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Facebook SDK
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
    implementation("com.facebook.android:facebook-android-sdk:12.3.0")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

=======
>>>>>>> Stashed changes
    implementation("com.facebook.android:facebook-android-sdk:latest.release")

    // Coil (Image loading)
    implementation("io.coil-kt:coil-compose:2.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Compose UI & Material
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
<<<<<<< Updated upstream
=======
>>>>>>> cfcab10a0125c08aeada692313bd29ffbaaff774
>>>>>>> Stashed changes
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
