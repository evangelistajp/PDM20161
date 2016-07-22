package com.example.evangelista.partiujp.Model;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Evangelista on 11/07/2016.
 */
public class Evento {

    private int id;
    private String nome;
    private String descricao;
    private Date data;
    private double valor;
    private String gps;
   // private String photo;


    public Evento(String nome, String descricao, Date data, double valor,String gps) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.gps = gps;
        //this.photo = foto;
    }

    public Evento(){}

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

//    public String getPhoto() {
//        return photo;
//    }
//
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                ", gps='" + gps + '\'' +
                //", foto " + photo + '\'' +
                '}';
    }
}
