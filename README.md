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
**Resources: **
- [Android Activity Lifecycle - Android official documentation](https://developer.android.com/guide/components/activities/activity-lifecycle.html)
- [Activity Lifecycle - Tutorials Point](https://www.javatpoint.com/android-life-cycle-of-activity)
- [Dimension](https://developer.android.com/guide/topics/resources/more-resources.html#Dimension)
- [Managing Screen Sizes](https://android-developers.googleblog.com/2011/07/new-tools-for-managing-screen-sizes.html)
- [Different values folder for different screens](https://stackoverflow.com/a/32861248/6200296)
- [Supporting Different Screen Sizes - Android official documentation](https://developer.android.com/training/multiscreen/screensizes.html)