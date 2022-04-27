package br.univates.appunivates.tools;

import android.content.Context;
import android.widget.Toast;

public class Globais {

    public static void exibirMensagem(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

}
