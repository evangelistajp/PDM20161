package com.example.evangelista.myplace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AddActivity extends AppCompatActivity {
    private static final int FOTO = 1;

    private EditText etNome, etDesc;
    private Button btAdd, btPhoto;
    private ImageView imageView;
    private Bitmap bitmapPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        instanciaComponentesInterface();
    }

    private void instanciaComponentesInterface(){
        this.etNome = (EditText) findViewById(R.id.et_add_nomePlace);
        this.etDesc = (EditText) findViewById(R.id.et_add_descPlace);
        this.btPhoto = (Button) findViewById(R.id.btn_photo);
        this.btPhoto.setOnClickListener(new OnClick());

        this.imageView = (ImageView) findViewById(R.id.imageView);

        this.btAdd = (Button) findViewById(R.id.btn_add);
        this.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = AddActivity.this.etNome.getText().toString();
                String desc = AddActivity.this.etDesc.getText().toString();
                Bitmap photo = bitmapPhoto;

                Log.i("APP", "add foto photo " + photo);

                Place place = new Place(nome,desc,photo);
                Log.i("APP", "lugar muito doido " +place.getPhoto());
                Intent it = new Intent();
                it.putExtra("NOME", nome);
                it.putExtra("DESC", desc);
                it.putExtra("PHOTO", photo);
                setResult(RESULT_OK, it);
                finish();
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

}
