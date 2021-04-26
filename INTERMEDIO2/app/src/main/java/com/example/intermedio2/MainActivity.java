package com.example.intermedio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtAngulo;
    SeekBar skAngulo;
    EditText editRadio;
    Float Volumen, Radio;
    Button btnCalcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtAngulo = findViewById(R.id.txtAngulo);
        skAngulo = findViewById(R.id.skAngulo);
        editRadio= findViewById(R.id.editRadio);
        btnCalcular= findViewById(R.id.btnCalcular);

    }

    @Override
    protected void onStart() {
        super.onStart();

        skAngulo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtAngulo.setText(progress+"°");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Radio= Float.parseFloat(editRadio.getText().toString());

                Volumen= (2.0f / 3.0f) * Radio * skAngulo.getProgress() * 3.0f;
                Toast.makeText(MainActivity.this, "El resultado es "+Volumen, Toast.LENGTH_SHORT).show();
            }
        });
        editRadio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        });
    }

}