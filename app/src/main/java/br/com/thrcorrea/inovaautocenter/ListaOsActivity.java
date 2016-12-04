package br.com.thrcorrea.inovaautocenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.thrcorrea.inovaautocenter.adapter.OrdensServicosAdapter;
import br.com.thrcorrea.inovaautocenter.model.OrdemServico;

public class ListaOsActivity extends AppCompatActivity {

    private ListView listaOrdensServicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_os);

        listaOrdensServicos = (ListView) findViewById(R.id.lista_os);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_os, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_lista_os_logout:
                Preferencias preferencias = new Preferencias(ListaOsActivity.this);
                preferencias.logout();
                startActivity(new Intent(ListaOsActivity.this, LoginActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaLista() {

        OrdensServicosAdapter adapter = new OrdensServicosAdapter(this, new ArrayList<OrdemServico>());
        listaOrdensServicos.setAdapter(adapter);

        Preferencias preferencias = new Preferencias(ListaOsActivity.this);
        String idUser = preferencias.getIdUser();

        new BuscaOsTask(this, adapter).execute(idUser);
    }
}
