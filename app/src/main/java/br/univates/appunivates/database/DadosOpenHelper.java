package br.univates.appunivates.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.univates.appunivates.tools.Globais;

public class DadosOpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 8; //versão do banco de dados
    private static final String NM_BANCO = "banco";
    private Context context;

    public DadosOpenHelper(Context context) {
        super(context, NM_BANCO, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            StringBuilder sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_LINGUAGENS);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" id_categoria INTEGER, ");
            sql.append(" nome VARCHAR(30) NOT NULL, ");
            sql.append(" descricao TEXT, ");
            sql.append(" favorito BIT, ");
            sql.append(" nota INTEGER ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

            sql = new StringBuilder();
            sql.append(" CREATE TABLE IF NOT EXISTS ");
            sql.append(Tabelas.TB_PESSOAS);
            sql.append(" ( ");
            sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
            sql.append(" nome VARCHAR(30) NOT NULL, ");
            sql.append(" telefone VARCHAR(15), ");
            sql.append(" data_nascimento DATE, ");
            sql.append(" id_linguagem INTEGER, ");
            sql.append(" cpf VARCHAR(11) ");
            sql.append(" ) ");
            db.execSQL(sql.toString());

        }catch (Exception ex){
            Globais.exibirMensagem(context, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            StringBuilder sql;

            if(newVersion >= 2){
                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" nota INTEGER ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" telefone VARCHAR(15) ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" data_nascimento DATE ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_LINGUAGENS);
                    sql.append(" DROP COLUMN ");
                    sql.append(" data_nascimento DATE ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" CREATE TABLE IF NOT EXISTS ");
                    sql.append(Tabelas.TB_PESSOAS);
                    sql.append(" ( ");
                    sql.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ");
                    sql.append(" nome VARCHAR(30) NOT NULL, ");
                    sql.append(" telefone VARCHAR(15), ");
                    sql.append(" data_nascimento DATE ");
                    sql.append(" ) ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_PESSOAS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" cpf VARCHAR(11) ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

                try {
                    sql = new StringBuilder();
                    sql.append(" ALTER TABLE ");
                    sql.append(Tabelas.TB_PESSOAS);
                    sql.append(" ADD COLUMN ");
                    sql.append(" id_linguagem INTEGER ");
                    db.execSQL(sql.toString());
                }catch (Exception ex){
                    Log.e("ALTER_TABLE", ex.getMessage());
                }

            }

        }catch (Exception ex){
            Log.e("ONUPGRADE", ex.getMessage());
        }
    }
}
