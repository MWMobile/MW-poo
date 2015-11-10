package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 10/11/2015.
 */
public class DBUser{

    private static final String TAG = "DBUSER";

    private static final String TABLE_USER = "user";

    private static final String KEY_ID= "id";
    private static final String KEY_MATRICULA = "matricula";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_NOME = "nome";
    private static final String KEY_CURSO = "curso";
    private static final String KEY_PERIODO = "periodo";

    /**
     * DROP_DB
     * Funcao para testes que dropa a tabela user do DB (onde armazena o usuario logado)
     * @param db
     */
    protected static void dropDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_USER);
    }

    /**
     * PRINT_DB
     * Imprime o Banco de Dados do usuario.
     */
    protected static void printDB(SQLiteDatabase db){

        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing User", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", cursor.getString(0) + " ");
                Log.d("MATRICULA", cursor.getInt(1) + " ");
                Log.d("SENHA", cursor.getString(2) + " ");
                Log.d("NOME", cursor.getString(3) + " ");
                Log.d("CURSO", cursor.getString(4) + " ");
                Log.d("PERIODO", cursor.getString(5) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

    }

    /**
     * ADD_USER
     * Adiciona um User ao Banco de Dados. (utilizado para o login)
     * O DB tera 1 usuario, por enquanto.
     * @param user              Usuario a ser adicionado.
     */
    protected static void addUser(SQLiteDatabase db, User user){

        ContentValues values = new ContentValues();

        values.put(KEY_ID, 0);
        values.put(KEY_MATRICULA, user.getMatricula());
        values.put(KEY_SENHA, user.getSenha());
        values.put(KEY_NOME, user.getNome());
        values.put(KEY_CURSO, user.getCurso());
        values.put(KEY_PERIODO, user.getPeriodo());

        db.insert(TABLE_USER, null, values);
    }

    /**
     * GET_USER
     * faz pesquisa no db por um usuario dado seu index; sempre sera usado 0 pois so um usuario estara
     * presente no DB (o unico logado).
     * @param id         Indice do usuario a ser pesquisado.
     * @return              Resultado da busca.
     */
    protected static User getUser(int id, SQLiteDatabase db){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_ID + " = " + id;
        return search(query, db);
    }

    /**
     * GET_USER
     * Procura por um usuario utilizando uma string (matricula ou nome) como chave.
     * @param string        Matricula ou nome do usuario.
     * @return              Resultado da busca.
     */
    protected static User getUser(String string, SQLiteDatabase db){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = " + string +
                " OR " + KEY_NOME + " = " + string;
        return search(query, db);
    }

    /**
     * SEARCH
     * Funcao que, dada uma query, busca um User no Banco de Dados.
     * @param query         String de comando de busca no Banco de Dados.
     * @return              Usuario a ser encontrado, ou null se o mesmo nao existir.
     */
    private static User search(String query, SQLiteDatabase db) {

        Cursor userCursor = db.rawQuery(query, null);

        User user = null;
        if (userCursor.moveToFirst()) {
            do {
                user = new User(userCursor.getInt(1));
                user.setSenha(userCursor.getString(2));
                user.setNome(userCursor.getString(3));
                user.setCurso(userCursor.getString(4));
                user.setPeriodo(userCursor.getInt(5));
            } while (userCursor.moveToNext() || userCursor.isLast());
        }

        return  user;
    }

    /**
     * UPDATE
     * Funcao que atualiza o usuario no banco de dados. faz uma atualizacao do banco de dados de acordo com a escolha
     * do controller como matricula e uma chave que nao se muda, sera usado ela como forma de achar o usuario;
     * assim, o usuario podera alterar sua senha (caso queira) e seu nome (caso errado)
     * @param user      Usuario a ser atualizado.
     */
    protected static void updUser(SQLiteDatabase db, User user){

        String update = "UPDATE " + TABLE_USER + " SET " + KEY_SENHA + " = " + user.getSenha() +
                " , " + KEY_NOME + " = " + user.getNome() + " WHERE " + KEY_MATRICULA +
                " = " + user.getMatricula();

        db.execSQL(update);
    }

    /**
     * DELETE
     * Funcao que deleta um User do DB, procurando pela matricula.
     * @param user      Usuario a ser deletado.
     */
    protected static void delUser(SQLiteDatabase db, User user){

        String deletar = "DELETE FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = "
                + user.getMatricula();

        db.execSQL(deletar);
    }

}
