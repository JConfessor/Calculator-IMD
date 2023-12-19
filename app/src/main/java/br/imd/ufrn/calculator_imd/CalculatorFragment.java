package br.imd.ufrn.calculator_imd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {

    private TextView display;
    private StringBuilder currentInput;
    private double operand1;
    private double operand2;
    private char operator;

    public CalculatorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        display = view.findViewById(R.id.display);
        currentInput = new StringBuilder();
        operand1 = 0;
        operand2 = 0;
        operator = ' ';

        setNumberButtonClickListeners(view);

        setOperatorButtonClickListeners(view);

        view.findViewById(R.id.btnDel).setOnClickListener(v -> deleteLastInput());

        // Set click listener for EQUAL button
        view.findViewById(R.id.btnEqual).setOnClickListener(v -> performOperation());

        return view;
    }

    private void setNumberButtonClickListeners(View view) {
        for (int i = 0; i <= 9; i++) {
            String buttonId = "btn" + i;
            @SuppressLint("DiscouragedApi") int resId = getResources().getIdentifier(buttonId, "id", requireActivity().getPackageName());

            Button button = view.findViewById(resId);
            if (button != null) {
                button.setOnClickListener(v -> appendToInput(((Button) v).getText().toString()));
            }
        }
    }

    private void setOperatorButtonClickListeners(View view) {
        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};

        for (int resId : operatorButtonIds) {
            Button button = view.findViewById(resId);
            if (button != null) {
                button.setOnClickListener(v -> handleOperator(((Button) v).getText().charAt(0)));
            }
        }
    }
    private void appendToInput(String value) {
        currentInput.append(value);
        updateDisplay();
    }

    private void deleteLastInput() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            updateDisplay();
        }
    }

    private void handleOperator(char newOperator) {
        if (currentInput.length() > 0) {
            operand1 = Double.parseDouble(currentInput.toString());
            operator = newOperator;
            currentInput.setLength(0);
            updateDisplay();
        }
    }

    private void performOperation() {
        if (currentInput.length() > 0) {
            operand2 = Double.parseDouble(currentInput.toString());

            double result = 0;
            boolean invalidOperation = false;

            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        invalidOperation = true;
                    }
                    break;
            }

            if (!invalidOperation) {
                currentInput.setLength(0);
                currentInput.append(result);
                updateDisplay();
            } else {
                Toast.makeText(requireContext(), "Operação não permitida (divisão por zero)", Toast.LENGTH_SHORT).show();
            }

            operand1 = 0;
            operand2 = 0;
            operator = ' ';
        }
    }

    private void updateDisplay() {
        display.setText(currentInput.toString());
    }
}
