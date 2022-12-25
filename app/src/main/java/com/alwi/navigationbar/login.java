package com.alwi.navigationbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.alwi.navigationbar.Utility.AppVar;
import com.alwi.navigationbar.Utility.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    //panggil session
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(getApplicationContext());


        final EditText tusername = (EditText) findViewById(R.id.edusername);
        final EditText tpassword = (EditText) findViewById(R.id.edpassword);

        final Button bsimpan = (Button) findViewById(R.id.btnlogin);
        bsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("cek tombol");
                loginUSER(tusername.getText().toString(),tpassword.getText().toString());

            }
        });
    }
    private void loginUSER(final String username, final String password){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.equalsIgnoreCase("Sukses")) {
                            System.out.println("data respon :" + response.toString());

                            LoadDataUser(username, password);


                        } else {
                            showDialogInfoGagal("Login Gagal");


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("data respons error :" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(AppVar.KEY_USERNAME, username);
                params.put(AppVar.KEY_PASSWORD, password);

                return params;
            }
        };
        // cek time out koneksi ke server
        int socketTimeout = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(login.this).add(stringRequest);
    }
    private void LoadDataUser(final String username, final String password){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOAD_DATA_USER_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("Sukses")) {
                            System.out.println("data respon :" + response.toString());

                            String param = response.toString();
                            String[] parts = param.split("#");
                            String parts1   = parts[0]; //sukses
                            String parts2   = parts[1]; //id_user
                            String parts3   = parts[2]; //nama_user
                            String parts4   = parts[3]; //username
                            String parts5   = parts[4]; //password

                            //buat session
                            sessionManager.createSession(parts2, parts3);
                            showDialogInfo("sukses register");
                        } else {
                            showDialogInfoGagal("Load Data User Gagal");


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("data respons error :" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put(AppVar.KEY_USERNAME, username);
                params.put(AppVar.KEY_PASSWORD, password);

                return params;
            }
        };
        // cek time out koneksi ke server
        int socketTimeout = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(login.this).add(stringRequest);
    }
    public void showDialogInfo(String str) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(login.this, R.style.AppTheme_Dark_Dialog);
        alertDialog2.setTitle("INFO");
        alertDialog2.setMessage(str);
        alertDialog2.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent r =new Intent(login.this,MainActivity.class);
                        startActivity(r);
                    }
                });
        try {
            alertDialog2.show();
        }
        catch(Exception e) {

        }
    }
    public void showDialogInfoGagal(String str) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(login.this, R.style.AppTheme_Dark_Dialog);
        alertDialog2.setTitle("INFO");
        alertDialog2.setMessage(str);
        alertDialog2.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
        try {
            alertDialog2.show();
        }
        catch(Exception e) {

        }
    }

    public void op(View view) {
        Intent op= new Intent(login.this,register.class);
        startActivity(op);
    }
}