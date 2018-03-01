# SphereMall Gateway Android SDK
Official Android SDK for integrating with SphereMall Product
### Version 1.0.7
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
  compile 'com.github.SphereMall:Android-MS-Client:version'
}
```
### Instantiating the SDK Client:
Initialize SMClient in Application class and provide client via dagger.
```
SMClient client = SMClient.initialize(context, gatewayUrl, clientId, secretKey, version);
```
## Using the client with base Resources functionality
* [Multiple Resources](https://github.com/SphereMall/Android-MS-Client/wiki/1.-Multiple-Resources)
* [Single Resource by ID](https://github.com/SphereMall/Android-MS-Client/wiki/2.-Single-Resource-by-ID)
* [Limiting and Offsetting Results](https://github.com/SphereMall/Android-MS-Client/wiki/3.-Limiting-and-Offsetting-Results)
* [Filtering result with specific fields](https://github.com/SphereMall/Android-MS-Client/wiki/4.-Filtering-result-with-specific-fields)
* [Sorting Results](https://github.com/SphereMall/Android-MS-Client/wiki/5.-Sorting-Results)
* [Counting Results](https://github.com/SphereMall/Android-MS-Client/wiki/6.-Counting-Results)
* [Product Resource](https://github.com/SphereMall/Android-MS-Client/wiki/7.-Product-Resource)
  * [Get full](https://github.com/SphereMall/Android-MS-Client/wiki/7.1.-Get-full)
* [Shop](https://github.com/SphereMall/Android-MS-Client/wiki/8.-Shop-service)
