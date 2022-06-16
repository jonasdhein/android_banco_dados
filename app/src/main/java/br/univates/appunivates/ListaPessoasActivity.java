package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.appunivates.adapter.LinguagemAdapter;
import br.univates.appunivates.adapter.PessoaAdapter;
import br.univates.appunivates.controller.LinguagemController;
import br.univates.appunivates.controller.PessoaController;
import br.univates.appunivates.model.Linguagem;
import br.univates.appunivates.model.Pessoa;
import br.univates.appunivates.tools.Globais;

public class ListaPessoasActivity extends AppCompatActivity {

    Button btnNova;
    ListView ltvLista;
    ArrayList<Pessoa> listagem;
    Context context;
    PessoaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);

        context = ListaPessoasActivity.this;

        btnNova = findViewById(R.id.btnAddPessoa_pessoas);
        ltvLista = findViewById(R.id.ltvLista_pessoa);

        btnNova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PessoasActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new PessoaController(context);
            listagem = controller.lista();

            PessoaAdapter adapter = new PessoaAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}