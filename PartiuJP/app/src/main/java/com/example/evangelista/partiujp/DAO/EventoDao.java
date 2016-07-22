package com.example.evangelista.partiujp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.evangelista.partiujp.Model.Evento;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Evangelista on 11/07/2016.
 */
public class EventoDao implements DAO<Evento> {
    private BancoHelper banco;
    private static final String TABELA = "evento";
    private Date data;

    public EventoDao(Context context) {
        this.banco =  new BancoHelper(context);
    }

    @Override
    public void inserir(Evento novo) {
        ContentValues cv = new ContentValues();
        cv.put("nome", novo.getNome());
        cv.put("descricao", novo.getDescricao());
        cv.put("data", String.valueOf(novo.getData()));
        cv.put("preco", novo.getValor());
        cv.put("gps", novo.getGps());
        //cv.put("photo", novo.getPhoto());
        Log.i("APP" , "Inserir Evento "+cv.toString());

        Log.i("APP", "Evento DAO: " +novo.toString());
        this.banco.getWritableDatabase().insert(TABELA,null,cv);
    }

    @Override
    public void atualizar(Evento obj, String nome) {
        ContentValues cv = new ContentValues();

        cv.put("nome", obj.getNome());
        cv.put("descricao", obj.getDescricao());
        cv.put("data", String.valueOf(obj.getData()));
        cv.put("preco", obj.getValor());
        cv.put("gps", obj.getGps());
        //cv.put("photo", obj.getPhoto());
        Log.i("APP" , "Atualizar evento "+cv.toString());
        //banco.onUpgrade("evento", cv, "nome=?", new String[]{nome});
    }

    @Override
    public void remover(int id) {
        String[] where = {Integer.toString(id)};
        this.banco.getWritableDatabase().delete(TABELA, "id = ?", where);
    }

    @Override
    public void remover(Evento obj) {
        this.remover(obj.getId());
    }

    @Override
    public Evento get(int id) {
        return null;
    }

    @Override
    public List<Evento> get() {

        String[] colunas = {"id", "nome","descricao","data","preco","gps","photo"};
        List<Evento> lista = new ArrayList<Evento>();

        Cursor c = this.banco.getReadableDatabase().query(TABELA, colunas, null, null, null, null, null);
        if (c.getCount() > 0){
            c.moveToFirst();
            do{
                Evento e = new Evento();
                e.setId(c.getInt(c.getColumnIndex(colunas[0])));
                e.setNome(c.getString(1));
                e.setDescricao(c.getString(2));
                String sData = c.getString((c.getColumnIndex("data")));
                Log.i("APP", "GET() DATA STRING " + sData);
                DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");

                try {
                    data = ((Date) formatter.parse(sData));
                } catch (ParseException e1) {
                    Log.e("APP","ERRO NA CONVERSAO DE DATA");
                    e1.printStackTrace();
                }
                Log.i("APP", "GET() DATA DATE " + data);
                e.setData(data);
                e.setValor(Integer.parseInt(c.getString(4)));
                e.setGps(c.getString(5));
                //Bitmap bitmap = c.getBlob(6);
                //e.setPhoto(c.getString(6));
                lista.add(e);
                Log.i("APP", "GET() "+ e);
            }while (c.moveToNext());
        }
        Log.i("APP", "retorna a lista de evento " + lista.size());
        return lista;
    }
}
