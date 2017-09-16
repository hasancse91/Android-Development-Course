# Android Ninja - Class No: 7
Date: 16 September, 2017

## Topics ##
- `RecyclerView` - show a movie list with image (using `Picasso` Library). Data will come from server using [`Retrofit`](https://github.com/hasancse91/Android-Development-Course/tree/master/05-FifthClass)
- `RecyclerView` - item click event listen (listen every component click event of the `RecyclerView` item)
- `RecyclerView` will be refreshable using `SwipeRefreshLayout`
- Check network state (is internet available) using `ConnectivityManager` and `NetworkInfo` classes before any network call
- Show `ProgressDialog` when first time call to network for movie list
- Pass *Movie Object* from `MovieListActivity` to `MovieDetailsActivity` using `Intent` and show the `Movie` data in `MovieDetailsActivity`
- Enable back button on `ActionBar` of `MovieDetailsActivity`
- Enable **Click Again to Exit** feature in `MovieListActivity` when device's back button pressed