package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.appunivates.adapter.LinguagemAdapter;
import br.univates.appunivates.model.*;

public class MainActivity extends AppCompatActivity {

    EditText txtNome;
    EditText txtDescricao;
    ListView ltvLista;
    Button btnSalvar;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    Linguagem objeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
        ltvLista = findViewById(R.id.ltvLista_linguagem);
        btnSalvar = findViewById(R.id.btnSalvar_linguagem);

        listagem = new ArrayList<>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = txtNome.getText().toString().trim();
                String descricao = txtDescricao.getText().toString().trim();

                if(!nome.equals("")) {

                    objeto = new Linguagem();
                    objeto.setId(1);
                    objeto.setNome(nome);
                    objeto.setDescricao(descricao);

                    listagem.add(objeto);

                    adapter = new LinguagemAdapter(context, listagem);

                    ltvLista.setAdapter(adapter);
                    txtNome.setText("");
                    txtDescricao.setText("");
                    txtNome.requestFocus();
                }

            }
        });

    }
}