package com.example.onememory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void submit(View view) {
        EditText editText = findViewById(R.id.help_feedback);
        Toast.makeText(feedback.this, "提交反馈：" + editText.getText(), Toast.LENGTH_SHORT).show();
    }
}
