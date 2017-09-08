package com.hellohasan.secondclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Logger.addLogAdapter(new AndroidLogAdapter());

        editText = (EditText) findViewById(R.id.edittext);
        textView = (TextView) findViewById(R.id.textView);

        //retrieve data from intent, those are sent from previous Activity
        String string = getIntent().getStringExtra("data");
        Logger.d("String " + string);

        editText.setText(string);
    }

    public void buttonClick(View view) {

        if(view.getId() ==  R.id.button1) {
            String text = "Button 1 Clicked: " + editText.getText().toString();
            textView.setText(text);
            Toast.makeText(this, "Button 1", Toast.LENGTH_SHORT).show();
        }
        else if(view.getId() == R.id.button2) {
            String text = "Button 2 Clicked: " + editText.getText().toString();
            textView.setText(text);
            Toast.makeText(this, "Button 2", Toast.LENGTH_SHORT).show();
        }
    }
}
