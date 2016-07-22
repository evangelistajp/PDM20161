package com.example.evangelista.partiujp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.evangelista.partiujp.Activity.MainActivity;
import com.example.evangelista.partiujp.DAO.EventoDao;
import com.example.evangelista.partiujp.Model.Evento;
import com.example.evangelista.partiujp.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Evangelista on 15/07/2016.
 */
public class BroadcastReceiverEvento extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("Alarme","Alarme " );

        EventoDao eventoDao = new EventoDao(context);
        List<Evento> eventos = eventoDao.get();

        for (int i = 0; i < eventos.size(); i++) {
            Log.i("Alarme","hora evento " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(eventos.get(i).getData()));
            Log.i("Alarme","hora relogio " +new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
            String dataEvento = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(eventos.get(i).getData());
            if (dataEvento.equals((new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())).toString())) {
                this.gerarNotificacao(context, new Intent(context, MainActivity.class), "Lembrete",
                        eventos.get(i).getNome(), eventos.get(i).getDescricao());
                Log.i("Alarme"," Notificação ok ");
            }
        }
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao){
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        //builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setSmallIcon(R.mipmap.alarme2);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.alarme2));
        builder.setContentIntent(p);

        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.mipmap.alarme, n);

    }
}
