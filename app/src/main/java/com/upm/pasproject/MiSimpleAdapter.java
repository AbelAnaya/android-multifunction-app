package com.upm.pasproject;

import android.content.Context;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MiSimpleAdapter extends SimpleAdapter {

    static class ViewHolder{
        
    }

    public MiSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        super.setViewImage(v, value);
        /*Picasso.get()
                .load(value)
                .resize(150,150)
                .into(v);*/
    }
}
