package com.hellohasan.ninthclass.ImageCapture;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.hellohasan.ninthclass.R;
import com.hellohasan.ninthclass.WebScraping.WebScrapingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageCaptureActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    // Activity request codes
    private static final int CAPTURE_IMAGE_REQUEST_CODE = 7189;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_capture);
        ButterKnife.bind(this);

        checkCameraPermission();
    }


    private void checkCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 112);
            return;
        }
    }

    public void takeImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_REQUEST_CODE);//7181
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAPTURE_IMAGE_REQUEST_CODE){
            Uri imageUri = data.getData();

            imageView.setImageURI(imageUri);

        }
    }

    public void goSecondActivity(View view) {
        startActivity(new Intent(this, WebScrapingActivity.class));
    }
}
