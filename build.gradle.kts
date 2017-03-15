group='io.github.yusaka39'
version='0.0.1'

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
    plugin("maven")
}

repositories {
    mavenCentral()
    jcenter()
}

/*

main.kotlin.srcDirs += 'src/main/myKotlin'
}

*/

dependencies {

    compile("org.jetbrains.kotlin:kotlin-stdlib:1.1.0")

    testCompile("junit:junit:4.12")
}
