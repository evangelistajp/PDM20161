package com.example.evangelista.partiujp.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.evangelista.partiujp.DAO.EventoDao;
import com.example.evangelista.partiujp.Model.Evento;
import com.example.evangelista.partiujp.R;
import com.example.evangelista.partiujp.Util.EventoAdapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int ADD = 1, SOBRE = 2;
    private ListView listView;
    private EventoDao eventoDAO;
    Evento evento;
    private String geo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instanciaComponentesInterface();

        this.eventoDAO = new EventoDao(this);

        EventoAdapter adapter = new EventoAdapter(this.eventoDAO.get() ,this);
        this.listView.setAdapter(adapter);
        this.alarmEventos();
        this.atualizaAdapter();
    }

    private void atualizaAdapter(){
        this.listView.setAdapter(new EventoAdapter(this.eventoDAO.get(), this));
    }

    private void instanciaComponentesInterface(){
        this.listView = (ListView) findViewById(R.id.lv_Evento);
        this.listView.setOnItemClickListener( new OnClickList());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD, 1, "Adicionar evento");
        menu.add(0, SOBRE, 2, "Sobre");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case ADD:
                Intent itAdd = new Intent(this, AddEventoActivity.class);
                startActivityForResult(itAdd, ADD);

                break;
            case SOBRE:
                Intent itSobre = new Intent(this, SobreActivity.class);
                startActivity(itSobre);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == ADD){
                String nome = data.getStringExtra("NOME");
                Log.i("APP", "String NOME" + nome);
                String desc = data.getStringExtra("DESC");
                String sDate = data.getStringExtra("DATA");
                String sHora = data.getStringExtra("HORA");
                DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
                Date date = null;
                try {
                    date = (Date)formatter.parse(sDate+" "+ sHora);
                    Log.i("APP", "Hora add e formatada " + date +" "+ sHora);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String sValor = data.getStringExtra("VALOR");
                Log.i("APP", "Valor String " + sValor);
                double dValor = Double.parseDouble(sValor);
                String gps = data.getStringExtra("LOCALIZA");
                //String photo = data.getStringExtra("PHOTO");

                Evento evento = new Evento(nome,desc,date,dValor,gps);
                Log.i("APP", "Evento add :"+ evento.toString());

                this.eventoDAO.inserir(evento);

                //((EventoAdapter)this.listView.getAdapter()).notifyDataSetChanged();
                this.atualizaAdapter();

                Toast.makeText(this, evento.getNome(), Toast.LENGTH_SHORT).show();
                Log.i("APP", "Todos os eventos cadastrados "+ this.eventoDAO.get().toString());
            }
        }
    }

    private class OnClickList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            MainActivity.this.evento = (Evento) parent.getAdapter().getItem(position);
            builder.setTitle(evento.getNome());
            builder.setMessage("Descrição: " + evento.getDescricao()+"\n"+
                    "Valor: " + evento.getValor()+"\n"+
                    "Data: " + evento.getData()+"\n"+
                    "Localização: " + evento.getGps()+"\n" );
            MainActivity.this.geo = evento.getGps();

            builder.setIcon(R.mipmap.gps);
            builder.setPositiveButton("Localização", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String geo = "geo:"+ MainActivity.this.geo;
                    Uri gmmIntentUri = Uri.parse(geo);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                }
            });
            builder.setNegativeButton("Deletar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Evento deletado!", Toast.LENGTH_SHORT).show();
                    MainActivity.this.eventoDAO.remover(MainActivity.this.evento);
                    ((BaseAdapter)MainActivity.this.listView.getAdapter()).notifyDataSetChanged();
                    MainActivity.this.atualizaAdapter();


                }
            });
            builder.create().show();



        }
    }

    // NOTIFICAÇÕES
    public void alarmEventos() {
        Log.i("APP", "AlarmEventos");

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent(
                "ALARME_EVENTO"), PendingIntent.FLAG_NO_CREATE) == null);

        if (alarmeAtivo) {
            Log.i("APP", "Novo alarme");

            Intent intent = new Intent("ALARME_EVENTO");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 5);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 2 * 1000, p);
            //alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
        }else {
            Log.i("APP", "Alarme já ativo");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        Intent intent = new Intent("ALARME_EVENTO");
//        PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//        AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarme.cancel(p);
    }


}
