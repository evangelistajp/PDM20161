package com.example.evangelista.myplace;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.storage.StorageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int ADD = 1, SOBRE = 2;
    private ListView listView;
    private Cadastro cadastro;

    public MainActivity() {

        this.cadastro = new Cadastro();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);

        //ArrayAdapter<Place> adapter = new ArrayAdapter<Place>(this, android.R.layout.simple_list_item_1, this.cadastro.get());
        PlaceAdapter adapter = new PlaceAdapter(this.cadastro, this);
        this.listView.setAdapter(adapter);

        this.listView.setOnItemClickListener(new OnClickList() );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD, 1, "Adicionar lugar");
        menu.add(0, SOBRE, 2, "Sobre");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case ADD:
                Intent itAdd = new Intent(this, AddActivity.class);
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
                String desc = data.getStringExtra("DESC");
                Bitmap photo = (Bitmap) data.getParcelableExtra("PHOTO");

                //Log.i("APP", "shasgahdja " +photo);

                Place place = new Place(nome,desc,photo);
                this.cadastro.insere(place);
                //((ArrayAdapter)this.listView.getAdapter()).notifyDataSetChanged();
                ((PlaceAdapter)this.listView.getAdapter()).notifyDataSetChanged();
                Toast.makeText(this, place.getNome(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class OnClickList implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("PLACE");
            Place place = (Place) parent.getAdapter().getItem(position);

            builder.setMessage("NOME: " +((Place) parent.getAdapter().getItem(position)).getNome()+
                    "\nDESCRIÇÃO: "+ ((Place) parent.getAdapter().getItem(position)).getDesc() + "\nFOTO:" + ((Place)parent.getAdapter().getItem(position)).getPhoto());

            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Ok", null);
            builder.create().show();

        }
    }
}
