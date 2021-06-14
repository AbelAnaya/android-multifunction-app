package com.upm.pasproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.upm.pasproject.MiSimpleAdapter;
import com.upm.pasproject.R;
import com.upm.pasproject.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private String APIURL =
            "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ConstraintLayout constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.fragment_home,null);

        ListView listView = (ListView) constraintLayout.findViewById(R.id.listview);

        List<HashMap<String,String>> datos = new ArrayList<>();

        HashMap<String,String> hm = new HashMap<>();
        for (int i = 0; i<15;i++){
            hm.put("crypto","Ethereum (ETH)");
            hm.put("value",""+(8023.65+1000*i)+"$");
            hm.put("icon_url", "https://cryptoicons.org/api/black/eth/200");
            datos.add(hm);
        }

        SimpleAdapter adapter = new MiSimpleAdapter(container.getContext(),
                datos, //datos
                R.layout.row, //layout
                new String[] {"crypto","value", "icon_url"}, //from
                new int[] {R.id.crypto, R.id.value, R.id.icon_url} //to
        );

        listView.setAdapter(adapter);
        return constraintLayout;
    }
}