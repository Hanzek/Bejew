package com.example.bejew;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Ranking extends AppCompatActivity {
    int theBest;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        SharedPreferences sharedPref = getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        theBest = sharedPref.getInt("best",0);
        t = findViewById(R.id.score);
        t.setText(String.valueOf(theBest));

    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage("Do you want to return to the main menu?");
        builder.setTitle("Return");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent intent = new Intent(Ranking.this, Inicio.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void back(View view) {
        onBackPressed();
    }

    public void share(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBody =String.valueOf(theBest);
        String sharesub = "your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT,shareBody);
        intent.putExtra(intent.EXTRA_SUBJECT,sharesub);
        startActivity(Intent.createChooser(intent,"Share"));
    }

    public void rt(View view) {
        Intent i = new Intent (Ranking.this, RankingWithTime.class);
        startActivity(i);
        finish();
    }
}
