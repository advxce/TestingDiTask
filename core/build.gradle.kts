import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

val userProperties = Properties()
userProperties.load(FileInputStream(rootProject.file("local.properties")))
val newsApiKey =userProperties.getProperty("NEWS_API_KEY") ?: ""
val weatherApiKey = userProperties.getProperty("WEATHER_API_KEY") ?: ""


android {
    namespace = "com.example.core"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "NEWS_API_KEY", "\"${newsApiKey}\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"${weatherApiKey}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures{
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)


    //
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    //
    api(libs.network.retrofit)
    api(libs.network.okhttp.logging.interceptor)
    api(libs.kotlinx.serialization.json)
    api(platform(libs.network.okhttp.bom) )
    api(libs.retrofit2.kotlinx.serialization.converter)

    //
    api(libs.dagger)
    ksp(libs.dagger.compiler)


}