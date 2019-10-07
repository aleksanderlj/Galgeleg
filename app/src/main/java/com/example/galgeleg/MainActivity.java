package com.example.galgeleg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Galgelogik spil;
    int billedeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.textView);

        spil = new Galgelogik();

        //Man kan ikke få adgang til internettet på sin main thread :(
        /*
        try {
            spil.hentOrdFraDr();
        } catch (Exception e) {
            e.printStackTrace();
        }
         */


        spil.logStatus();
        billedeIndex = 1;
        tv.setText(spil.getSynligtOrd());

        int[] alfaKnap = getAlfabetKnapper();
        for (int n : alfaKnap) {
            findViewById(n).setOnClickListener(this);
        }

        findViewById(R.id.retryBtn).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        TextView ord = findViewById(R.id.textView);
        TextView status = findViewById(R.id.statusText);
        ImageView iv = findViewById(R.id.galge);
        int darkgoldenrod = Color.parseColor("#daa520");

        int[] galgeBillede = {
                R.drawable.galge,
                R.drawable.forkert1,
                R.drawable.forkert2,
                R.drawable.forkert3,
                R.drawable.forkert4,
                R.drawable.forkert5,
                R.drawable.forkert6
        };


        if(erAlfabetknap(v.getId())) {
            Button btn = (Button) v;
            spil.gætBogstav(btn.getText().toString().toLowerCase());
            status.setVisibility(View.VISIBLE);

            if (spil.erSidsteBogstavKorrekt()) {
                status.setText("Korrekt!");
                status.setTextColor(Color.GREEN);
            } else {
                status.setText("Forkert!");
                status.setTextColor(Color.RED);
                iv.setImageResource(galgeBillede[billedeIndex++]);
            }
            btn.setEnabled(false);
            ord.setText(spil.getSynligtOrd());

            if(spil.erSpilletSlut()){
                for (int i : getAlfabetKnapper()){
                    findViewById(i).setEnabled(false);
                }

                if(spil.erSpilletVundet()){
                    status.setText("Du vandt!");
                    status.setTextColor(darkgoldenrod);
                    findViewById(R.id.retryBtn).setVisibility(View.VISIBLE);
                } else{
                    ord.setText(spil.getOrdet());
                    status.setText("Du tabte!");
                    status.setTextColor(Color.RED);
                    findViewById(R.id.retryBtn).setVisibility(View.VISIBLE);
                }

            }


        } else {
            switch (v.getId()){
                case R.id.retryBtn:
                    for (int i : getAlfabetKnapper()){
                        findViewById(i).setEnabled(true);
                    }
                    spil.nulstil();
                    ord.setText(spil.getSynligtOrd());
                    billedeIndex = 0;
                    iv.setImageResource(galgeBillede[billedeIndex++]);
                    findViewById(R.id.retryBtn).setVisibility(View.INVISIBLE);
                    status.setVisibility(View.INVISIBLE);
                    break;
            }

        }

    }

    int[] getAlfabetKnapper(){
        int[] n = {
                R.id.buttonA,
                R.id.buttonB,
                R.id.buttonC,
                R.id.buttonD,
                R.id.buttonE,
                R.id.buttonF,
                R.id.buttonG,
                R.id.buttonH,
                R.id.buttonI,
                R.id.buttonJ,
                R.id.buttonK,
                R.id.buttonL,
                R.id.buttonM,
                R.id.buttonN,
                R.id.buttonO,
                R.id.buttonP,
                R.id.buttonQ,
                R.id.buttonR,
                R.id.buttonS,
                R.id.buttonT,
                R.id.buttonU,
                R.id.buttonV,
                R.id.buttonW,
                R.id.buttonX,
                R.id.buttonY,
                R.id.buttonZ,
                R.id.buttonÆ,
                R.id.buttonØ,
                R.id.buttonÅ,
        };

        return n;
    }

    boolean erAlfabetknap (int id){
        boolean n = false;

        for (int i : getAlfabetKnapper()){
            if (id == i){
                return true;
            }
        }

        return false;
    }
}
