package com.example.evangelista.myplace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class PlaceActivity extends AppCompatActivity {
    private static final int FOTO =1;
    private EditText etNome;
    private ImageView ivFoto;
    private Button btSalvar, btCancelar, btFoto;
    private Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        this.instanciaComponentesInterface();
        this.listeners();
    }
    private void instanciaComponentesInterface() {
        this.etNome = (EditText) findViewById(R.id.etNomePlace);
        this.ivFoto = (ImageView) findViewById(R.id.ivFotoPlace);
        this.btFoto = (Button) findViewById(R.id.btFotoPlace);
        this.btSalvar = (Button) findViewById(R.id.btSalvarPlace);
        this.btCancelar =(Button)findViewById(R.id.btCancelarPlace);
    }
    private void listeners(){
        OnClick oc = new OnClick();
        this.btFoto.setOnClickListener(oc);
        this.btSalvar.setOnClickListener(oc);
        this.btCancelar.setOnClickListener(oc);
    }
    protected void onActivityResult (int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == FOTO){
                this.foto = data.getParcelableExtra("data");
                this.ivFoto.setImageBitmap(this.foto);
            }
        }
    }
    private class OnClick implements View.OnClickListener{
        public void onClick(View v) {
            if (v.equals(PlaceActivity.this.btFoto)){
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, FOTO);
            }else if (v.equals(PlaceActivity.this.btSalvar)){
                Intent it = new Intent();
                it.putExtra("NOME", PlaceActivity.this.etNome.getText().toString());
                it.putExtra("FOTO", PlaceActivity.this.foto);
                setResult(RESULT_OK, it);
                finish();
            }else{
                finish();
            }
        }
    }


}
