package com.example.sensoresdeiluminacion;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightLevelTextView;
    private TextView instructionTextView;
    private Button playAgainButton;
    private CameraManager cameraManager;
    private String cameraId;

    private boolean shouldWin; // Variable para saber si debe ganar o no
    private boolean isGameRunning = true; // Variable para saber si el juego está en ejecución

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightLevelTextView = findViewById(R.id.lightLevelTextView);
        instructionTextView = findViewById(R.id.instructionTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGameRunning) {
                    playAgain();
                }
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        // Generar un valor aleatorio inicial para shouldWin (true: ganar, false: perder)
        updateShouldWin();

        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightLevel = event.values[0];
            lightLevelTextView.setText("Nivel de Iluminación: " + lightLevel);

            if (isGameRunning && lightLevel < 16 && shouldWin) {
                // Bajos niveles de iluminación y se debe ganar
                try {
                    cameraManager.setTorchMode(cameraId, true); // Enciende el flash
                    showWinToast();
                    isGameRunning = false; // Detener el juego después de ganar
                    instructionTextView.setText(""); // Eliminar el mensaje de "oscuro" o "claro"
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else if (isGameRunning && lightLevel >= 16 && !shouldWin) {
                // Altos niveles de iluminación y se debe ganar
                try {
                    cameraManager.setTorchMode(cameraId, true); // Enciende el flash
                    showWinToast();
                    isGameRunning = false; // Detener el juego después de ganar
                    instructionTextView.setText(""); // Eliminar el mensaje de "oscuro" o "claro"
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else if (!isGameRunning) {
                // Juego detenido, apagar el flash
                try {
                    cameraManager.setTorchMode(cameraId, false); // Apaga el flash
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void showWinToast() {
        Toast.makeText(this, "¡Ganaste!", Toast.LENGTH_LONG).show();
    }

    private void updateShouldWin() {
        // Generar un nuevo valor aleatorio para shouldWin
        Random random = new Random();
        shouldWin = random.nextBoolean();
    }

    private void playAgain() {
        // Volver a generar un valor aleatorio y actualizar el mensaje
        updateShouldWin();
        instructionTextView.setText(shouldWin ? "claro" : "oscuro"); // Mostrar el mensaje "oscuro" o "claro" nuevamente
        isGameRunning = true; // Volver a habilitar el juego
    }
}
