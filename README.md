# XkcdComics
Xkcd Comics Application, using latest technologies stack.

<br/>

<div align="center">
  <img src="https://github.com/yasirnaseem1990/XkcdComics/blob/master/Home_Screenshot.png" width="230px" /> 
  <img src="https://github.com/yasirnaseem1990/XkcdComics/blob/master/ComicDetail_Screenshot.png" width="230px" />  
</div>

<br/>

## Features
* Comics with picture and name
* Browse through the comics
* Search query using id.
* See the comic details, including its description
* Get the comic explanation


## Architecture
* Built with Modern Android Development practices
* Utilized Usecase, Repository pattern for data
* Includes unit tests for Use cases, Repository, ViewModels, API Service response.

<img src="https://github.com/yasirnaseem1990/XkcdComics/blob/master/TestCases_Screenshot.png" width="500px" />
<img src="https://github.com/yasirnaseem1990/XkcdComics/blob/master/CodeCoverage_Screenshot.png" width="500px" />

## 📱 Download Demo on Android
Download the [APK file from here](https://github.com/yasirnaseem1990/XkcdComics/blob/master/apk/XkcdComics.apk?raw=true) on your Android phone and enjoy the Demo App :)

# Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android apps. **This is in the [main branch](https://github.com/yasirnaseem1990/XkcdComics)**.
  - [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- [MockK](https://mockk.io) - For Mocking and Unit Testing

## Note For Team
* The API you guys have provided for displaying the Comics, that API is not working properly. Not much information on their websites as well
* Instead of list only one single object return [Xkcd](https://xkcd.com/info.0.json)
* Just like the above for searching there is no url, i tried so many time to search comics using this link [ xkcd search](https://relevantxkcd.appspot.com/) , but not working.
* I did integrated the code for searching as well. Just for demonstrating my approach towards.
* I did my best to follow good code practices, consistent coding style and formatting , good use of comments along with the best architecture.

#### Thanks for the opportunity Team. 

