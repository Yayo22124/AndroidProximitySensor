package com.example.proximitysensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // SensorManager
    private SensorManager sensorManager;
    private Sensor proximity;
    private TextView sensorResult;
    private RelativeLayout mainLayout; // Nueva variable para el RelativeLayout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorResult = findViewById(R.id.sensorResult);
        mainLayout = findViewById(R.id.relativeLayout); // Asigna la referencia del RelativeLayout

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_FASTEST);
    }

    // Get distance
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent event) {
        float distance = event.values[0];

        if (distance <= 5) {
            // Cambia el color de fondo del RelativeLayout y el texto del TextView
            mainLayout.setBackgroundColor(Color.RED); // Cambia el color según tus preferencias
            sensorResult.setText("Objeto cercano");
        } else {
            // Restaura el color de fondo y el texto
            mainLayout.setBackgroundColor(Color.parseColor("#090A14")); // Color original
            sensorResult.setText("Nada cerca");
        }
    }

}