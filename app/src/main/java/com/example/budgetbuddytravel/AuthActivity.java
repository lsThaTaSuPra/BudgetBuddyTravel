package com.example.budgetbuddytravel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;

import java.io.*;

public class AuthActivity extends AppCompatActivity {

    private static final String TAG = "AuthActivity";

    private EditText editEmail, editPassword;
    private Button buttonLogin, buttonRegister;
    private static final String PREFS_NAME = "BudgetBuddyPrefs";
    private static final String KEY_EMAIL = "saved_email";

    private final String FILE_NAME = "utilisateur.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // 🔁 Charger l'email sauvegardé
        try {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String savedEmail = prefs.getString(KEY_EMAIL, "");
            if (!savedEmail.isEmpty()) {
                editEmail.setText(savedEmail);
                Log.d(TAG, "Email chargé depuis SharedPreferences.");
            }
        } catch (Exception e) {
            Log.e(TAG, "Erreur lors du chargement de l'email sauvegardé", e);
        }

        // Connexion
        buttonLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            try {
                if (verifierIdentifiants(email, password)) {
                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(KEY_EMAIL, email);
                    editor.apply();

                    Log.d(TAG, "Connexion réussie pour l'email : " + email);
                    Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d(TAG, "Échec de la connexion : identifiants incorrects.");
                    Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e(TAG, "Erreur pendant la tentative de connexion", e);
                Toast.makeText(this, "Erreur lors de la connexion", Toast.LENGTH_LONG).show();
            }
        });

        // Inscription
        buttonRegister.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                enregistrerUtilisateur(email, password);
                Log.d(TAG, "Compte enregistré pour : " + email);
                Toast.makeText(this, "Compte créé ! Vous pouvez maintenant vous connecter.", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Erreur lors de l'enregistrement de l'utilisateur", e);
                Toast.makeText(this, "Erreur lors de l'enregistrement", Toast.LENGTH_LONG).show();
            }
        });
    }

    // ✅ Enregistre email + mot de passe dans le fichier
    private void enregistrerUtilisateur(String email, String password) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String data = email + ";" + password;
            fos.write(data.getBytes());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.w(TAG, "Erreur lors de la fermeture du fichier après enregistrement", e);
                }
            }
        }
    }

    // ✅ Vérifie les identifiants contre le fichier
    private boolean verifierIdentifiants(String email, String password) throws IOException {
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = openFileInput(FILE_NAME);
            reader = new BufferedReader(new InputStreamReader(fis));
            String ligne = reader.readLine();

            if (ligne != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 2) {
                    return parts[0].equals(email) && parts[1].equals(password);
                } else {
                    Log.w(TAG, "Format du fichier invalide");
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Fichier utilisateur introuvable", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.w(TAG, "Erreur lors de la fermeture du lecteur", e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    Log.w(TAG, "Erreur lors de la fermeture du flux", e);
                }
            }
        }
        return false;
    }
}
