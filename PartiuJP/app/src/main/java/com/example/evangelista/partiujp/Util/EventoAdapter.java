package com.example.evangelista.partiujp.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evangelista.partiujp.Model.Evento;
import com.example.evangelista.partiujp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Evangelista on 11/07/2016.
 */
public class EventoAdapter extends BaseAdapter {
    private List<Evento> lista;
    private Context context;

    public EventoAdapter(List<Evento> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.lista.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Evento e = this.lista.get(position);

        if (convertView == null){
            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.layout_evento_adapter, null);
        }else view = convertView;

        view.setMinimumHeight(150);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFotoEventoCelula);
        TextView tvnome = (TextView) view.findViewById(R.id.tvNomeEventoCelula);
        TextView tvdata = (TextView) view.findViewById(R.id.tvDataEventoCelula);
        TextView tvvalor = (TextView) view.findViewById(R.id.tvValorEventoCelula);

//        if(e.getPhoto()!=null){
//            byte [] encodeByte= Base64.decode(e.getPhoto(),Base64.DEFAULT);
//            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
//            ivFoto.setImageBitmap(bitmap);
//        }

//        if(e.getPhoto()!=null)
//            ivFoto.setImageBitmap(e.getPhoto());
        tvnome.setText("Evento: " +e.getNome());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String sData = formatter.format(e.getData());
        tvdata.setText(" Data: " + sData);

//        String svalor = Integer.toString((int) e.getValor());
//        tvvalor.setText("R$: "+svalor);
        return view;
    }


}
