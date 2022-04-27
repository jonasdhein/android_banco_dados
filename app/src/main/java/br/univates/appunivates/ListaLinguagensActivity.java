package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import br.univates.appunivates.adapter.LinguagemAdapter;
import br.univates.appunivates.controller.LinguagemController;
import br.univates.appunivates.model.*;
import br.univates.appunivates.tools.Globais;

public class ListaLinguagensActivity extends AppCompatActivity {

    ListView ltvLista;
    ArrayList<Linguagem> listagem;
    LinguagemAdapter adapter;
    Context context;
    LinguagemController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_linguagens);
        context = ListaLinguagensActivity.this;

        ltvLista = findViewById(R.id.ltvLista_linguagem);

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    private void atualizarLista(){
        try {

            controller = new LinguagemController(context);
            listagem = controller.lista();

            adapter = new LinguagemAdapter(context, listagem);
            ltvLista.setAdapter(adapter);

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}