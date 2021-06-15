package com.upm.pasproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.upm.pasproject.rest.CoinGeckoAPI;
import com.upm.pasproject.rest.models.Crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CryptoFragment extends Fragment {

    private ConstraintLayout constraintLayout;
    private ListView listView;
    private List<HashMap<String,String>> datos;
    private SimpleAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Constarint Layout to inflate fragment
        constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_crypto,null);

        // ListView object to manipulate the list
        listView = (ListView) constraintLayout.findViewById(R.id.listview);
        // Data for List
        datos = new ArrayList<>();
        // Adapter for ListView
        adapter = new MiSimpleAdapter(container.getContext(),
                datos, //datos
                R.layout.row, //layout
                new String[] {"crypto","value", "icon_url"}, //from
                new int[] {R.id.crypto, R.id.value, R.id.icon_url} //to
        );
        // Link Adapter to ListView
        listView.setAdapter(adapter);

        // Set button listeners
        Button loadFromApi = (Button)constraintLayout.findViewById(R.id.load_from_api);
        Button loadFromDB = (Button)constraintLayout.findViewById(R.id.load_from_db);

        View.OnClickListener loadFromApiListener;
        View.OnClickListener loadFromDBListener;

        loadFromApi.setOnClickListener(loadFromApiListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                ApiRestRequest();
            }
        });

        loadFromDB.setOnClickListener(loadFromDBListener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DBRequest();
            }
        });

        // Api Rest Request
        ApiRestRequest();

        return constraintLayout;
    }

    void ApiRestRequest(){
        CoinGeckoAPI.getInstance().listCoins("usd","market_cap_desc", 100,1,"false")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
                    for(Crypto coin:x) {
                        HashMap<String,String> hm = new HashMap<>();
                        hm.put("crypto", coin.getName()+" ("+coin.getSymbol().toUpperCase()+")");
                        hm.put("value", coin.getCurrentPrice()+"$");
                        hm.put("icon_url", coin.getImage());
                        datos.add(hm);
                    }
                    adapter.notifyDataSetChanged();
                });
    }

    void DBRequest(){
        //TODO
    }
}

