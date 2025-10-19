// LoginActivity.java
package com.example.aplicaounioz; // Mude para o seu pacote

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login_activity extends AppCompatActivity {

    private Button btnEntrar;
    private TextView tvRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 1. Encontrar os componentes pelo ID
        btnEntrar = findViewById(R.id.buttonLogin);
        tvRecuperarSenha = findViewById(R.id.button);

        // ----------------------------------------------------
        // A) Conexão Login -> Menu
        // ----------------------------------------------------
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // *** LOGICA DE VALIDAÇÃO AQUI ***
                boolean loginValido = true; // Substitua por sua lógica de validação de DB!

                if (loginValido) {
                    // 1. Criar a INTENT para ir para a MenuActivity
                    Intent intent = new Intent(login_activity.this, menu_activity.class);

                    // 2. Iniciar a Activity
                    startActivity(intent);

                    // 3. Opcional: Finalizar a tela de login para evitar que o usuário volte
                    finish();
                } else {
                    Toast.makeText(login_activity.this, "Usuário ou senha inválidos.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ----------------------------------------------------
        // B) Conexão Login -> Recuperação de Senha
        // ----------------------------------------------------
        tvRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Criar a INTENT para ir para a RecuperacaoSenhaActivity
                Intent intent = new Intent(login_activity.this, recuperacaoActivity.class);

                // 2. Iniciar a Activity
                startActivity(intent);

                // NÃO usamos finish() aqui, pois o usuário deve poder cancelar
                // a recuperação e voltar para a tela de Login.
            }
        });
    }
}