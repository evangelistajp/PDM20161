package com.example.evangelista.partiujp.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.evangelista.partiujp.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEventoActivity extends AppCompatActivity {

    //private static final int FOTO = 1;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private EditText nome, desc,valor;
    private TextView txtlocaliza,txtData,txtHora;
    //private ImageView imFoto;
    private Button btnGps, btnAdd, btnData, btnHora;
    private Bitmap bitmapPhoto;
    //String localDaFoto;

    private LocationManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evento);

        this.instanciaComponentesInterface();
        this.defineListeners();

    }

    private void instanciaComponentesInterface() {
        this.nome = (EditText) findViewById(R.id.et_add_nomeEvento);
        this.desc = (EditText) findViewById(R.id.et_add_descEvento);
        this.valor = (EditText) findViewById(R.id.et_add_valorEvento);
        this.txtlocaliza = (TextView) findViewById(R.id.tv_add_gpsEvento);
        //this.imFoto = (ImageView) findViewById(R.id.iv_add_Evento);
        //this.btnfoto = (Button) findViewById(R.id.btn_add_photo);
        this.btnGps = (Button) findViewById(R.id.btn_add_gps);
        this.btnAdd = (Button) findViewById(R.id.btn_add);
        this.btnData = (Button) findViewById(R.id.btn_add_DataEvento);
        this.btnHora = (Button) findViewById(R.id.btn_add_HoraEvento);
        this.txtData = (TextView) findViewById(R.id.tv_add_dataEvento);
        this.txtHora = (TextView) findViewById(R.id.tv_add_HoraEvento);
        this.manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //this.localDaFoto = null;
    }


    private void defineListeners(){
        this.btnAdd.setOnClickListener(new OnclickAdd());
        this.btnData.setOnClickListener(new OnClickData());
        this.btnHora.setOnClickListener(new OnClickHora());
        this.btnGps.setOnClickListener(new OnClickGPS());
        //this.btnfoto.setOnClickListener(new OnClickfoto());
    }



    private class OnclickAdd implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String nome = AddEventoActivity.this.nome.getText().toString();
            String desc = AddEventoActivity.this.desc.getText().toString();
            String data = AddEventoActivity.this.txtData.getText().toString();
            if (data.equals("")){
                data = "10/10/2016";
            }
            Log.i("APP", "Valor dataS "+ data);
            String hora = AddEventoActivity.this.txtHora.getText().toString();
            if (hora.equals("")){
                hora = "10:00";
            }
            Log.i("APP", "Valor horaS "+ hora);
            String valor = AddEventoActivity.this.valor.getText().toString();
            String localiza = AddEventoActivity.this.txtlocaliza.getText().toString();
            Bitmap photo = bitmapPhoto;

            Intent it = new Intent();
            it.putExtra("NOME", nome);
            it.putExtra("DESC", desc);
            it.putExtra("DATA", data);
            it.putExtra("HORA", hora);
            it.putExtra("VALOR", valor);
            it.putExtra("LOCALIZA", localiza);
            //it.putExtra("Foto: ", photo);
            setResult(RESULT_OK, it);
            finish();

        }
    }

    private class OnClickData implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("APP", "Escolher a DATA");

            AlertDialog.Builder builder = new AlertDialog.Builder(AddEventoActivity.this);
            builder.setTitle("DATA");
            final DatePicker picker = new DatePicker(AddEventoActivity.this);
            picker.setCalendarViewShown(false);
            builder.setView(picker);
            builder.setIcon(R.mipmap.data);
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    int   day  = picker.getDayOfMonth();
                    int   month= picker.getMonth() + 1;
                    int   year = picker.getYear();
                    String sData = day+"/"+month+"/"+year;
                    Log.i("APP", "data escolhida " +sData);
                    txtData.setText(sData);
                }
            });
            builder.create().show();

        }
    }

    private class OnClickHora implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("APP", "Escolher a Hora do evento");

            AlertDialog.Builder builder = new AlertDialog.Builder(AddEventoActivity.this);
            builder.setTitle("HORA");
            final TimePicker picker = new TimePicker(AddEventoActivity.this);
            builder.setView(picker);
            builder.setIcon(R.mipmap.hora);
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    int   hora  = picker.getCurrentHour();
                    int   minuto = picker.getCurrentMinute();

                    String sHora = hora+":"+minuto;
                    Log.i("APP", "data escolhida " +sHora);
                    txtHora.setText(sHora);

                }
            });

            builder.create().show();
        }
    }

    private class OnClickGPS implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.i("APP", "GPS");

            if (ActivityCompat.checkSelfPermission(AddEventoActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddEventoActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            AddEventoActivity.this.manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GPSListener());
            Log.i("GPS", "Localização solicitada");
        }
    }

    public class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.i("GPS", "Localização recebida");
            double dlat = location.getLatitude();
            String slat = String.valueOf(dlat);
            double dlog = location.getLongitude();
            String slog = String.valueOf(dlog);
            String localizacao = "" +slat + "," + slog;
            Log.i("APP", "localização do evento "+localizacao);

            txtlocaliza.setText(localizacao);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }

        @Override
        public void onProviderEnabled(String provider) { }

        @Override
        public void onProviderDisabled(String provider) { }
    }

//    private class OnClickfoto implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//            startActivityForResult(intent, AddEventoActivity.this.FOTO);
//        }
//    }

    //@Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK){
//            if (requestCode == FOTO){
//                Bundle extras = data.getExtras ();
//                bitmapPhoto =  ( Bitmap ) extras.get("data");
//                AddEventoActivity.this.imFoto.setImageBitmap(bitmapPhoto);
//
//            }
//        }
//    }


}


