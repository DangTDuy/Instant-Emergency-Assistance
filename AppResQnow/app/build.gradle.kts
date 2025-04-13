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
    //API GOOGLE MAPS
    implementation("com.google.maps.android:maps-compose:2.11.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Permissions
    implementation("com.google.accompanist:accompanist-permissions:0.30.1")

    // Location
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Volley - gọi HTTP API
    implementation("com.android.volley:volley:1.2.1")

    // AndroidX Dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)

    // Test Libraries
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

<<<<<<< HEAD
    // Thư viện cho Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation("com.google.firebase:firebase-analytics")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("io.coil-kt:coil-compose:2.0.0")
    implementation("com.google.firebase:firebase-auth")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // OutlineTextField
=======
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.11.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Coil (for image loading)
    implementation("io.coil-kt:coil-compose:2.0.0")

    // Facebook Sign-In
    implementation("com.facebook.android:facebook-android-sdk:latest.release")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.6.1")

    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Material 3
>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d
    implementation("androidx.compose.material3:material3:1.1.0")

    // Compose UI
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
<<<<<<< HEAD
    implementation("com.google.android.gms:play-services-auth:20.7.0")
=======

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
>>>>>>> 7dd2d1af6625a33cb3715e0ff8e67ff028c5aa7d
}
