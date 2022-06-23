package com.mastercoding.mp5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToCart extends AppCompatActivity {
    public static final String TAG = "AddToCart";
    public static String KEY_TO_ADD_MEAL = "com.mastercoding.mp5.add_to_list";
    Intent intent;
    EditText editText;
    Button btn;
    String meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Log.d(TAG, "onCreate: Start");

        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn_add_to_cart);


        Log.d(TAG, "onCreate: End");
    }

    public void onClick(View view) {
        Log.d(TAG, "onClick: Start");
        meal = editText.getText().toString();
        if (meal.length() > 0) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra(KEY_TO_ADD_MEAL, meal);
            setResult(RESULT_OK, intent);
            finish();
        }
        Log.d(TAG, "onClick: End");
    }
}