package com.example.intermedio_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
SeekBar skbPuntos, skbCalifa;
TextView txtPuntos1, txtPuntos2, txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        skbPuntos = findViewById(R.id.skbPuntos);
        skbCalifa = findViewById(R.id.skbCalifa);
        txtPuntos1 = findViewById(R.id.txtPuntos1);
        txtPuntos2 = findViewById(R.id.txtPuntos2);
        txtResultado = findViewById(R.id.txtResultado);

        skbPuntos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //VALOR QUE SE MUESTRA CUANDO ARRASTRAMOS
                txtPuntos1.setText("Puntos para acreditar: "+String.valueOf(i));
                if (skbCalifa.getProgress()>= skbPuntos.getProgress()){
                    txtResultado.setText("ACREDITADO!!!");
                }else {
                    txtResultado.setText("REPROBADO :c");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Este es cuando se empieza a arrastrar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Este es cuando se suelta
            }
        });

        skbCalifa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                txtPuntos2.setText("Puntos acreditados: "+String.valueOf(i));
                if (skbCalifa.getProgress()>= skbPuntos.getProgress()){
                    txtResultado.setText("APROBADO!!!");
                }else {
                    txtResultado.setText("REPROBADO :c");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}