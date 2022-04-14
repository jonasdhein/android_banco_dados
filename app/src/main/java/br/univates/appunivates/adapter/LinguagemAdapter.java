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

import br.univates.appunivates.R;
import br.univates.appunivates.model.Linguagem;

public class LinguagemAdapter extends ArrayAdapter<Linguagem> {

    private final Context context;
    private final ArrayList<Linguagem> elementos;

    public LinguagemAdapter(Context context, ArrayList<Linguagem> elementos){
        super(context, R.layout.item_lista_linguagem, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        try{
            Linguagem objeto = elementos.get(position);

            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //toda vez que passa por um item da lista, os elementos são associados
            View rowView = inflater.inflate(R.layout.item_lista_linguagem, parent, false);

            TextView nome = rowView.findViewById(R.id.lblNome_item_linguagem);
            TextView descricao = rowView.findViewById(R.id.lblDescricao_item_linguagem);

            nome.setText(objeto.getNome());
            descricao.setText(objeto.getDescricao());

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent tela = new Intent(context, NOVATELAAQUI_Activity.class);
                    //tela.putExtra("id", objeto.getId());
                    //context.startActivity(tela);
                }
            });

            return rowView;

        }catch (Exception ex){
            Log.e("ERRO_ADAPTER", ex.getMessage());
            return null;
        }

    }
}
