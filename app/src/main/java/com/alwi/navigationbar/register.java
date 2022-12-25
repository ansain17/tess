package com.alwi.navigationbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class register extends AppCompatActivity {

    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sessionManager = new SessionManager(getApplicationContext());


        final EditText tname = (EditText) findViewById(R.id.ednama);
        final EditText tusername = (EditText) findViewById(R.id.edusername);
        final EditText tpassword = (EditText) findViewById(R.id.edpassword);
        final EditText tlevel = (EditText) findViewById(R.id.edlevel);

        final Button bsimpan = (Button) findViewById(R.id.btnregister);
        bsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("cek tombol");
                daftarUSER(tname.getText().toString(),tusername.getText().toString(),tpassword.getText().toString(),tlevel.getText().toString());

            }
        });
    }

    private void daftarUSER(final String nama, final String username, final String password, final String level){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.SAVE_DATA_REGISTER_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println("data respon :" + response.toString());

                        showDialogInfo("sukses register");


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("data respons error :" + error.toString());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();

                params.put(AppVar.KEY_NAMAUSER, nama);
                params.put(AppVar.KEY_USERNAME, username);
                params.put(AppVar.KEY_PASSWORD, password);
                params.put(AppVar.KEY_LEVEL, level);

                return params;
            }
        };
        // cek time out koneksi ke server
        int socketTimeout = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(register.this).add(stringRequest);
    }
    public void showDialogInfo(String str) {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(register.this, R.style.AppTheme_Dark_Dialog);
        alertDialog2.setTitle("INFO");
        alertDialog2.setMessage(str);
        alertDialog2.setNegativeButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent r = new Intent(register.this, login.class);
                        startActivity(r);
                    }
                });

        try {
            alertDialog2.show();
        } catch (Exception e) {

        }
    }
}