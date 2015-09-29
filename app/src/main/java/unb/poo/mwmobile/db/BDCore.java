package unb.poo.mwmobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
    private static final String NOME_BD = "user";
    private static final int VERSAO_BD = 1;

    public BDCore(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    /*Criação do bando de dados caso não haja nenhum*/
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table user(_id matricula integer primary key, senha text not null;");
    }

    /*Caso haja um banco de dados e queira criar um novo, drop table deleta o bd anterior e chama a função onCreate*/
    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        bd.execSQL("drop table user;");
        onCreate(bd);
    }
}
