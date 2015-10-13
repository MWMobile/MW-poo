package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import unb.poo.mwmobile.models.User;

public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_DB = "userStorage";
    private static final int VERSAO_DB = 1;

    private static final String TABLE_USER = "user";

    private static final String KEY_ID= "id";
    private static final String KEY_MATRICULA = "matricula";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_NOME = "nome";

//    Campos para adicionar
//    private Materia[] materias;
//    private Materia[] historico;
//    private double IRA;

    public DBCore(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    /*Criação do banco de dados caso não haja nenhum*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                "("+ KEY_ID + " INTEGER, " + KEY_MATRICULA + " INTEGER, " + KEY_SENHA + " TEXT, " + KEY_NOME + " TEXT)";
        db.execSQL(createDb);
    }

    /*Caso haja um banco de dados e queira criar um novo, drop table deleta o db anterior
    e chama a função onCreate*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_USER);
        onCreate(db);
    }

//    Funcao para testes que dropa a tabela user do DB (onde armazena o usuario logado)
    public void dropDB() {
        SQLiteDatabase db = this.getReadableDatabase();

        onCreate(db);
        db.execSQL("DROP TABLE " + TABLE_USER);
        onCreate(db);
    }

//    Funcao para testes que imprime o SQLite por inteiro
    public void printDB(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing DB", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", cursor.getString(0) + " ");
                Log.d("MATRICULA", cursor.getString(1) + " ");
                Log.d("SENHA", cursor.getString(2) + " ");
                Log.d("NOME", cursor.getString(3) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

    }


//    CRUD
//    ========================================================================================
//    CREATE
//    Adiciona um usuario no db
//    (usado para logar, o DB so tera 1 usuario por enquando)
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_ID, 0);
        values.put(KEY_MATRICULA, user.getMatricula());
        values.put(KEY_SENHA, user.getSenha());
        values.put(KEY_NOME, user.getNome());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

//    READ
//    faz pesquisa no db por um usuario dado seu index
//    sempre sera usado 0 pois so um usuario estara presente no DB, o unico logado
//    por motivos futuros o metodo de procura por string (matricula ou nome) foi adicionado
    public User getUser(int index){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_ID + " = " + index;
        return search(query);
    }

    public User getUser(String string){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = " + string +
                " OR " + KEY_NOME + " = " + string;
        return search(query);
    }

    private User search(String query) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getInt(1));
                user.setSenha(cursor.getString(2));
                user.setNome(cursor.getString(3));
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

        return  user;
    }

//    UPDATE
//    faz uma atualizacao do banco de dados de acordo com a escolha do controller
//    como matricula e uma chave que nao se muda, sera usado ela como forma de achar o usuario
//    assim, o usuario podera alterar sua senha (caso queira) e seu nome (caso errado)
//    em breve sera adicionado o update relacionado as materias
    public void updUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        String update = "UPDATE " + TABLE_USER + " SET " + KEY_SENHA + " = " + user.getSenha() +
                " , " + KEY_NOME + " = " + user.getNome() + " WHERE " + KEY_MATRICULA +
                " = " + user.getMatricula();

        db.execSQL(update);
    }

//    DELETE
    public void delUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        String deletar = "DELETE FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = "
                + user.getMatricula();

        db.execSQL(deletar);
    }
//    ========================================================================================
}
