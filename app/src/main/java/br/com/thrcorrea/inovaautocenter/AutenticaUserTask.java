package br.com.thrcorrea.inovaautocenter;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONStringer;

/**
 * Created by thale on 03/12/2016.
 */

public class AutenticaUserTask extends AsyncTask<String, Void, String> {

    private final Context context;

    public AutenticaUserTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        WebClient client = new WebClient();

        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("cpf").value(params[0]);
            js.key("password").value("40BD001563085FC35165329EA1FF5C5ECBDBBEEF");
            js.endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String resposta = client.autenticaUsuario(js.toString());

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();

//        Intent intent = new Intent(context, ListaOsActivity.class);
//        context.startActivity(intent);
    }
}
