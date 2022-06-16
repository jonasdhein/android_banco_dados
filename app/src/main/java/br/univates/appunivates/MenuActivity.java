package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnPessoas;
    Button btnLinguagens;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        context = MenuActivity.this;
        btnPessoas = findViewById(R.id.btnPessoas_menu);
        btnLinguagens = findViewById(R.id.btnLinguagens_menu);

        btnPessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, ListaPessoasActivity.class);
                startActivity(tela);
            }
        });

        btnLinguagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tela = new Intent(context, ListaLinguagensActivity.class);
                startActivity(tela);
            }
        });
    }
}