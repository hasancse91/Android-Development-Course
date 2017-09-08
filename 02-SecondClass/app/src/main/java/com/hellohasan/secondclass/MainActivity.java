/**
 * Android Ninja - Class No: 2 [19 August, 2017]
 * Topics:
 * - ConstraintLayout
 * - TextView
 * - EditText
 * - Button
 * - Click listener Java method
 * - Click listener XML attribute
 * - Toast
 * - Intent (start another activity)
 * - Pass values from one activity to another activity
 * - Reusable XML layout (using `include` tag)
 * - Using third party library (Logger Library: https://hellohasan.com/2017/05/23/android-development-pretty-logger-library/)
 */

package com.hellohasan.secondclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView thirdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        thirdTextView = (TextView) findViewById(R.id.third_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thirdTextView.setText("Button Clicked");

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("data", "Java");
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
