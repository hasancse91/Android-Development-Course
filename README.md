# Android Development Course

I'm taking an onsite course on Android App Development at Dhaka, Bangladesh (see here for details course outlines: https://goo.gl/SP6yzk). I'll add all source codes of my training program is this repository. You'll find here step by step source code and topics of Android App Development training.

### [Class: 1](https://github.com/hasancse91/Android-Development-Course/tree/master/01-FirstClass)
 - ConstraintLayout
 - TextView
 - Button
 - Click listener Java method
 - Toast
 - Intent (start another activity)
 
### [Class: 2](https://github.com/hasancse91/Android-Development-Course/tree/master/02-SecondClass) 
 - ConstraintLayout
 - TextView
 - EditText
 - Button
 - Click listener Java method
 - Click listener XML attribute
 - Toast
 - Intent (start another activity)
 - Pass values from one activity to another activity
 - Reusable XML layout (using `include` tag)
 - Using third party library ([See Bengali blog post](https://hellohasan.com/2017/05/23/android-development-pretty-logger-library/))
 
### [Class: 3](https://github.com/hasancse91/Android-Development-Course/tree/master/03-ThirdClass)
- ImageView with Android Piccasso Library
- SharedPreference
- WebView
- Send an E-mail using Intent
- Discussing about Singleton Design Pattern
- Discussing about Android compile and build system. Keyword: Dalvik

### [Class: 4](https://github.com/hasancse91/Android-Development-Course/tree/master/04-FourthClass)
- `ButterKnife` Library to reduce boilerplate code
- Phone call
- Run time permission for phone call
- Send a POST request to a PHP server and receive a response ([See Bengali Blog post](https://hellohasan.com/2016/12/03/android-retrofit-get-post-method/))
- Update UI from another class using `interface`

### [Class: 5](https://github.com/hasancse91/Android-Development-Course/tree/master/05-FifthClass)
- For signing in, send a POST request to server with user name and password
- If sign in is success, finish the login Activity and start another Activity to show user's IP address
- Send a GET request to [this](http://ip.jsontest.com/) site. That site will send a response body of JSON with user's IP address. Set the IP address to a `TextView`
- Use of mention string resources in `string.xml` file instead of hardcoded text
- Use of `@OnClick` annotation of `ButterKnife` library to listen any button/view click event
- *[Check these awesome blog posts on Retrofit](https://futurestud.io/tutorials/tag/retrofit/)*

### [Class: 6](https://github.com/hasancse91/Android-Development-Course/tree/master/06-SixthClass)
- ListView - show a static string list from `<string-array>` of `string.xml`
- ListView - item click event listen
- ListView - item long click event listen
- RecyclerView - show a movie list with image (using `Picasso` Library). Data will come from server using [`Retrofit`](https://github.com/hasancse91/Android-Development-Course/tree/master/05-FifthClass)
- POJO to JSON covert using Gson Library and print in Log
- [*Check this Bengali Blog post for `RecyclerView` and `CardView`*](https://hellohasan.com/2017/02/20/android-cardview-recyclerview-bengali-tutorial/)

### [Class: 7](https://github.com/hasancse91/Android-Development-Course/tree/master/07-SeventhClass)
- `RecyclerView` - show a movie list with image (using `Picasso` Library). Data will come from server using [`Retrofit`](https://github.com/hasancse91/Android-Development-Course/tree/master/05-FifthClass)
- `RecyclerView` - item click event listen (listen every component click event of the `RecyclerView` item)
- `RecyclerView` will be refreshable using `SwipeRefreshLayout`
- Check network state (is internet available) using `ConnectivityManager` and `NetworkInfo` classes before any network call
- Show `ProgressDialog` when first time call to network for movie list
- Pass *Movie Object* from `MovieListActivity` to `MovieDetailsActivity` using `Intent` and show the `Movie` data in `MovieDetailsActivity`
- Enable back button on `ActionBar` of `MovieDetailsActivity`
- Enable **Click Again to Exit** feature in `MovieListActivity` when device's back button pressed

### [Class: 8](https://github.com/hasancse91/Android-Development-Course/tree/master/08-EighthClass)
- `Activity Lifecycle`
- Use of `dimen` value for widget's height-width or padding-margin instead of hardcoded `dp` value
- Use different `xml layout` for responsive UI
- `AlertDialog` to show a pop up dialog

**Resources:**
- [Android Activity Lifecycle - Android official documentation](https://developer.android.com/guide/components/activities/activity-lifecycle.html)
- [Activity Lifecycle - Tutorials Point](https://www.javatpoint.com/android-life-cycle-of-activity)
- [Dimension](https://developer.android.com/guide/topics/resources/more-resources.html#Dimension)
- [Managing Screen Sizes](https://android-developers.googleblog.com/2011/07/new-tools-for-managing-screen-sizes.html)
- [Different values folder for different screens](https://stackoverflow.com/a/32861248/6200296)
- [Supporting Different Screen Sizes - Android official documentation](https://developer.android.com/training/multiscreen/screensizes.html)

### [Class: 9](https://github.com/hasancse91/Android-Development-Course/tree/master/09-NinthClass)
- Capture image using default camera App of device
- Web scraping using JSOUP library
- `ProgressBar`

**Resources:**
- [Bengali Blog Post on JSOUP Library](https://hellohasan.com/2017/02/25/android-web-scraping-jsoup/)
- [Android working with Camera - AndroidHive](https://www.androidhive.info/2013/09/android-working-with-camera-api/)
- [Android Uploading Camera Image, Video to Server with Progress Bar - AndroidHive](https://www.androidhive.info/2014/12/android-uploading-camera-image-video-to-server-with-progress-bar/)

### [Class: 10](https://github.com/hasancse91/Android-Development-Course/tree/master/10-TenthClass)
- Implement an Abstraction Layer for Retrofit Network call
- Android debugging
- Refactor/rename (class, variable, method etc.)
- Rename Android unique package name
- Change Launcher Icon of Android App
- Some Android Studio keyboard shortcuts

**Resources:**
- [Bengali Blog Post on ***Abstraction in network layer using Retrofit***](https://hellohasan.com/2017/10/01/android-retrofit-get-post-method-different-network-layer/)
- [Debug your App - Android Official Documentation](https://developer.android.com/studio/debug/index.html)
- [Refactor/rename file in Android Studio](https://stackoverflow.com/a/28269008/6200296)
- [Refactor your code in Android Studio - OneTouchCode](http://onetouchcode.com/2016/10/12/code-refactor-android-studio/)
- [Rename package in Android Studio](https://stackoverflow.com/a/29092698/6200296)
- [Change Launcher icon](https://stackoverflow.com/a/21385148/6200296)
- [Android Studio Keyboard Shortcuts - Android Official Documentation](https://developer.android.com/studio/intro/keyboard-shortcuts.html)

### [Class: 11](https://github.com/hasancse91/Android-Development-Course/tree/master/11-EleventhClass)
- SQLite Database 
	- Create Database
	- Create Table
	- Write a record (row) into table
	- Read all records from table
	- Count the number of row in table
- Git
	- Branching
	- gitignore

**Resources:**
- [Android SQLite Database - TutorialsPoint](https://www.tutorialspoint.com/android/android_sqlite_database.htm)
- [Local Databases with SQLiteOpenHelper - CodePath](http://guides.codepath.com/android/local-databases-with-sqliteopenhelper)
- [Android SQLite Database Tutorial - AndroidHive](https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/)
- [Git branching and Merging](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)
- [gitignore in Android Studio](https://stackoverflow.com/a/17803964/6200296)

### [Class: 12](https://github.com/hasancse91/Android-Development-Course/tree/master/12-TwelfthClass)
- SQLite Database implementation with abstraction layer
	- Create Database
	- Create Table
	- Write a record (row) into table
	- Read all records from table
	- Count the number of row in table
	- Search a student by his registration number

No concrete implementation for database query. I implement here an abstraction layer for query. Activity class just call a method of an `interface` and implement a `callback` for query result.

### [Class: 13](https://github.com/hasancse91/Android-Development-Course/tree/master/13-ThirteenthClass)
- `Fragment`
- SQLite Database implementation with abstraction layer
	- Create Database
	- Create Table
	- Write a record (row) into table
	- Read all records from `student` table and show in a `RecyclerView`
	- Count the number of row in table
	- Search a student by his registration number
	- Delete a student by his registration number

### [Class: 14](https://github.com/hasancse91/Android-Development-Course/tree/master/14-FourteenthClass)
- SQLite Database implementation with abstraction layer
	- Create Database
	- Create two tables and make relationship using `Foreign Key`
	- `ON CASCADE DELETE` implementation
	- Insert operation
	- Read operation
	- Update operation
	- Delete operation
	- Count table row
- `DialogFragment` implementation

### [Class: 15](https://github.com/hasancse91/Android-Development-Course/tree/master/15-FifteenthClass)
- In App `Notification`
- `ProGuard` to prevent reverse engineering of APK

**Resources:**
- [Bengali Blog post on ProGuard](https://hellohasan.com/2017/07/12/prevent-android-app-decompile-proguard-rules/)

### [Class: 16](https://github.com/hasancse91/Android-Development-Course/tree/master/16-SixteenthClass)
- Show Google Map using `SupportMapFragment`
- Show user's current location on Map

### [Class: 17](https://github.com/hasancse91/Android-Development-Course/tree/master/17-SeventeenthClass)
- `Service` - to do any task in background
- Get user's current location (Latitude and Longitude) from GPS using `SmartLocation` library

### [Class: 18](https://github.com/hasancse91/Android-Development-Course/tree/master/18-EighteenthClass)
- `Firebase Analytics`
- `Firebase Crashlytics`
- `EventBus` Library

### [Class: 19](https://github.com/hasancse91/Android-Development-Course/tree/master/19-NineteenthClass)
- Audio Player
- Service