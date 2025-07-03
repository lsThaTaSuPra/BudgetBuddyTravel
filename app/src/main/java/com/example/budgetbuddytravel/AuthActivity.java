package com.example.budgetbuddytravel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;

public class AuthActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin, buttonRegister;

    private final String FILE_NAME = "utilisateur.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Connexion
        buttonLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (verifierIdentifiants(email, password)) {
                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AuthActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
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

            enregistrerUtilisateur(email, password);
            Toast.makeText(this, "Compte créé ! Vous pouvez maintenant vous connecter.", Toast.LENGTH_SHORT).show();
        });
    }

    // ✅ Enregistre email + mot de passe dans le fichier
    private void enregistrerUtilisateur(String email, String password) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String data = email + ";" + password;
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Vérifie les identifiants contre le fichier
    private boolean verifierIdentifiants(String email, String password) {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String ligne = reader.readLine();
            reader.close();
            fis.close();

            if (ligne != null) {
                String[] parts = ligne.split(";");
                if (parts.length == 2) {
                    return parts[0].equals(email) && parts[1].equals(password);
                }
            }
        } catch (IOException e) {
            // Fichier non trouvé ou erreur de lecture
            e.printStackTrace();
        }
        return false;
    }
}