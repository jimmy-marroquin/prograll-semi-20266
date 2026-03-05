package com.example.miprimeraapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // ── Vistas ──────────────────────────────────────────────
    private Button    btnCalcular;
    private EditText  txtNum1;
    private EditText  txtNum2;
    private RadioGroup optOptions;
    private TextView  lblRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ── Referencias ─────────────────────────────────────
        btnCalcular  = findViewById(R.id.btnCalcular);
        txtNum1      = findViewById(R.id.txtNum1);
        txtNum2      = findViewById(R.id.txtNum2);
        optOptions   = findViewById(R.id.optOptions);
        lblRespuesta = findViewById(R.id.lblRespuesta);

        // ── Listener del botón ──────────────────────────────
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }

    // ── Lógica principal ────────────────────────────────────
    private void calcular() {

        // 1. Leer y limpiar entradas
        String sNum1 = txtNum1.getText().toString().trim();
        String sNum2 = txtNum2.getText().toString().trim();

        // 2. Validar campos vacíos
        if (sNum1.isEmpty() || sNum2.isEmpty()) {
            lblRespuesta.setText("⚠ Ingresa ambos números");
            return;
        }

        // 3. Validar que se eligió una operación
        int opId = optOptions.getCheckedRadioButtonId();
        if (opId == -1) {
            Toast.makeText(this, "Selecciona una operación", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(sNum1);
            double num2 = Double.parseDouble(sNum2);
            double resultado;
            String nombreOp;

            // 4. Ejecutar operación seleccionada
            if (opId == R.id.optSuma) {
                resultado  = num1 + num2;
                nombreOp   = "Suma";

            } else if (opId == R.id.optRestar) {
                resultado  = num1 - num2;
                nombreOp   = "Resta";

            } else if (opId == R.id.optMultiplicar) {
                resultado  = num1 * num2;
                nombreOp   = "Multiplicación";

            } else if (opId == R.id.optDividir) {
                // 5. Protección división entre cero
                if (num2 == 0) {
                    lblRespuesta.setText("⚠ No se puede dividir entre 0");
                    return;
                }
                resultado = num1 / num2;
                nombreOp  = "División";

            } else {
                return; // No debería llegar aquí
            }

            // 6. Formatear resultado: sin ".0" si es entero
            String resFormateado = (resultado == Math.floor(resultado) && !Double.isInfinite(resultado))
                    ? String.valueOf((long) resultado)
                    : String.format("%.4f", resultado).replaceAll("0+$", "").replaceAll("\\.$", "");

            // 7. Mostrar resultado
            lblRespuesta.setText(nombreOp + ": " + num1 + " y " + num2 + " = " + resFormateado);

        } catch (NumberFormatException e) {
            lblRespuesta.setText("⚠ Ingresa solo números válidos");
        }
    }
}
