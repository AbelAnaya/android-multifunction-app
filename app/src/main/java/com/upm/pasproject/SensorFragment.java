package com.upm.pasproject;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static android.content.Context.SENSOR_SERVICE;

public class SensorFragment extends Fragment implements SensorEventListener {

    private ConstraintLayout constraintLayout;
    private SensorManager sensorManager;
    private android.hardware.Sensor sensorLight;
    private android.hardware.Sensor sensorAccelerometer;

    // Format to 2 decimal digits
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    private DecimalFormat df = new DecimalFormat("#0.00",symbols);
    private FirebaseDatabase db;
    private DatabaseReference reference;

    public String luminosidad;
    public String acelerometroX;
    public String acelerometroY;
    public String acelerometroZ;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Constarint Layout to inflate fragment
        constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_sensor, null);

        Button saveButton = (Button)constraintLayout.findViewById(R.id.save_firebase);

        View.OnClickListener saveFirebaseListener;

        saveButton.setOnClickListener(saveFirebaseListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                saveFirebase();
            }
        });

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        sensorLight = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_LIGHT);
        sensorAccelerometer = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);

        return constraintLayout;
    }

    private void saveFirebase() {
        db = FirebaseDatabase.getInstance();
        TextView userEmail = (TextView) getActivity().findViewById(R.id.userEmail);
        String user = userEmail.getText().toString().split("@")[0];
        reference = db.getReference("users/"+userEmail.getText().toString().split("@")[0]);

        reference.child("Luminosidad").setValue(this.luminosidad);
        reference.child("Acelerometro Eje X").setValue(this.acelerometroX);
        reference.child("Acelerometro Eje Y").setValue(this.acelerometroY);
        reference.child("Acelerometro Eje Z").setValue(this.acelerometroZ);

        // Notify data has been saved
        showAlert();

    }

    private void showAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Firebase Message");
        builder.setMessage("Los valores se han guardado correctamente");
        builder.setPositiveButton("OK",null);
        AlertDialog dialog = builder.create();

        dialog.show();
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

            case android.hardware.Sensor.TYPE_LIGHT:

                ((TextView) constraintLayout.findViewById(R.id.text_sensorLight))
                        .setText("Medida luminosidad: "+df.format((float)event.values[0])+" lx");

                this.luminosidad = df.format((float)event.values[0]);

            break;
            case android.hardware.Sensor.TYPE_ACCELEROMETER:

                // Eje X
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerX))
                        .setText("Medida acelerometro eje X: "+df.format((double)event.values[0])+" m/s^2");

                this.acelerometroX = df.format((double)event.values[0]);

                // Eje Y
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerY))
                        .setText("Medida acelerometro eje Y: "+df.format((double)event.values[1])+" m/s^2");

                this.acelerometroY = df.format((double)event.values[1]);

                // Eje Z
                ((TextView) constraintLayout.findViewById(R.id.text_sensorAccelerometerZ))
                        .setText("Medida acelerometro eje Z: "+df.format((double)event.values[2])+" m/s^2");

                this.acelerometroZ = df.format((double)event.values[2]);

            break;
        }
    }

    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {

    }
}