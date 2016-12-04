package br.com.thrcorrea.inovaautocenter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.ViewDebug;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.thrcorrea.inovaautocenter.adapter.OrdensServicosAdapter;
import br.com.thrcorrea.inovaautocenter.model.OrdemServico;

/**
 * Created by thale on 30/11/2016.
 */

public class BuscaOsTask extends AsyncTask<String, Object, List<OrdemServico>> {

    private final Context context;
    private final OrdensServicosAdapter adapter;

    public BuscaOsTask(Context context, OrdensServicosAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
    }

    @Override
    protected List<OrdemServico> doInBackground(String... params) {

        List<OrdemServico> ordensServicos = new ArrayList<OrdemServico>();
        WebClient client = new WebClient();
        JSONObject jsonObject;

        String idUser = params[0];

        try {
            jsonObject = new JSONObject(client.buscaOrdensServicos(idUser));
            JSONArray arr = jsonObject.getJSONArray("os");
            for (int i=0; i < arr.length(); i++) {
                ordensServicos.add(converteOrdemServico(arr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ordensServicos;
    }

    @Override
    protected void onPostExecute(List<OrdemServico> result) {
        adapter.setOrdensServicos(result);
        adapter.notifyDataSetChanged();
//        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
    }

    private OrdemServico converteOrdemServico(JSONObject jobj) throws JSONException {
        String numeroOS = "123";
        String status = jobj.getString("status");
        String carro =  jobj.getJSONObject("veiculo").getString("modelo");
        String data = jobj.getString("data_entrada");

        return new OrdemServico(numeroOS, status, carro, data);
    }
}
