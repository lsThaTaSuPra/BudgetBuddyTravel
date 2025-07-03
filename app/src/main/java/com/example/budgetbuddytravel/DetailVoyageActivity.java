package com.example.budgetbuddytravel.utils;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetbuddytravel.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DetailVoyageActivity extends AppCompatActivity {

    private LinearLayout layoutDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_voyage);

        layoutDetail = findViewById(R.id.layoutDetailVoyage);

        String nomFichier = getIntent().getStringExtra("fichier");
        if (nomFichier == null) {
            Toast.makeText(this, "Fichier non trouvé", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        chargerContenuFichier(nomFichier);
    }

    private void chargerContenuFichier(String fichierNom) {
        try {
            FileInputStream fis = openFileInput(fichierNom);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                TextView tv = new TextView(this);
                tv.setText(ligne);
                tv.setPadding(0, 8, 0, 8);
                layoutDetail.addView(tv);
            }

            reader.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lecture : " + fichierNom, Toast.LENGTH_SHORT).show();
        }
    }
}
