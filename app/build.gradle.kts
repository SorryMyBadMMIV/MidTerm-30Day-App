plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.a30dayapp"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.a30dayapp"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    /*
     * The UI tests in src/androidTest also run on the PC under Robolectric, so
     * ./gradlew :app:testDebugUnitTest covers them with no device or emulator.
     * connectedDebugAndroidTest still runs the same files on a real device.
     */
    sourceSets {
        getByName("test").kotlin.directories.add("src/androidTest/java")
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            /*
             * Robolectric's Android 17 start-up reaches into a JDK internal that
             * Java 25 hides unless it is exported to the tests explicitly.
             */
            all { it.jvmArgs("--add-exports=java.base/jdk.internal.access=ALL-UNNAMED") }
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.androidx.espresso.core)
    testImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}