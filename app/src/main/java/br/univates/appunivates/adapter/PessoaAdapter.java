package br.univates.appunivates.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.univates.appunivates.LinguagensActivity;
import br.univates.appunivates.PessoasActivity;
import br.univates.appunivates.R;
import br.univates.appunivates.controller.LinguagemController;
import br.univates.appunivates.model.Linguagem;
import br.univates.appunivates.model.Pessoa;
import br.univates.appunivates.tools.Globais;

public class PessoaAdapter extends ArrayAdapter<Pessoa> {

    private final Context context;
    private final ArrayList<Pessoa> elementos;

    public PessoaAdapter(Context context, ArrayList<Pessoa> elementos){
        super(context, R.layout.item_lista_pessoa, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Pessoa objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_pessoa, parent, false);

            TextView txtNome = rowView.findViewById(R.id.lblNome_item_pessoa);
            TextView txtTelefone = rowView.findViewById(R.id.lblTelefone_item_pessoa);
            TextView txtLinguagem = rowView.findViewById(R.id.lblLinguagem_item_pessoa);

            txtNome.setText(objeto.getNome());

            String telefone = Globais.formataTelefone(objeto.getTelefone());
            txtTelefone.setText(telefone);

            LinguagemController controllerLinguagem = new LinguagemController(context);
            Linguagem linguagem = controllerLinguagem.buscar(objeto.getId_linguagem());
            if(linguagem != null) {
                txtLinguagem.setText(linguagem.getNome());
            }

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent tela = new Intent(context, PessoasActivity.class);
                    tela.putExtra("id", objeto.getId());
                    context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}
