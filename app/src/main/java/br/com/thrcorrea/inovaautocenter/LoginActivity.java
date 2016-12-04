package br.com.thrcorrea.inovaautocenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LoginActivity extends AppCompatActivity {

    private TextView lblErro;
    private Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.login_btn_entrar);
        final EditText edtCpf = (EditText) findViewById(R.id.login_edt_cpf);
        final EditText edtSenha = (EditText) findViewById(R.id.login_edt_password);
        lblErro = (TextView) findViewById(R.id.login_lbl_erro);

        edtCpf.addTextChangedListener(MaskUtil.insert(edtCpf, MaskUtil.MaskType.CPF));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cpf = edtCpf.getText().toString();
                String senha = edtSenha.getText().toString();

                new ValidaUsuarioTask().execute(cpf, senha);
            }
        });

        preferencias = new Preferencias(this);

        if (preferencias.isLoggedIn()) {
            vaiListaOs();
        }
    }

    public void vaiListaOs(){
        Intent intent = new Intent(LoginActivity.this, ListaOsActivity.class);
        startActivity(intent);
        finish();
    }

    private class ValidaUsuarioTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            WebClient client = new WebClient();

            JSONStringer js = new JSONStringer();

            String cpf = MaskUtil.unmask(params[0]);
            String senha = params[1];

            try {
                js.object();
                js.key("cpf").value(cpf);
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
            JSONObject jsonResposta = null;
            try {
                jsonResposta = new JSONObject(resposta);
                if (jsonResposta.getBoolean("success")) {
                    String idUser = jsonResposta.getJSONObject("data").getString("idUser");
                    preferencias.setIdUser(idUser);
                    vaiListaOs();
                } else {
                    lblErro.setText(R.string.mensagem_erro_login);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
