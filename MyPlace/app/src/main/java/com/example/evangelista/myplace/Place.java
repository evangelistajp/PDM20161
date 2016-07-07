package com.example.evangelista.myplace;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Evangelista on 16/06/2016.
 */
public class Place {

    private String nome;
    private String desc;
    private String gps;
    private Bitmap photo;

    public Place(String nome, String desc,String gps, Bitmap photo) {
        this.nome = nome;
        this.desc = desc;
        this.gps = gps;
        this.photo = photo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public String toString() {
        return "Place:" +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\''+
                ", gps='" + gps + '\''+
                "photo :" + getPhoto();
    }


}
