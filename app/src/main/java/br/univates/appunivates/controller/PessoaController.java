package br.univates.appunivates.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.univates.appunivates.database.DadosOpenHelper;
import br.univates.appunivates.database.Tabelas;
import br.univates.appunivates.model.Linguagem;
import br.univates.appunivates.model.Pessoa;
import br.univates.appunivates.tools.Globais;

public class PessoaController {

    private SQLiteDatabase conexao;
    private Context context;

    public PessoaController(Context context){
        DadosOpenHelper banco = new DadosOpenHelper(context);
        this.conexao = banco.getWritableDatabase();
        this.context = context;
    }

    public Pessoa buscar(int id){
        Pessoa objeto = null;

        try{

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM ");
            sql.append(Tabelas.TB_PESSOAS);
            sql.append(" WHERE id = '"+ id +"'");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToNext()){
                objeto = new Pessoa();

                objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone")));
                objeto.setData_nascimento(resultado.getString(resultado.getColumnIndexOrThrow("data_nascimento")));
                objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));
                objeto.setId_linguagem(resultado.getInt(resultado.getColumnIndexOrThrow("id_linguagem")));
            }

            return objeto;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return objeto;
        }
    }

    public boolean incluir(Pessoa objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());
            valores.put("telefone", objeto.getTelefone());
            valores.put("data_nascimento", objeto.getData_nascimento());
            valores.put("cpf", objeto.getCpf());
            valores.put("id_linguagem", objeto.getId_linguagem());

            conexao.insertOrThrow(Tabelas.TB_PESSOAS, null,
                    valores);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean alterar(Pessoa objeto){
        try{

            ContentValues valores = new ContentValues();
            valores.put("nome", objeto.getNome());
            valores.put("telefone", objeto.getTelefone());
            valores.put("data_nascimento", objeto.getData_nascimento());
            valores.put("cpf", objeto.getCpf());
            valores.put("id_linguagem", objeto.getId_linguagem());

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(objeto.getId());

            conexao.update(Tabelas.TB_PESSOAS, valores, "id = ?" , parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public boolean excluir(int id){
        try{

            String[] parametros = new String[1];
            parametros[0] = String.valueOf(id);

            conexao.delete(Tabelas.TB_PESSOAS, "id = ?", parametros);

            return true;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return false;
        }
    }

    public ArrayList<Pessoa> lista(){

        ArrayList<Pessoa> listagem = new ArrayList<>();
        try{

            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM ");
            sql.append(Tabelas.TB_PESSOAS);
            sql.append(" ORDER BY nome ");

            Cursor resultado = conexao.rawQuery(sql.toString(), null);
            if(resultado.moveToFirst()){

                Pessoa objeto;
                do{
                    objeto = new Pessoa();

                    objeto.setId(resultado.getInt(resultado.getColumnIndexOrThrow("id")));
                    objeto.setNome(resultado.getString(resultado.getColumnIndexOrThrow("nome")));
                    objeto.setTelefone(resultado.getString(resultado.getColumnIndexOrThrow("telefone")));
                    objeto.setData_nascimento(resultado.getString(resultado.getColumnIndexOrThrow("data_nascimento")));
                    objeto.setCpf(resultado.getString(resultado.getColumnIndexOrThrow("cpf")));
                    objeto.setId_linguagem(resultado.getInt(resultado.getColumnIndexOrThrow("id_linguagem")));

                    listagem.add(objeto);

                }while (resultado.moveToNext());

            }

            return listagem;

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
            return listagem;
        }
    }

}
