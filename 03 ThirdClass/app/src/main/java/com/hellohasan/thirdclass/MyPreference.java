package com.hellohasan.thirdclass;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreference {

    public static final String Image_URL = "image_url";

    private static MyPreference myPreference;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private MyPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("my_sharedpreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static MyPreference getPreferences(Context context) {
        if (myPreference == null)
            myPreference = new MyPreference(context);

        return myPreference;
    }

    public void setImageUrl(String imageUrl) {
        editor.putString(Image_URL, imageUrl);
        editor.apply();
        editor.commit();
    }

    public String getImageUrl() {
        return sharedPreferences.getString(Image_URL, "");
    }
}