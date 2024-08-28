plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("org.jetbrains.kotlin.plugin.serialization") version ("1.9.22")
    kotlin("kapt")
}

android {
    namespace = "com.sci.recipeandroid"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.sci.recipeandroid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs{
        getByName("debug") {
            storeFile = file("../keystore/coffee_debug.keystore")
            storePassword = "coffee2024"
            keyAlias = "key0"
            keyPassword = "coffee2024"
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = true
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
    viewBinding{
        enable = true
    }


    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.bundles.koin.bundle)
    implementation(libs.bundles.ktor.bundle)
    implementation(libs.glide.android)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.mmkv.android)
    implementation(libs.kotlinx.serialization)
    implementation (libs.androidx.recyclerview)

    //google login and sign up
    implementation (libs.androidx.credentials)
    implementation (libs.androidx.credentials.play.services.auth)
    implementation (libs.googleid)

    // Facebook SDK
    implementation(libs.facebook.sdk)

    //room set up
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    annotationProcessor(libs.androidx.room.compiler)


}