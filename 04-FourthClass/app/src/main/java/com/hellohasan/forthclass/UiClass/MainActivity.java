/**
 * Android Ninja - Class No: 4
 * Date: 26 August, 2017
 *
 * Topics:
 - `ButterKnife` Library to reduce boilerplate code
 - Phone call
 - Run time permission for phone call
 - Send a POST request to a PHP server and receive a response: https://hellohasan.com/2016/12/03/android-retrofit-get-post-method/
 - Update UI from another class using `interface`
 */

package com.hellohasan.forthclass.UiClass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hellohasan.forthclass.ModelClass.DataModel;
import com.hellohasan.forthclass.ModelClass.ResponseModel;
import com.hellohasan.forthclass.NetworkRelatedClass.NetworkCall;
import com.hellohasan.forthclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.phoneEditText)
    EditText phoneEditText;
    @BindView(R.id.senderNameEditText)
    EditText senderEditText;
    @BindView(R.id.ageEditText)
    EditText ageEditText;
    @BindView(R.id.serverResponseTextView)
    TextView responseTextView;

    private AlphaAnimation buttonClickAnimation = new AlphaAnimation(1F, 0.002F);
    private final int REQUEST_PHONE_CALL = 19987;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
    //end of onCreate() method

    //button click event to send request to server
    public void sendDataToServer(View view) {
       // send implementation of interface as a parameter of constructor
       NetworkCall networkCall =  new NetworkCall(new CallBackInterface() {
           @Override
           public void uiUpdate(ResponseModel responseModel) {
               //when uiUpdate() method will be called from NetworkCall class
               //textView will update
               responseTextView.setText(responseModel.getMessage());
           }
       });

        //get text from EditText and store into two strings
        String senderName = senderEditText.getText().toString();
        String age = ageEditText.getText().toString();

        if(senderName.isEmpty() || age.isEmpty())
            Toast.makeText(this, "Enter name and age", Toast.LENGTH_SHORT).show();
        else
            networkCall.sendData(new DataModel(senderName, Integer.parseInt(age))); //call method to send request to server
    }
    //end of server call

    /**
     * Start phone call related tasks
     */
    //phone icon click event
    public void makePhoneCall(View view) {
        view.startAnimation(buttonClickAnimation); //show button effect animation

        callNow(); //check permission and make call
    }

    // first check self permission. if not granted then ask for permission
    // otherwise make phone call
    private void callNow() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            return;
        }
        String telephone = "tel:" + phoneEditText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(telephone));
        startActivity(intent);
    }

    //when user `Allow` or `Deny` the permission this method will call
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PHONE_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callNow(); //user allowed phone call so make a call
            }
        }
    }
    /**
     * End of phone call and run time permission
     */
}
