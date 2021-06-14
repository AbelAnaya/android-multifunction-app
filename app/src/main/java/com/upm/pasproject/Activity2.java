package com.upm.pasproject;

import android.database.DataSetObserver;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ListView listView = (ListView) findViewById(R.id.listview);

        List<HashMap<String,String>> datos = new ArrayList<>();

        for(int i=0; i < 100; i++){
            HashMap<String,String> hm = new HashMap<>();
            hm.put("nombre","Fernando "+i);
            hm.put("apellido","de Mingo "+i);
            hm.put("dni",""+i*10);
            datos.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,
                datos, //datos
                R.layout.row, //layout
                new String[] {"nombre","apellido","dni"}, //from
                new int[] {R.id.nombre, R.id.apellido, R.id.dni} //to
                );

        listView.setAdapter(adapter);

    }
}