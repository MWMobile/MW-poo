package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BD {
    private SQLiteDatabase bd;

    /*Função de escrita do bando de dados local*/
    public BD (Context context){
        BDCore db = new BDCore(context);
        bd = db.getWritableDatabase();
    }

    /*Função que insere um usuário fictício para teste*/
    public void inserir(){
        ContentValues valores = new ContentValues();
        valores.put("matricula", "140123456");
        valores.put("senha", "USER1234");

        bd.insert("user", null, valores);
    }

    /*Função que auntentica o usuário teste
    public int buscar(int matric, String passw) {
        String[] colunas = new String[]{"matricula", "senha"};
        Cursor c = bd.query("user", colunas, null, null, null, null);

        if(c.getCount() > 0){
            c.moveToFirst();
            do{
                if(matric = c.getMatricula()){
                    if(passw.equals(c.getSenha()){
                        int count = 1;
                        return 1;
                    }
                }else{
                    return 0;
                }
            }while(c.moveToNext() || count = 1);
        }
    }*/
}
