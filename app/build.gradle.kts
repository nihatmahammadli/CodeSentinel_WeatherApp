plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.safe.args)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.nihatmahammadli.weatherapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.nihatmahammadli.weatherapp"
        minSdk = 24
        targetSdk = 36
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
        viewBinding = true
        buildConfig = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {
    //Dagger
    implementation("com.google.dagger:hilt-android:2.56.2")
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.fragment)
    ksp("com.google.dagger:hilt-android-compiler:2.56.2")

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // Material Design, Glide, Swipe
    implementation ("com.google.android.material:material:1.12.0")
    implementation(libs.swiperefreshlayout)
    implementation(libs.glide)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}