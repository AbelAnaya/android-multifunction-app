package com.upm.pasproject;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiSimpleAdapter extends SimpleAdapter {
    private List<HashMap<String, String>> datos;
    private Context context;

    static class ViewHolder{
        TextView crypto;
        TextView value;
        ImageView icon_url;
    }

    public MiSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        datos = (List<HashMap<String, String>>) data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row, null);
            vh = new ViewHolder();
            vh.crypto = convertView.findViewById(R.id.crypto);
            vh.value = convertView.findViewById(R.id.value);
            vh.icon_url = convertView.findViewById(R.id.icon_url);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        HashMap<String, String> dato = datos.get(position);
        vh.crypto.setText(dato.get("crypto"));
        vh.value.setText(dato.get("value"));
        /*Picasso.get()
                .load(dato.get("icon_url"))
                .resize(150,150)
                .into(vh.icon_url);*/

        return convertView;
    }
}
