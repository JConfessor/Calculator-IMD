package br.imd.ufrn.calculator_imd;

import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ToggleButton tbCalculator;
    private ToggleButton tbNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbCalculator = findViewById(R.id.tbCalculator);
        tbNotes = findViewById(R.id.tbNotes);

        loadFragment(new CalculatorFragment());

        tbCalculator.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                loadFragment(new CalculatorFragment());
                tbNotes.setChecked(false);
            }
        });

        tbNotes.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                loadFragment(new NotesFragment());
                tbCalculator.setChecked(false);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
