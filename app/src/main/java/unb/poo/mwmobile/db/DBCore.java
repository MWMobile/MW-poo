package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.User;

public class DBCore extends SQLiteOpenHelper {

    private static final String NOME_DB = "userStorage";
    private static final int VERSAO_DB = 1;

    private static final String TABLE_USER = "user";
    private static final String TABLE_MATERIA = "materia";
    private static final String TABLE_HIST = "historico";

    private static final String KEY_ID= "id";
    private static final String KEY_IDM = "idM";
    private static final String KEY_MATRICULA = "matricula";
    private static final String KEY_MATERIA = "materia";
    private static final String KEY_HIST = "historico";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_NOME = "nome";
    private static SQLiteDatabase db;

//    Campos para adicionar
    private Materia[] materias;
    private Materia[] historico;
//    private double IRA;
//    TODO adicionar também curso e período

    public DBCore(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    /*Criação do banco de dados caso não haja nenhum*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                "("+ KEY_ID + " INTEGER, " + KEY_MATRICULA + " INTEGER, " + KEY_SENHA + " TEXT, " + KEY_NOME + " TEXT)";
        db.execSQL(createDb);

        String createDbM = "CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA +
                "(" + KEY_MATRICULA + " INTEGER, " + KEY_IDM + " INTEGER, " + KEY_MATERIA + " TEXT)";
        db.execSQL(createDbM);

        String createDbH = "CREATE TABLE IF NOT EXISTS " + TABLE_HIST +
                "(" + KEY_MATRICULA + " INTEGER, " + KEY_IDM + " INTEGER, " + KEY_HIST + " TEXT)";
        db.execSQL(createDbH);

        this.db = db;
    }

    /*Caso haja um banco de dados e queira criar um novo, drop table deleta o db anterior
    e chama a função onCreate*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST);
        onCreate(db);
    }

    private void openWrite(){
        db = this.getWritableDatabase();
    }

    private void openRead(){
        db = this.getReadableDatabase();
    }

    private void closeDB() {
        db.close();
    }

//    Funcao para testes que dropa a tabela user do DB (onde armazena o usuario logado)
    public void dropDB() {
        openWrite();

        onCreate(db);
        db.execSQL("DROP TABLE " + TABLE_USER);
        db.execSQL("DROP TABLE " + TABLE_MATERIA);
        db.execSQL("DROP TABLE " + TABLE_HIST);
        onCreate(db);

        closeDB();
    }

//    Funcao para testes que imprime o SQLite por inteiro
    public void printDB(){
        openRead();

        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing User", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", cursor.getString(0) + " ");
                Log.d("MATRICULA", cursor.getString(1) + " ");
                Log.d("SENHA", cursor.getString(2) + " ");
                Log.d("NOME", cursor.getString(3) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

        String query2 = "SELECT * FROM " + TABLE_MATERIA;
        Cursor cursor2 = db.rawQuery(query2, null);

        Log.d("Printing Materia", " ");

        if (cursor2.moveToFirst()) {
            do {
                Log.d("ID", cursor2.getString(0) + " ");
                Log.d("MATRICULA", cursor2.getString(1) + " ");
                Log.d("MATERIA", cursor2.getString(2) + " ");
            } while (cursor2.moveToNext() || cursor2.isLast() == true);
        }

        String query3 = "SELECT * FROM " + TABLE_HIST;
        Cursor cursor3 = db.rawQuery(query3, null);

        Log.d("Printing Historico", " ");

        if (cursor3.moveToFirst()) {
            do {
                Log.d("ID", cursor3.getString(0) + " ");
                Log.d("MATRICULA", cursor3.getString(1) + " ");
                Log.d("HISTORICO", cursor3.getString(2) + " ");
            } while (cursor3.moveToNext() || cursor3.isLast() == true);
        }

        closeDB();
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
        openRead();

        Cursor cursor = db.rawQuery(query, null);

        User user = null;
        if (cursor.moveToFirst()) {
            do {
                user = new User(cursor.getInt(1));
                user.setSenha(cursor.getString(2));
                user.setNome(cursor.getString(3));
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

        closeDB();
        return  user;
    }

//    UPDATE
//    faz uma atualizacao do banco de dados de acordo com a escolha do controller
//    como matricula e uma chave que nao se muda, sera usado ela como forma de achar o usuario
//    assim, o usuario podera alterar sua senha (caso queira) e seu nome (caso errado)
//    em breve sera adicionado o update relacionado as materias
    public void updUser(User user){
        openWrite();

        String update = "UPDATE " + TABLE_USER + " SET " + KEY_SENHA + " = " + user.getSenha() +
                " , " + KEY_NOME + " = " + user.getNome() + " WHERE " + KEY_MATRICULA +
                " = " + user.getMatricula();

        db.execSQL(update);

        closeDB();
    }

//    DELETE
    public void delUser(User user){
        openWrite();

        String deletar = "DELETE FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = "
                + user.getMatricula();

        db.execSQL(deletar);

        closeDB();
    }

    public void delMateria(User user){
        openWrite();

        String updateH = "UPDATE " + TABLE_HIST + " SET " + KEY_HIST + " = " + materias[0].getNome()
                + " WHERE " + KEY_MATRICULA + " = " + user.getMatricula();
        String deletarM = "DELETE * FROM " + TABLE_MATERIA + "WHERE ROWNUM <= 1";
        db.execSQL(deletarM);

        closeDB();
    }

    public void updMateria(User user){
        openWrite();

        String updateM = "UPDATE " + TABLE_MATERIA + " SET " + KEY_MATERIA + " = " + materias[0].getNome()
                + " WHERE " + KEY_MATRICULA + " = " + user.getMatricula();
        db.execSQL(updateM);

        closeDB();
    }
//    ========================================================================================
}
