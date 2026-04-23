plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.hilt)
}

android {
    namespace = "br.com.usinasantafe.cav"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.usinasantafe.cav"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "br.com.usinasantafe.cav.CustomTestRunner"
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions.unitTests.isIncludeAndroidResources = true
    testOptions {
        unitTests.all { test ->
            test.jvmArgs("-XX:+EnableDynamicAgentLoading")
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }

    productFlavors {
        flavorDimensions += "version"
        create("dev") {
            dimension = "version"
            applicationIdSuffix = ".dev"
            manifestPlaceholders["appName"] = "CAV-DEV"
            resValue("string", "base_url", "https://app.usinasantafe.com.br/cavdev/view/")
        }
        create("qa") {
            dimension = "version"
            applicationIdSuffix = ".qa"
            manifestPlaceholders["appName"] = "CAV-QA"
            resValue("string", "base_url", "https://app.usinasantafe.com.br/cavqa/view/")
        }
        create("prod") {
            dimension = "version"
            applicationIdSuffix = ".prod"
            manifestPlaceholders["appName"] = "CAV"
            resValue("string", "base_url", "https://app.usinasantafe.com.br/cavprod/versao_1_00/view/")
        }
    }

    sourceSets {
        getByName("androidTest") {
            java.srcDirs("src/androidTest/java")
            res.srcDirs("src/androidTest/res")
            manifest.srcFile("src/androidTest/AndroidManifest.xml")
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
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.navigation.testing)
    implementation(libs.androidx.tracing)
    implementation(libs.androidx.lifecycle.compose.viewmodel)
    implementation(libs.androidx.lifecycle.compose.runtime)
    testImplementation(libs.mockito)
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.core.ktx)
    testImplementation(libs.robolectric)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.okhttp.mock.webserver)
    testImplementation(libs.retrofit)
    testImplementation(libs.retrofit.gson)
    testImplementation(libs.okhttp.logging.interceptor)
    androidTestImplementation(libs.okhttp.mock.webserver)
    implementation(libs.guava)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    androidTestImplementation(libs.room.testing)
    testImplementation(libs.room.testing)
    implementation(libs.work.runtime)
    androidTestImplementation(libs.work.testing)
    testImplementation(libs.work.testing)
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    testImplementation(libs.hilt.android.testing)
    kaptTest(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)
    implementation(libs.hilt.ext.work)
    kapt(libs.hilt.ext.compiler)
    annotationProcessor(libs.hilt.ext.compiler)
    implementation(libs.timber)
    testImplementation(kotlin("test"))
    androidTestImplementation(kotlin("test"))
    androidTestImplementation(libs.mockito)
}

kapt {
    correctErrorTypes = true
}