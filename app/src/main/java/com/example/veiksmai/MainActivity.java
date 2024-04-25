package com.example.veiksmai;

import static com.example.veiksmai.R.id.editTextActionBar;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText editOperations;
    private TextView textDescription;
    private TextView editTextNumberDecimal;
    private TextView textResult;
   boolean isResult = false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            editOperations = findViewById(R.id.editTextActionBar);
            textDescription = findViewById(R.id.textViewDescription);
            textDescription.setText("Enter the actions you want to perform with the numbers. \n" + "The actions must be separated by a space. \n" + "The actions can be: +, -, *, /, %, ^, s = sqrt, ! ");
            textDescription.setVisibility(View.INVISIBLE);
            textResult = findViewById(R.id.textViewResult);
            String Result = calcResult(editOperations.getText().toString());
            if (parseDouble(Result) != 0) {
                textResult.setVisibility(View.VISIBLE);
            } else {
                textResult.setVisibility(View.INVISIBLE);
            }

            textResult.setText("Result: " + Result);
            editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
            editTextNumberDecimal.setVisibility(View.INVISIBLE);

            Button buttonPlus = findViewById(R.id.plusButton);
            Button buttonMinus = findViewById(R.id.minusButton);
            Button buttonMultiply = findViewById(R.id.multiplyButton);
            Button buttonDivision = findViewById(R.id.divisionButton);
            Button buttonDegree = findViewById(R.id.degreeButton);
            Button buttonFactorial = findViewById(R.id.factorialButton);
            Button buttonSqrt = findViewById(R.id.sqrtButton);
            Button buttonResult = findViewById(R.id.resultButton);
            Button buttonClear = findViewById(R.id.clearButton);

            buttonPlus.setOnClickListener(v1 -> {
                editOperations.setText(editOperations.getText() + " + ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonMinus.setOnClickListener(v12 -> {
                editOperations.setText(editOperations.getText() + " - ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonMultiply.setOnClickListener(v13 -> {
                editOperations.setText(editOperations.getText() + " * ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonDivision.setOnClickListener(v14 -> {
                editOperations.setText(editOperations.getText() + " / ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonDegree.setOnClickListener(v15 -> {
                editOperations.setText(editOperations.getText() + " ^ ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonFactorial.setOnClickListener(v16 -> {
                editOperations.setText(editOperations.getText() + " ! ");
                editOperations.setSelection(editOperations.getText().length());
            });

            buttonSqrt.setOnClickListener(v17 -> {
                editOperations.setText(editOperations.getText() + " sqrt ");
                editOperations.setSelection(editOperations.getText().length());
            });
            buttonResult.setOnClickListener(v18 -> {
                if (isResult) {

                    String Result1 = calcResult(editOperations.getText().toString());
                    float Results = (float) Math.round(Float.parseFloat(Result1) * 1000) /1000;
                    editOperations.setText(editOperations.getText()+ " = " + Results);
                    editOperations.setSelection(editOperations.getText().length());
                    isResult = false;
                }else {
                    editOperations.setText("");
                }
            });
            buttonClear.setOnClickListener(v19 -> {
                editOperations.setText("");
            });

            return insets;
        });
    }

    private String calcResult(String operations) {
        String[] actions = operations.split(" ");
        Log.d("Actions", Arrays.toString(actions));
        double result = 0;
        isResult = true;
        for (int i = 0; i < actions.length; i++) {
            if (actions[i].equals("+")) {
                Log.d("Actions", "i " + actions[i]);
                result = Double.parseDouble(actions[0]) + Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("-")) {
                result = Double.parseDouble(actions[0]) - Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("*")) {
                result = Double.parseDouble(actions[0]) * Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("/")) {
                result = Double.parseDouble(actions[0]) / Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("%")) {
                result = Double.parseDouble(actions[0]) % Double.parseDouble(actions[i + 1]);
            } else if (actions[i].equals("^")) {
                result = Math.pow(Double.parseDouble(actions[0]), Double.parseDouble(actions[i + 1]));
            } else if (actions[i].equals("sqrt")) {
                result = Math.sqrt(Double.parseDouble(actions[0]));
            } else if (actions[i].equals("!")) {
                result = factorial(Double.parseDouble(actions[0]));
            }
        }
        return String.valueOf(result);
    }

    private double factorial(double result) {
        if (result == 0) {
            return 1;
        } else {
            return result * factorial(result - 1);
        }
    }

}