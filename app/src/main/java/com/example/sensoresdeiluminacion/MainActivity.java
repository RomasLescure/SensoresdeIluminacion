package com.example.sensoresdeiluminacion;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightLevelTextView;
    private TextView instructionTextView;
    private TextView timerTextView;
    private Button playAgainButton;
    private CameraManager cameraManager;
    private String cameraId;

    private boolean shouldWin; // Variable para saber si debe ganar o no
    private boolean isGameRunning = true; // Variable para saber si el juego está en ejecución

    private static final int GAME_DURATION = 5; // Duración del juego en segundos
    private int remainingTime = GAME_DURATION;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightLevelTextView = findViewById(R.id.lightLevelTextView);
        instructionTextView = findViewById(R.id.instructionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE); // Ocultar el botón "Jugar de Nuevo" al inicio

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

        // Iniciar el temporizador con duración de 5 segundos
        countDownTimer = new CountDownTimer(GAME_DURATION * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Actualizar el temporizador en pantalla cada segundo
                remainingTime = (int) (millisUntilFinished / 1000);
                timerTextView.setText("Tiempo restante: " + remainingTime);
            }

            @Override
            public void onFinish() {
                // El tiempo ha finalizado, se perdió el juego
                isGameRunning = false;
                instructionTextView.setText(""); // Eliminar el mensaje de "oscuro" o "claro"
                cameraOff(); // Apagar la cámara
                showLoseToast();
            }
        };

        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float lightLevel = event.values[0];
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            lightLevelTextView.setText("Nivel de Iluminación: " + decimalFormat.format(lightLevel));

            // Detener el temporizador si el juego ya no está en ejecución
            if (!isGameRunning) {
                countDownTimer.cancel();
                return;
            }

            if (isGameRunning && lightLevel >= 15 && shouldWin) {
                // Altos niveles de iluminación y se debe ganar
                try {
                    cameraManager.setTorchMode(cameraId, true); // Enciende el flash
                    showWinToast();
                    isGameRunning = false; // Detener el juego después de ganar
                    instructionTextView.setText(""); // Eliminar el mensaje de "oscuro" o "claro"
                    countDownTimer.cancel(); // Detener el temporizador si se ganó antes de que termine
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else if (isGameRunning && lightLevel < 15 && !shouldWin) {
                // Bajos niveles de iluminación y se debe ganar
                try {
                    cameraManager.setTorchMode(cameraId, true); // Enciende el flash
                    showWinToast();
                    isGameRunning = false; // Detener el juego después de ganar
                    instructionTextView.setText(""); // Eliminar el mensaje de "oscuro" o "claro"
                    countDownTimer.cancel(); // Detener el temporizador si se ganó antes de que termine
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            } else if (!isGameRunning) {
                // Juego detenido, apagar el flash
                cameraOff();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void showWinToast() {
        Toast.makeText(this, "¡Ganaste!", Toast.LENGTH_LONG).show();
        playAgainButton.setVisibility(View.VISIBLE); // Mostrar el botón "Jugar de Nuevo" al ganar
    }

    private void showLoseToast() {
        Toast.makeText(this, "¡Perdiste! Presiona Jugar de Nuevo para intentarlo de nuevo.", Toast.LENGTH_LONG).show();
        playAgainButton.setVisibility(View.VISIBLE); // Mostrar el botón "Jugar de Nuevo" al perder
    }

    private void cameraOff() {
        try {
            cameraManager.setTorchMode(cameraId, false); // Apagar el flash
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void updateShouldWin() {
        // Generar un nuevo valor aleatorio para shouldWin en el rango de 1 a 40
        Random random = new Random();
        shouldWin = random.nextInt(2) == 0; // 0 o 1 (true o false)
    }

    private void playAgain() {
        // Volver a generar un valor aleatorio y actualizar el mensaje
        updateShouldWin();
        instructionTextView.setText(shouldWin ? "claro" : "oscuro"); // Mostrar el mensaje "oscuro" o "claro" nuevamente
        isGameRunning = true; // Volver a habilitar el juego
        countDownTimer.start(); // Iniciar el temporizador nuevamente
        playAgainButton.setVisibility(View.INVISIBLE); // Ocultar el botón "Jugar de Nuevo" al iniciar el juego
    }
}