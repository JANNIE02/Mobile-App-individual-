# Setup: edits Android Studio must make (Labs 5, 6 and 7)

The repo has no Gradle files, `AndroidManifest.xml` or `themes.xml`, so these
steps are manual. Package used everywhere: `com.example.tenantmanagementsystem`
(if yours differs, change it in every .kt file and in nav_graph.xml and
fragment_view_tenants.xml).

## 1. AndroidManifest.xml
Register the three Activities. The MAIN/LAUNCHER `<intent-filter>` must be on
`LoginActivity` only. Keep your existing `<application>` attributes.

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
    android:name=".RegisterActivity"
    android:exported="false" />

<activity
    android:name=".MainActivity"
    android:exported="false"
    android:windowSoftInputMode="adjustResize" />
```

## 2. Theme: res/values/themes.xml  (IMPORTANT)
Practical 3 adds its own Toolbar, so the theme must have NO action bar.
(Do NOT use `Theme.AppCompat.Light.DarkActionBar`: it crashes with
"This Activity already has an action bar".)

```xml
<style name="Theme.TenantManagementSystem" parent="Theme.AppCompat.Light.NoActionBar" />
```
If a `res/values-night/themes.xml` exists, change its parent the same way.

## 3. Project build.gradle.kts (Project: TenantManagementSystem)
Add ONE line inside `plugins { }`:
```kotlin
id("androidx.navigation.safeargs.kotlin") version "2.9.8" apply false
```

## 4. Module build.gradle.kts (Module :app)
```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("androidx.navigation.safeargs.kotlin")   // must be BELOW android.application
}

android {
    // ...keep namespace, compileSdk, defaultConfig, buildTypes, compileOptions
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.8")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.8")
    // ...keep your existing dependencies
}
```
Use ONE `buildFeatures` block. Then click **Sync Now**, then
**Build -> Rebuild Project**, then run.

## 5. Files in this zip
- Kotlin: LoginActivity, RegisterActivity, MainActivity, DashboardFragment,
  AddTenantFragment, ViewTenantsFragment, Tenant, TenantRepository
- res/layout: activity_login, activity_register, activity_main, nav_header,
  fragment_dashboard, fragment_add_tenant, fragment_view_tenants
- res/menu: main_menu, drawer_menu
- res/navigation: nav_graph

Copy `app/src` over your project's `app/src` (merge, replace).
If you already have a Compose `MainActivity.kt` or a `ui/theme` folder from the
project template, leave `ui/theme` alone (it is unused) and make sure there is
only ONE `MainActivity.kt`.
