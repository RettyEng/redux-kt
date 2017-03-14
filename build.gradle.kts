buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = "1.1.0"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}


apply {
    plugin("java")
    plugin("kotlin")
}

repositories {
    jcenter()
}

/*

main.kotlin.srcDirs += 'src/main/myKotlin'
}
*/

dependencies {
    testCompile("junit:junit:4.12")
}
