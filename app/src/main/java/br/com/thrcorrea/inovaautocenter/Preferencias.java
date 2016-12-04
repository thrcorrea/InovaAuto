package br.com.thrcorrea.inovaautocenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by thale on 04/12/2016.
 */

public class Preferencias {


    private final Context context;
    private final SharedPreferences preferencias;

    public Preferencias(Context context) {
        this.context = context;
        this.preferencias = context.getSharedPreferences("inovaAuto", Context.MODE_PRIVATE);
    }

    public void logout() {
        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove("idUser");
        editor.commit();
    }

    public boolean isLoggedIn(){
        String idUser = preferencias.getString("idUser", "");

        return ( !idUser.isEmpty() && idUser != null);
    }

    public String getIdUser() {
        return preferencias.getString("idUser", "");
    }

    public void setIdUser(String idUser) {
        SharedPreferences.Editor editor = preferencias.edit();
        if (!idUser.isEmpty() && idUser != null) {
            editor.putString("idUser", idUser);
            editor.commit();
        }
    }
}
