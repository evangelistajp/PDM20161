package com.example.evangelista.myplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by samyr on 20/06/2016.
 */
public class PlaceAdapter extends BaseAdapter {
    private Cadastro cadastro;
    private Context context;

    public PlaceAdapter() {
    }

    public PlaceAdapter(Cadastro cadastro, Context context){
        this.cadastro=cadastro;
        this.context=context;

    }

    @Override
    public int getCount() {
        return this.cadastro.quantidade();
    }

    @Override
    public Object getItem(int position) {
        return this.cadastro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Place p = this.cadastro.get(position);

        if(convertView == null){
            LayoutInflater li = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.layout_place, null);
        }
        else
        {
            view = convertView;
        }
        view.setMinimumHeight(300);
        ImageView ivFoto = (ImageView) view.findViewById(R.id.ivFotoCelula);
        TextView tvNome = (TextView) view.findViewById(R.id.tvNomeCelula);

        if(p.getPhoto()!=null)
            ivFoto.setImageBitmap(p.getPhoto());
        tvNome.setText(p.getNome());
        return view;
    }
}
