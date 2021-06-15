package com.upm.pasproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static android.content.Context.SENSOR_SERVICE;

public class SensorFragment extends Fragment implements SensorEventListener {

    private ConstraintLayout constraintLayout;
    private SensorManager sensorManager;
    private Sensor sensorLight;
    private Sensor sensorAccelerometer;

    // Format to 2 decimal digits
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    private DecimalFormat df = new DecimalFormat("#0.00",symbols);


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Constarint Layout to inflate fragment
        constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_sensor, null);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return constraintLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {

        switch(event.sensor.getType()){

            case Sensor.TYPE_LIGHT:

                ((TextView) constraintLayout.findViewById(R.id.text_sensorLight))
                        .setText("Medida luminosidad: "+df.format((float)event.values[0])+" lx");
            break;
            case Sensor.TYPE_ACCELEROMETER:

                // Eje X
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerX))
                        .setText("Medida acelerometro eje X: "+df.format((double)event.values[0])+" m/s^2");

                // Eje Y
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerY))
                        .setText("Medida acelerometro eje Y: "+df.format((double)event.values[1])+" m/s^2");

                // Eje Z
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerZ))
                        .setText("Medida acelerometro eje Z: "+df.format((double)event.values[2])+" m/s^2");
            break;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}