package com.upm.pasproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.upm.pasproject.rest.CoinGeckoAPI;
import com.upm.pasproject.rest.CoinGeckoEndPoints;
import com.upm.pasproject.rest.models.Crypto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CryptoFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_crypto,null);

        ListView listView = (ListView) constraintLayout.findViewById(R.id.listview);

        List<HashMap<String,String>> datos = new ArrayList<>();
        HashMap<String,String> hm = new HashMap<>();


        SimpleAdapter adapter = new MiSimpleAdapter(container.getContext(),
                datos, //datos
                R.layout.row, //layout
                new String[] {"crypto","value", "icon_url"}, //from
                new int[] {R.id.crypto, R.id.value, R.id.icon_url} //to
        );

        listView.setAdapter(adapter);

        CoinGeckoAPI.getInstance().listCoins("usd","market_cap_desc", 100,1,"false")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
                    for(Crypto coin:x) {
                        hm.put("crypto", coin.getName()+" ("+coin.getSymbol().toUpperCase()+")");
                        hm.put("value", coin.getCurrentPrice()+"$");
                        hm.put("icon_url", coin.getImage());
                        datos.add(hm);
                    }
                    adapter.notifyDataSetChanged();
                });

        /*CoinGeckoAPI.getInstance().listCoins("usd","market_cap_desc",
                100,1,"false")
                .enqueue(new Callback<List<Crypto>>() {
            @Override
            public void onResponse(Call<List<Crypto>> call, Response<List<Crypto>> response) {
                List<Crypto> resultado = response.body();

                List<HashMap<String,String>> datos = new ArrayList<>();
                HashMap<String,String> hm = new HashMap<>();

                for(Crypto coin:resultado) {
                    hm.put("crypto", coin.getName()+" ("+coin.getSymbol().toUpperCase()+")");
                    hm.put("value", coin.getCurrentPrice()+"$");
                    hm.put("icon_url", coin.getImage());
                    datos.add(hm);
                }

                updateUI();

            }

            @Override
            public void onFailure(Call<List<Crypto>> call, Throwable t) {

                Log.d("DBG",t.toString());

            }
        });*/

        return constraintLayout;
    }
}

