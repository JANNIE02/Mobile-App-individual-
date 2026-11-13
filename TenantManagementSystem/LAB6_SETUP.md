# Lab 6 setup: edits Android Studio must make

The repo has no Gradle files or AndroidManifest.xml, so these steps are manual.
Package used everywhere: `com.example.tenantmanagementsystem`.

## 1. AndroidManifest.xml (Lab 5, Step 4)
Register `LoginActivity` and `RegisterActivity`, and move the MAIN/LAUNCHER
`<intent-filter>` from `MainActivity` to `LoginActivity`:

```xml
<activity
    android:name=".LoginActivity"
    android:exported="true"
    android:windowSoftInputMode="adjustResize">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>

<activity
    android:name=".MainActivity"
    android:exported="false"
    android:windowSoftInputMode="adjustResize" />

<activity
    android:name=".RegisterActivity"
    android:exported="false" />
```

## 2. Project build.gradle.kts (Lab 6, Step 7)
```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
}
```

## 3. Module (:app) build.gradle.kts (Lab 6, Step 8)
```kotlin
plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs.kotlin")   // must be BELOW android.application
}

dependencies {
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.8")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.8")
    // ...keep your existing dependencies
}
```
Also confirm `buildFeatures { viewBinding = true; dataBinding = true }`
(needed by the binding classes), then click Sync Now.
