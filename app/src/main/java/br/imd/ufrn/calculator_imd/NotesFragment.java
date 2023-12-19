package br.imd.ufrn.calculator_imd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class NotesFragment extends Fragment {

    private EditText etFirstNote;
    private EditText etSecondNote;
    private EditText etThirdNote;
    private TextView tvResult;

    public NotesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        etFirstNote = view.findViewById(R.id.etFirstNote);
        etSecondNote = view.findViewById(R.id.etSecondNote);
        etThirdNote = view.findViewById(R.id.etThirdNote);
        Button btnCalculate = view.findViewById(R.id.btnCalculate);
        tvResult = view.findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> calculateResult());

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void calculateResult() {
        double firstNote = Double.parseDouble(etFirstNote.getText().toString());
        double secondNote = Double.parseDouble(etSecondNote.getText().toString());
        double thirdNote = Double.parseDouble(etThirdNote.getText().toString());

        double average = (firstNote + secondNote + thirdNote) / 3;

        if (average >= 7.0) {
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGreenDark));
            tvResult.setText(String.format("Aprovado! Média: %.2f", average));
            showToast(String.format("Aprovado! Média: %.2f", average));
        } else if (average >= 5.0) {
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorYellow));
            tvResult.setText(String.format("Aprovado por nota! Média: %.2f", average));
            showToast(String.format("Aprovado por nota! Média: %.2f", average));
        } else {
            tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorRedDark));
            tvResult.setText(String.format("Reprovado! Média: %.2f", average));
            showToast(String.format("Reprovado! Média: %.2f", average));
        }
    }
    private void showToast(String message, Object... args) {
        Toast.makeText(requireContext(), String.format(message, args), Toast.LENGTH_SHORT).show();
    }
}
