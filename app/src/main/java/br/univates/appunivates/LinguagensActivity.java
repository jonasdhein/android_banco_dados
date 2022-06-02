package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import br.univates.appunivates.controller.LinguagemController;
import br.univates.appunivates.model.Linguagem;
import br.univates.appunivates.model.Nota;
import br.univates.appunivates.tools.Globais;

public class LinguagensActivity extends AppCompatActivity {

    Button btnExcluir;
    CheckBox chbFavorito;
    EditText txtNome;
    EditText txtDescricao;
    Spinner spiNota;
    Linguagem objeto;
    LinguagemController controller;
    Context context;
    int id_linguagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linguagens);

        txtNome = findViewById(R.id.txtNome_linguagem);
        txtDescricao = findViewById(R.id.txtDescricao_linguagem);
        chbFavorito = findViewById(R.id.chbFavorito_linguagem);
        spiNota = findViewById(R.id.spiNota_linguagem);
        btnExcluir = findViewById(R.id.btnExcluir_linguagem);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller = new LinguagemController(context);
                boolean retorno = controller.excluir(id_linguagem);
                if(retorno){
                    finish();
                }else{
                    Globais.exibirMensagem(context, "Erro ao excluir");
                }
            }
        });

        context = LinguagensActivity.this;

        ArrayList<Nota> lista_notas = new ArrayList<>();
        lista_notas.add(new Nota(0, "Selecione..."));
        lista_notas.add(new Nota(1, "Nota 1"));
        lista_notas.add(new Nota(2, "Nota 2"));
        lista_notas.add(new Nota(3, "Nota 3"));
        lista_notas.add(new Nota(4, "Nota 4"));
        lista_notas.add(new Nota(5, "Nota 5"));

        ArrayAdapter<Nota> adapter_notas = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, lista_notas);

        spiNota.setAdapter(adapter_notas);

        //Verificar se veio algum EXTRA da tela anterior
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_linguagem = extras.getInt("id", 0);
            //buscar através desta chave
            controller = new LinguagemController(context);
            objeto = controller.buscar(id_linguagem);
            if(objeto != null){
                txtNome.setText(objeto.getNome());
                txtDescricao.setText(objeto.getDescricao());

                if(objeto.getFavorito() == 1){
                    chbFavorito.setChecked(true);
                }else{
                    chbFavorito.setChecked(false);
                }

                for(int i = 0; i < spiNota.getAdapter().getCount(); i++){
                    Nota nota = (Nota) spiNota.getItemAtPosition(i);
                    if(nota.getId() == objeto.getNota()){
                        spiNota.setSelection(i);
                        break;
                    }
                }
            }

        }else{
            id_linguagem = 0;
            btnExcluir.setVisibility(View.GONE);
        }

    }

    //Funcao para inflar o menu na tela
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){

            case R.id.action_ok:

                //SALVAR
                salvar();

            case R.id.action_cancel:

                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void salvar(){
        try{

            String nome = txtNome.getText().toString().trim();
            String descricao = txtDescricao.getText().toString().trim();
            Nota nota = (Nota) spiNota.getSelectedItem();

            if(nome.equals("")) {
                Globais.exibirMensagem(context, "Informe um nome");
                return;
            }

            if(descricao.equals("")) {
                Globais.exibirMensagem(context, "Informe uma descrição");
                return;
            }

            if(nota.getId() <= 0) {
                Globais.exibirMensagem(context, "Informe uma nota");
                return;
            }

            if(nome.length() > 30){
                Globais.exibirMensagem(context,
                        "O nome é muito grande, credo.");
                return;
            }

            objeto = new Linguagem();
            objeto.setNome(nome);
            objeto.setDescricao(descricao);
            objeto.setNota(nota.getId());

            if(chbFavorito.isChecked()){
                objeto.setFavorito(1);
            }else{
                objeto.setFavorito(0);
            }

            controller = new LinguagemController(context);

            boolean retorno = false;
            if(id_linguagem == 0){
                retorno = controller.incluir(objeto);
            }else{
                objeto.setId(id_linguagem);
                retorno = controller.alterar(objeto);
            }

            if(retorno) {
                Globais.exibirMensagem(context, "Sucesso");
                finish();
            }



        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            Log.e("ERRO", ex.getMessage());
        }
    }
}