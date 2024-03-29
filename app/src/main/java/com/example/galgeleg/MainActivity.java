package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        ((Switch)findViewById(R.id.switch_dr)).setChecked(preferences.getBoolean("dr", false));

        findViewById(R.id.buttonSpil).setOnClickListener(this);
        findViewById(R.id.switch_dr).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.buttonSpil:
                Intent i = new Intent(this, GameActivity.class);
                startActivity(i);
                break;

            case R.id.switch_dr:
                SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
                editor.putBoolean("dr", ((Switch)findViewById(R.id.switch_dr)).isChecked());
                editor.apply();
                break;
        }

    }
}
