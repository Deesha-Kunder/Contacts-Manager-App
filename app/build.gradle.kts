plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.deesha.contactsmanagerapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.deesha.contactsmanagerapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //room database library
    implementation(libs.room)
    annotationProcessor(libs.roomcompiler)
    //livedata
    implementation(libs.livedata)
    //viewModel
    implementation(libs.viewmodel)
    //glide
    implementation(libs.glide)
    annotationProcessor(libs.glidecompiler)
}