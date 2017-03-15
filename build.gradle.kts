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
