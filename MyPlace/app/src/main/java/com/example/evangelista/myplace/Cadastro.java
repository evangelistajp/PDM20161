package com.example.evangelista.myplace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evangelista e Samyra on 16/06/2016.
 */
public class Cadastro {

   private List<Place> listPlace;

    public Cadastro(){
        this.listPlace = new ArrayList<Place>();
    }

    public void insere(Place place){

        listPlace.add(place);
    }

    public List<Place> get(){

        return this.listPlace;
    }

    public int quantidade(){

        return this.listPlace.size();
    }

    public void clear(){

        this.listPlace.clear();
    }

    public void delete(int pos){

        this.listPlace.remove(pos);
    }
    public Place get (int index){
        return this.listPlace.get(index);
    }
}
