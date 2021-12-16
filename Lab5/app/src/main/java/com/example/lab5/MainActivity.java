package com.example.lab5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher launcher = registerForActivityResult( new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if(data != null) handleResult(data);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*** RECIVING ***/
    private void handleResult(Intent result) {
        TextView operationText = findViewById(R.id.operationText);
        TextView resultText = findViewById(R.id.resultText);
        operationText.setText(result.getStringExtra(Intent.EXTRA_TEXT));
        resultText.setText(String.valueOf(result.getIntExtra("result", 0)));
    }

    /*** SENDING ***/
    public void run(View view) {
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_TEXT, gatherData());
        intent.setAction("Lab5");
        intent.setType("text/plain");
        launcher.launch(intent);
    }

    private int [] gatherData() {
        TextView arg1 = findViewById(R.id.arg1);
        TextView arg2 = findViewById(R.id.arg2);
        return new int[] {
                getNumber(arg1),
                getNumber(arg2)
        };
    }

    private int getNumber(TextView tv) {
        return getNumber(tv.getText().toString());
    }

    private int getNumber(String text) {
        return Integer.parseInt(nullCheck(text));
    }

    private String nullCheck(String text) {
        return text.equals("") ? "0" : text;
    }

}