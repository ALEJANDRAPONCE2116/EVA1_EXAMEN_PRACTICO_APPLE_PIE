package com.example.intermedio3_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnA1, btnA2, btnA3, btnB1, btnB2, btnB3, btnC1, btnC2, btnC3, btnReiniciar;
    TextView txtVwGanador;
    int jugadorO = 1;
    int jugadorx = 0;
    int jugadorActual = jugadorx;
    //arreglos con posiciones ocupadas
    int[] posOcu = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    boolean juegoAct = true;
    boolean ganador = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*headerText = findViewById(R.id.header_text);
        headerText.setText("O turn");*/

        //conecta variables con interfaz grafica
        btnA1 = findViewById(R.id.btnA1);
        btnA2 = findViewById(R.id.btnA2);
        btnA3 = findViewById(R.id.btnA3);
        btnB1 = findViewById(R.id.btnB1);
        btnB2 = findViewById(R.id.btnB2);
        btnB3 = findViewById(R.id.btnB3);
        btnC1 = findViewById(R.id.btnC1);
        btnC2 = findViewById(R.id.btnC2);
        btnC3 = findViewById(R.id.btnC3);
        txtVwGanador = findViewById(R.id.txtVwGanador);
        btnReiniciar = findViewById(R.id.btnReiniciar);

        btnA1.setOnClickListener(this);
        btnA2.setOnClickListener(this);
        btnA3.setOnClickListener(this);
        btnB1.setOnClickListener(this);
        btnB2.setOnClickListener(this);
        btnB3.setOnClickListener(this);
        btnC1.setOnClickListener(this);
        btnC2.setOnClickListener(this);
        btnC3.setOnClickListener(this);
        btnReiniciar.setOnClickListener(this);

        btnA1.setText("-");
        btnA2.setText("-");
        btnA3.setText("-");
        btnB1.setText("-");
        btnB2.setText("-");
        btnB3.setText("-");
        btnC1.setText("-");
        btnC2.setText("-");
        btnC3.setText("-");
    }

    @Override
    public void onClick(View v) {
        // Cuando se presionan los botones
        if (!juegoAct)
            return;

        //boton para cambiar los - por X y O
        Button btnClick = findViewById(v.getId());
        //variable que ve los turnos de los jugadores
        int etClk = Integer.parseInt(v.getTag().toString());

        //inhabilita boton una vez que se da click en el, usando el arreglo posOcu, que lo que hace es
        if (posOcu[etClk] != -1) {
            return;
        }

        //jugador que tiene el turno
        posOcu[etClk] = jugadorActual;

        if (jugadorActual == jugadorO) {
            btnClick.setText("O");
            jugadorActual = jugadorx;
        } else {
            btnClick.setText("X");
            jugadorActual = jugadorO;
        }

        comprobarGan();

        //Boton que reinicia la partida
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*finish();
                startActivity(getIntent());*/

                //Jugador X inicia partida
                jugadorActual = jugadorx;
                posOcu = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
                btnA1.setText("-");
                btnA2.setText("-");
                btnA3.setText("-");
                btnB1.setText("-");
                btnB2.setText("-");
                btnB3.setText("-");
                btnC1.setText("-");
                btnC2.setText("-");
                btnC3.setText("-");
                txtVwGanador.setText("GANADOR");
                juegoAct = true;
                ganador=false;
            }
        });
    }

    private void comprobarGan() {
        //METODO CON COMBINACIONES GANADORAS Y JUGADPR GANADOR
        int[][] posGanadoras = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < 8; i++) {
            /* 0 1 2     {0,1,2}= x x x  {3,4,5}= - - -  {6,7,8}= - - -  {0,3,6}= x - - {0,4,8}= x - -
               3 4 5              - - -           x x x           - - -           x - -          - x -
               6 7 8              - - -           - - -           x x x           x - -          - - x
            * */


            int val0 = posGanadoras[i][0];
            int val1 = posGanadoras[i][1];
            int val2 = posGanadoras[i][2];
            if (posOcu[val0] == posOcu[val1] && posOcu[val1] == posOcu[val2]) {

                //prohibe que una vez que se complete una jugada, se pueda clickear en los botones que no fueron utilizados
                //si este if no existiera, los turnos despues de ganar seguirian
                if (posOcu[val0] != -1) {
                    //false porque termina el juego actual y sale ganador
                    juegoAct = false;
                }
                //si gana jugador O
                if (posOcu[val0] == jugadorO || posOcu[val1] == jugadorO || posOcu[val2] == jugadorO) {
                    ganador = true;
                    txtVwGanador.setText("GANA O");
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("GANADOR!!");
                    builder.setMessage("El ganador es O");
                    builder.setPositiveButton("ACEPTAR", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                //Si gana jugador X
                else if (posOcu[val0] == jugadorx || posOcu[val1] == jugadorx || posOcu[val2] == jugadorx) {
                    ganador = true;
                    txtVwGanador.setText("GANA X");
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("GANADOR!!");
                    builder.setMessage("El ganador es X");
                    builder.setPositiveButton("ACEPTAR", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        }

    }
}