package com.upm.pasproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.cajaTexto); // Operacion costosa, no hacer mucho
        textView.setText(R.string.hello);
    }

    public void sendIntent(View v){
        Log.e("BUTTON", "Se ha pulsado el boton, sending Intent");


        // Esto es util porque puedo pasar a otro activity de mi app u otro app por ejemplo
        // Asi puedo cambiar de "vista"

        // Esto manda un mensaje al SO para decirle que envie un mensaje INTENT

        //Este mensaje INTENT va a activar aquellas activities almacenadas en el telefono
        // cuyo intent filter incluya la category y la action que le envio

        Intent phoneCalltoSO = new Intent(); // Creacion de objeto de clase Intent

        // Otra forma de hacerlo es enviar un intent con un (from, to)
        Intent phoneCallFromApp = new Intent(this, Activity2.class); // Creacion de objeto de clase Intent con (from,to)

        phoneCalltoSO.setAction(Intent.ACTION_CALL); // Constructor de atributo action (solo hay un action por eso es set)
        phoneCalltoSO.addCategory(Intent.CATEGORY_DEFAULT); // Añadir category. (Puede haber varias por eso es add y no set)

        //startActivity(phoneCalltoSO);
        startActivity(phoneCallFromApp); // Cambio de activity desde mi app. De esta forma no tengo que declarar un intent-filter al activity
        // al que quiero cambiar y sólo puedo acceder desde mi app. No habrá aplicaciones externas que puedan lanzarla con un Intent de action y category
    }
}