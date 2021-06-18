package com.example.calculatorgb;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String KEY = "MEMORY_SCREEN";
    private TextView display;
    private Calculator calculator;
    private Button resetBtn;
    private Button eraseBtn;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY, calculator);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        display = findViewById(R.id.screen);
        resetBtn = findViewById(R.id.reset);
        eraseBtn = findViewById(R.id.erase);

        int[] numberIds = new int[]{
                R.id.one_btn,
                R.id.two_btn,
                R.id.three_btn,
                R.id.four_btn,
                R.id.five_btn,
                R.id.six_btn,
                R.id.seven_btn,
                R.id.eight_btn,
                R.id.nine_btn,
                R.id.zero_btn,
                R.id.point};
        int[] operationIds = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.divide,
                R.id.multiply,
                R.id.equals};

        calculator = new Calculator();
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY)) {
            calculator = (Calculator) savedInstanceState.getSerializable(KEY);
        } else {
            calculator = new Calculator();
        }
        display.setText(calculator.getText());

        View.OnClickListener numberClickListener = v -> {
            calculator.onNumberPressed(v.getId());
            display.setText(calculator.getText());
        };

        View.OnClickListener actionClickListener = v -> {
            calculator.onActionPressed(v.getId());
            display.setText(calculator.getText());
        };

        resetBtn.setOnClickListener(v -> {
            calculator.reset();
            display.setText(getString(R.string.zero));
        });
        eraseBtn.setOnClickListener(v -> {
            calculator.backspace();
            display.setText(calculator.getText());
        });

        for (int numberId : numberIds) {
            findViewById(numberId).setOnClickListener(numberClickListener);

        }
        for (int operationId : operationIds) {
            findViewById(operationId).setOnClickListener(actionClickListener);

        }

    }


}