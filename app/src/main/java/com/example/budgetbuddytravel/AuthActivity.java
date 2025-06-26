package com.example.budgetbuddytravel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.budgetbuddytravel.model.Utilisateur;

public class AuthActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin, buttonRegister;

    // Simule une base de données avec un utilisateur
    private Utilisateur utilisateurSimule = new Utilisateur(1, "Guillaume", "test@email.com", "1234");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // ✅ Action bouton Connexion
        buttonLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            utilisateurSimule.seConnecter(email, password);

            if (utilisateurSimule.isEstConnecte()) {
                Toast.makeText(this, "Connexion réussie !", Toast.LENGTH_SHORT).show();

                // 🔁 Redirige vers MainActivity
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // ⛔ empêche de revenir à l'écran d'auth avec le bouton retour
            } else {
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        // ✅ Action bouton Créer un compte
        buttonRegister.setOnClickListener(v -> {
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            utilisateurSimule.setEmail(email);
            utilisateurSimule.setMotDePasse(password);
            utilisateurSimule.setNom("Nouvel utilisateur");

            utilisateurSimule.creerCompte();

            Toast.makeText(this, "Compte créé ! Vous pouvez maintenant vous connecter.", Toast.LENGTH_SHORT).show();
        });
    }
}
