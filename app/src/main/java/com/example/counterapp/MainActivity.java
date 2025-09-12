package com.example.counterapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button addButton;
    Button resetButton;
    int count = 0;

    private static final String TEXT_VALUE = "text_val";
    private static final String COUNT_VALUE = "count_val";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        addButton = findViewById(R.id.addButton);
        resetButton = findViewById(R.id.resetButton);

        // restore the saved state on screen rotation
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString(TEXT_VALUE);
            if (savedText != null) {
                textView.setText(savedText);
            }
            count = savedInstanceState.getInt(COUNT_VALUE, 0);
        } else {
            textView.setText(R.string.default_counter_label);
        }

        // add button on click listener -- increment count var and display in textView
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textView.setText(getString(R.string.counter_label, count));

                if (count >= 10) {
                    textView.setTextColor(Color.RED);
                }
            }
        });

        // reset button on click listener -- set count var to 0 and update textView
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                textView.setText(R.string.default_counter_label);
                textView.setTextColor(Color.BLACK);
            }
        });
    }

    // Save current states for textView label and count var
    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(TEXT_VALUE, textView.getText().toString());
        savedInstanceState.putInt(COUNT_VALUE, count);
    }
}