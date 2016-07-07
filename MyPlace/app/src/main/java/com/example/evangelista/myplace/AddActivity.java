package com.example.evangelista.myplace;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    private static final int FOTO = 1;

    private EditText etNome, etDesc;
    private TextView tvGps;
    private Button btAdd, btPhoto, btGps;
    private ImageView imageView;
    private Bitmap bitmapPhoto;
    private LocationManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        instanciaComponentesInterface();
    }

    private void instanciaComponentesInterface() {
        this.etNome = (EditText) findViewById(R.id.et_add_nomePlace);
        this.etDesc = (EditText) findViewById(R.id.et_add_descPlace);
        this.tvGps = (TextView) findViewById(R.id.tv_add_gps);
        this.btPhoto = (Button) findViewById(R.id.btn_photo);
        this.btPhoto.setOnClickListener(new OnClick());

        this.manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        this.imageView = (ImageView) findViewById(R.id.imageView);

        this.btAdd = (Button) findViewById(R.id.btn_add);
        this.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = AddActivity.this.etNome.getText().toString();
                String desc = AddActivity.this.etDesc.getText().toString();
                String localizacao = AddActivity.this.tvGps.getText().toString();
                Bitmap photo = bitmapPhoto;

                Log.i("APP", "add foto photo " + photo);

                //Place place = new Place(nome,desc,photo);
                //Log.i("APP", "lugar muito doido " +place.getPhoto());
                Intent it = new Intent();
                it.putExtra("NOME", nome);
                it.putExtra("DESC", desc);
                it.putExtra("GPS", localizacao);
                it.putExtra("PHOTO", photo);
                setResult(RESULT_OK, it);
                finish();
            }
        });
        this.btGps = (Button) findViewById(R.id.btn_add_gps);
        this.btGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("GPS", "Chamar GPS");
                if (ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(AddActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                AddActivity.this.manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GPSListener());
                Log.i("GPS", "Localização solicitada");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == FOTO){
                AddActivity.this.imageView.setImageBitmap((Bitmap) data.getParcelableExtra("data"));
                this.bitmapPhoto = ((Bitmap) data.getParcelableExtra("data"));
            }
        }
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(it, AddActivity.this.FOTO);
        }
    }

    public class GPSListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            Log.i("GPS", "Localização recebida");
            double dlat = location.getLatitude();
            String slat = String.valueOf(dlat);
            double dlog = location.getLongitude();
            String slog = String.valueOf(dlog);
            String localizacao = "Lat :" +slat + " log: " + slog;
            Log.i("APP", localizacao);

            tvGps.setText(localizacao);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
