package io.github.droidkaigi.confsched2022.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            androidApplication {
                namespace?.let {
                    this.namespace = it
                }
                compileSdk = 32

                defaultConfig {
                    minSdk = 23
                }

                compileOptions {
                    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
                    targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
                    isCoreLibraryDesugaringEnabled = true
                }

                dependencies {
                    add("coreLibraryDesugaring", libs.findLibrary("androidDesugarJdkLibs").get())
                }

                defaultConfig.targetSdk = 32
                packagingOptions {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}
