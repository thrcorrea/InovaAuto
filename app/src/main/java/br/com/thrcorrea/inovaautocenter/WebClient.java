package br.com.thrcorrea.inovaautocenter;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by thale on 30/11/2016.
 */

public class WebClient {

    private static final String urlApi = "http://inovautoapi.getsandbox.com";

    public String buscaOrdensServicos(String idUser){
        HttpURLConnection connection = null;
        try {
            URL url = new URL( urlApi + "/os/" + idUser);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());
            String resposta = "";
            while (scanner.hasNext()){
                 resposta = resposta + scanner.next();
            }
            return resposta;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return null;
    }

    public String autenticaUsuario(String json){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlApi + "/users/auth");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-type", "text");
            connection.connect();

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);


            int status = connection.getResponseCode();

            InputStream inputStream;
            if (status != HttpURLConnection.HTTP_OK) {
                inputStream = connection.getErrorStream();
            }
            else{
                inputStream = connection.getInputStream();
            }

            Scanner scanner = new Scanner(inputStream);
            String resposta = "";
            while (scanner.hasNext()){
                resposta = resposta + scanner.next();
            }

            return resposta;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return null;
    }
}
