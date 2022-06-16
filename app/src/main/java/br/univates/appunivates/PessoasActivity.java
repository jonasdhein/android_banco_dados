package br.univates.appunivates;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.santalu.maskara.widget.MaskEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.univates.appunivates.controller.LinguagemController;
import br.univates.appunivates.controller.PessoaController;
import br.univates.appunivates.model.Linguagem;
import br.univates.appunivates.model.Nota;
import br.univates.appunivates.model.Pessoa;
import br.univates.appunivates.tools.Globais;

public class PessoasActivity extends AppCompatActivity {

    Button btnExcluir;
    EditText txtNome, txtDataNascimento;
    MaskEditText txtTelefone, txtCpf;
    Pessoa objeto;
    Spinner spiLinguagem;
    PessoaController controller;
    Context context;
    int id_pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        txtNome = findViewById(R.id.txtNome_pessoa);
        txtTelefone = findViewById(R.id.txtTelefone_pessoa);
        txtDataNascimento = findViewById(R.id.txtDataNasc_pessoa);
        txtCpf = findViewById(R.id.txtCpf_pessoa);
        spiLinguagem = findViewById(R.id.spiLinguagem_pessoa);
        btnExcluir = findViewById(R.id.btnExcluir_pessoa);

        context = PessoasActivity.this;

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id_pessoa > 0) {
                    controller = new PessoaController(context);
                    boolean ret = controller.excluir(id_pessoa);
                    if(ret){
                        Globais.exibirMensagem(context, "Pessoa excluída com sucesso!");
                        finish();
                    }
                }
            }
        });

        txtDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendario = Calendar.getInstance(); //retorna a data atual

                Date data;

                try{
                    if(txtDataNascimento.getText().toString().equals("")){
                        calendario = Calendar.getInstance();
                    }else{
                        String dtStart = txtDataNascimento.getText().toString();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            data = format.parse(dtStart);
                            calendario.setTime(data);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            calendario = Calendar.getInstance();
                        }
                    }

                    int ano = calendario.get(Calendar.YEAR);
                    int mes = calendario.get(Calendar.MONTH);
                    int dia = calendario.get(Calendar.DAY_OF_MONTH);

                    new DatePickerDialog(context, android.R.style.Widget_DeviceDefault_DatePicker, mDateSetListener, ano, mes, dia).show();

                }catch (Exception ex){
                    calendario = Calendar.getInstance();
                    Globais.exibirMensagem(context, "Erro ao abrir data");
                }

            }
        });

        LinguagemController objControllerLinguagem = new LinguagemController(context);
        ArrayList<Linguagem> lista = objControllerLinguagem.lista();

        ArrayAdapter<Linguagem> arrayAdapter = new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_1, lista);

        spiLinguagem.setAdapter(arrayAdapter);

        //verificar se veio um ID da tela anterior
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            id_pessoa = extras.getInt("id", 0);
            //buscar através desta chave
            controller = new PessoaController(context);
            objeto = controller.buscar(id_pessoa);
            if(objeto != null){
                txtNome.setText(objeto.getNome());

                String telefone = Globais.formataTelefone(objeto.getTelefone());
                txtTelefone.setText(telefone);

                String data = Globais.converterData(objeto.getData_nascimento(), "yyyy-MM-dd", "dd/MM/yyyy");
                txtDataNascimento.setText(data);

                String cpf = Globais.formataCPF(objeto.getCpf());
                txtCpf.setText(cpf);

                for(int i = 0; i < spiLinguagem.getAdapter().getCount(); i++){
                    Linguagem item = (Linguagem) spiLinguagem.getItemAtPosition(i);
                    if(item.getId() == objeto.getId_linguagem()){
                        spiLinguagem.setSelection(i);
                        break;
                    }
                }
            }

        }else{
            id_pessoa = 0;
            btnExcluir.setVisibility(View.GONE);
        }

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String data = (String.format("%02d", dayOfMonth)) + "/"+ (String.format("%02d", monthOfYear + 1)) + "/" + (String.format("%02d", year));
            txtDataNascimento.setText(data);
        }
    };

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

            String telefone = txtTelefone.getText().toString().trim();
            telefone = Globais.apenasNumeros(telefone);

            String cpf = txtCpf.getText().toString().trim();
            cpf = Globais.apenasNumeros(cpf);

            String data = txtDataNascimento.getText().toString(); //25/05/2022

            String data_formatada = Globais.converterData(data, "dd/MM/yyyy",
                    "yyyy-MM-dd"); //2022-05-25

            Linguagem linguagem = (Linguagem) spiLinguagem.getSelectedItem();
            int id_linguagem = linguagem.getId();

            objeto = new Pessoa();
            objeto.setNome(nome);
            objeto.setTelefone(telefone);
            objeto.setCpf(cpf);
            objeto.setId_linguagem(id_linguagem);
            objeto.setData_nascimento(data_formatada);

            controller = new PessoaController(context);
            boolean retorno;

            if(id_pessoa == 0){
                controller.incluir(objeto);
            }else {
                objeto.setId(id_pessoa);
                controller.alterar(objeto);
            }
            finish();

        }catch (Exception ex){
            Log.e("CATCH", ex.getMessage());
        }
    }
}