# Building sample app and installing via adb

Execute following commands.

```sh
./bootstrap.sh
# Create apk
./gradlew assembleDebug
# Install sample to connected android device 
./gradlew installDebug
```

WARNING: bootstrap.sh will install redux-kt into your maven locale cache.

