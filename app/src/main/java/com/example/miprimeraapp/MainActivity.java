package com.example.miprimeraapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Button btnCalculator;
    private EditText txtNum1;
    private EditText txtNum2;
    private TextView lblRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCalculator = findViewById(R.id.btnCalcular);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        lblRespuesta = findViewById(R.id.lblRespuesta);

        1
        btnCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            String num1Str = txtNum1.getText().toString();
            String num2Str = txtNum2.getText().toString();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                lblRespuesta.setText("¡Ingresa ambos números!");
                return;
            }

            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double resultado = num1 + num2;

            lblRespuesta.setText("Respuesta: " + resultado);

        } catch (NumberFormatException e) {
            lblRespuesta.setText("Error: Números inválidos");
        }
    }
}