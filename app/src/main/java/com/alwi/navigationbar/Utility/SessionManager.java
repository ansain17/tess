package com.alwi.navigationbar.Utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alwi.navigationbar.MainActivity;
import com.alwi.navigationbar.HomeFragment;
import com.alwi.navigationbar.login;

import java.util.HashMap;


public class SessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "crudpref";
    private static final String is_login = "islogin";
    public  static final String kunci_iduser = "iduser";
    public  static final String kunci_nama = "nama";


    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name, mode);
        editor = pref.edit();
    }

    public void createSession(String email, String level){

        editor.putBoolean(is_login, true);
        editor.putString(kunci_iduser, email);
        editor.putString(kunci_nama, level);
        editor.commit();

    }

    public void checkLogin(){

        if (!this.is_login()){ // jika data tidak ditemukan , maka tampil hal login
            Intent i = new Intent(context,  login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else { // jika sudah login, langsung masuk ke menu utama

            Intent i = new Intent(context, MainActivity.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    private boolean is_login() {
        return pref.getBoolean(is_login, false);
    }


    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(pref_name, pref.getString(pref_name, null));
        user.put(kunci_iduser, pref.getString(kunci_iduser, null));
        user.put(kunci_nama, pref.getString(kunci_nama, null));
        return user;
    }

}
