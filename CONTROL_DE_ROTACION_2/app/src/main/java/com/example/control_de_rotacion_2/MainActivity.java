package com.example.control_de_rotacion_2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.codesgood.views.JustifiedTextView;

public class MainActivity extends AppCompatActivity {
    JustifiedTextView justifiedTextView;
    String spaceText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        justifiedTextView = findViewById(R.id.justified_text_view);

        spaceText = getResources().getString(R.string.spaceText);
        justifiedTextView.setText(Html.fromHtml(spaceText));
    }
}