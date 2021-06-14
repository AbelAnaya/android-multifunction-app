package com.upm.pasproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private String APIURL =
        "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ListView listView = (ListView) findViewById(R.id.listview);

        List<HashMap<String,String>> datos = new ArrayList<>();

        HashMap<String,String> hm = new HashMap<>();
        for (int i = 0; i<15;i++){
            hm.put("crypto","Ethereum (ETH)");
            hm.put("value",""+(8023.65+1000*i)+"$");
            hm.put("icon_url", "https://cryptoicons.org/api/black/eth/200");
            datos.add(hm);
        }

        SimpleAdapter adapter = new MiSimpleAdapter(this,
                datos, //datos
                R.layout.row, //layout
                new String[] {"crypto","value", "icon_url"}, //from
                new int[] {R.id.crypto, R.id.value, R.id.icon_url} //to
        );

        listView.setAdapter(adapter);
    }
}