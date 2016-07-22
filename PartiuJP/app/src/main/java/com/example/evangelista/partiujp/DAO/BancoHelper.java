package com.example.evangelista.partiujp.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Evangelista on 11/07/2016.
 */
public class BancoHelper extends SQLiteOpenHelper {
    private static final String BANCO = "eventos.db";
    private static final int VERSAO = 1;

    public BancoHelper(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table evento(" +
                "id integer primary key autoincrement not null, " +
                " nome string," +
                " descricao string,"+
                " tipo string,"+
                " data datetime,"+
                " preco double,"+
                " gps string,"+
                " photo string"+
                ");";

        db.execSQL(sql);
        Log.i("APP", "Tabela evento criada.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
