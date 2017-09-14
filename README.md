# Redux-kt
[![Release](https://jitpack.io/v/rettyeng/redux-kt.svg?style=flat-square)](https://jitpack.io/#rettyeng/redux-kt)

# Introduction

Redux-kt is a simple [Redux](http://redux.js.org/)-like implementation for kotlin. This product is very inspired by [ReSwift](https://github.com/ReSwift/ReSwift).

# Table of contents

* [Simple usage](#simple-usage)
  - [Installation](#installation)
    * [Gradle](#gradle)
    * [Maven](#maven)
  - [Coding](#coding)
  - [Sample apps](#sample-apps)
* [Contributing](#contributing)
  - [Building](#building)
  - [Using debug build](#using-debug-build)
* [Lisense](#license)

# Simple usage

This section explains a few steps to get started with redux-kt.

## Installation

Redux-kt is distributed with jitpack.io so you can use it with build systems that can resolve dependencies with maven repositories.

### Gradle

With gradle project, follow steps below to install redux-kt as a dependency.

#### Add jitpack.io as a maven repository

Open `build.gradle` of the target project and add repository setting.

```groovy
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

#### Modify dependencies

Open `build.gradle` of the target project and insert redux-kt as a dependency of the target.

```groovy
dependencies {
    compile 'com.github.rettyeng.redux-kt:redux-kt-core:0.0.2'
}
```

### Maven

With mvn, follow steps below to install redux-kt as a dependency.

#### Add jitpack.io as a maven repository

Open `pom.xml` of the target project and add `repository` tag.

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

#### Modify dependencies

Add redux-kt as a dependency of the project.

```xml
<dependencies>
    <dependency>
        <groupId>com.github.rettyeng.redux-kt</groupId>
        <artifactId>redux-kt-core</artifactId>
        <version>0.0.2</version>
    </dependency>
</dependencies>
```

## Coding

A minimal example is below. If you want more examples, you can find a sample Android app project in this repository. See also [Sample apps](#sample-apps).

```kotlin
data class ApplicationState(val text: String = ""): StateType
data class PostTextAction(val text: String): Action

val reducer: Reducer<ApplicationState> = { action, state ->
    when(action) {
        is PostTextAction -> state.copy(text = action.text)
        else -> state.copy()
    }
}
    
val loggingMiddleware: Middleware<ApplicationState> = {
    { dispatch ->
        { action ->
            Log.d("Middleware", "dispatching action $action")
            dispatch(action)
        }
    }
}

val store = Store<ApplicationState>(ApplicationState(),
                                    reducer,
                                    listOf(loggingMiddleware))

store.subscribe { old, new ->
    println("New state is $new")
}

store.dispatch(PostTextAction("foobar"))
```

## Sample apps

[README](sample/README.md) of the sample.

# Contributing

Feel free to report bugs, to create issues, or to create PRs.

## Building

Install maven and run following command.

```sh
mvn package
```

## Using debug build

Import [jar](#building) into your project as a library or install a snapshot to local maven and use it.

### Install redux-kt into a local maven repository

Use maven-plugin to install.

```sh
mvn install
```

### Use the version installed into a local repository

Use snapshot as a dependency.

#### Gradle

With gradle, repository settings is needed to use maven local cache.

```
buildscript {
    repositories {
        mavenLocal()
    }
}

dependencies {
    compile 'com.github.rettyeng:redux-kt:0.0.1-SNAPSHOT'
}
```

#### Maven

```
<dependencies>
    <dependency>
        <groupId>com.github.rettyeng</groupId>
        <artifactId>redux-kt</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
</dependencies>
```

# License

[MIT](LICENSE)


