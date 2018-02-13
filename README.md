# SphereMall Gateway Android SDK
Official Android SDK for integrating with SphereMall Product
### Version 1.0.5
### Installation
1. Add the JitPack repository to your build file
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
2. Add the dependency
```
dependencies {
  compile 'com.github.SphereMall:Android-MS-Client:1.0.5'
}
```
### Instantiating the SDK Client:
Initialize SMClient in Application class and provide client via dagger.
```
SMClient client = SMClient.initialize(context, gatewayUrl, clientId, secretKey, version);
```
