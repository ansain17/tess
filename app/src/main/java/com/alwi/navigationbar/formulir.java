package com.alwi.navigationbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class formulir extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup rdgrop;
    RadioButton pria,wanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir);

        rdgrop = (RadioGroup) findViewById(R.id.rdgrop);
        pria = (RadioButton) findViewById(R.id.pria);
        wanita = (RadioButton) findViewById(R.id.wanita);

        rdgrop.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }
}