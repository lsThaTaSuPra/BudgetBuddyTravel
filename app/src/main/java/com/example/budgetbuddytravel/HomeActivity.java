package com.example.budgetbuddytravel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout layoutVoyages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutVoyages = findViewById(R.id.layoutVoyages);
        Button nouveauVoyageBtn = findViewById(R.id.buttonNouveauVoyage);

        // Bouton vers la création de voyage
        nouveauVoyageBtn.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, TripActivity.class);
            startActivity(intent);
        });

        afficherTousLesVoyages();
    }

    private void afficherTousLesVoyages() {
        layoutVoyages.removeAllViews(); // Nettoyer avant de recharger

        File dir = getFilesDir();
        File[] fichiers = dir.listFiles((dir1, name) -> name.startsWith("voyage_") && name.endsWith(".txt"));

        if (fichiers == null || fichiers.length == 0) {
            TextView tv = new TextView(this);
            tv.setText("Aucun voyage trouvé.");
            layoutVoyages.addView(tv);
            return;
        }

        for (File fichier : fichiers) {
            try {
                FileInputStream fis = openFileInput(fichier.getName());
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                StringBuilder contenu = new StringBuilder();
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    contenu.append(ligne).append("\n");
                }

                reader.close();
                fis.close();

                TextView tv = new TextView(this);
                tv.setText(contenu.toString());
                tv.setPadding(0, 16, 0, 16);
                tv.setOnClickListener(v -> {
                    // TODO : ouvrir DetailVoyageActivity avec ce fichier
                    Toast.makeText(this, "Futur écran : détails du voyage", Toast.LENGTH_SHORT).show();
                });

                layoutVoyages.addView(tv);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erreur lecture fichier : " + fichier.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        afficherTousLesVoyages(); // Rafraîchit après un retour de TripActivity
    }
}
