package com.example.evangelista.partiujp.DAO;

import java.util.List;

/**
 * Created by Evangelista on 11/07/2016.
 */
public interface DAO<T> {

    public void inserir(T novo);
    public void atualizar(T obj,String nome);
    public void remover(int id);
    public void remover(T obj);
    public T get(int id);
    public List<T> get();
}
