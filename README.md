# movie-kotlin

## Project Introduction
This is a kotlin project which shows movie information
Both movie list and movie detail pages are working. Movies, trailers and reviews are downloaded from Internet and saved in a local room database.



## Refactor to use Dependence Injection and Service Locator

## TASKS
* Save network traffic if a movie has already saved locally
* Arrange UI more elegantly
* Increase test coverage

## Completed Tasks
* Change LinearLayout to ConstraintLayout
* To give Picasso or Glide enough permission to download from network, You need do three things:
* In `AndroidManifest.xml`, add two lines. `<uses-permission android:name="android.permission.INTERNET" />` and `android:usesCleartextTraffic="true"`
* delete your app in emulator and install it the next round
