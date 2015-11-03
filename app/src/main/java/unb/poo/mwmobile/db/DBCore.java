package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;

// TODO modificar o autor e verificar a documentação Javadoc.

/**
 * @author Raphael Queiroz
 */
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
    private static final String KEY_CURSO = "curso";
    private static final String KEY_PERIODO = "periodo";
    private static SQLiteDatabase db;
//    TODO verificar estes campos a adicionar
//    Campos para adicionar
//    private Materia[] materias;
//    private Materia[] historico;
//    private double IRA;

    /**
     * Construtor do Banco de Dados
     * @param context       Contexto.
     */
    public DBCore(Context context){
        super(context, NOME_DB, null, VERSAO_DB);
    }

    /**
     * ON_CREATE
     * Criacao do banco de dados caso nao haja nenhum.
     * Deixei a criacao de varias tables por causa de normalizacao (de DB)
     * onde separa-se em varias tables para otimizacao e nao colocar uma array
     * inteira dentro de uma lacuna.
     * @param db            Banco de Dados a ser criado.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                "("+ KEY_ID + " INTEGER, " + KEY_MATRICULA + " INTEGER, " + KEY_SENHA + " TEXT, "
                + KEY_NOME + " TEXT, " + KEY_CURSO + " TEXT, " + KEY_PERIODO + " INTEGER)";
        db.execSQL(createDb);

        String createDbM = "CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA +
                "(" + KEY_MATRICULA + " INTEGER, " + KEY_IDM + " INTEGER, " + KEY_MATERIA + " TEXT)";
        db.execSQL(createDbM);

        String createDbH = "CREATE TABLE IF NOT EXISTS " + TABLE_HIST +
                "(" + KEY_MATRICULA + " INTEGER, " + KEY_IDM + " INTEGER, " + KEY_HIST + " TEXT)";
        db.execSQL(createDbH);

        this.db = db;
    }

    /**
     * ON_UPGRADE
     * Substitui um eventual Banco de Dados anterior.
     * Caso haja um banco de dados e queira criar um novo, drop table deleta o db anterior
     * e chama a função onCreate().
     * @param db                Banco de Dados.
     * @param oldVersion        Versao antiga do DB.
     * @param newVersion        Versao nova do DB.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST);
        onCreate(db);
    }

    /**
     * OPEN_WRITE
     * Abre o Banco de Dados (escrita).
     */
    private void openWrite(){
        db = this.getWritableDatabase();
    }

    /**
     * OPEN_READ
     * Abre o Banco de Dados (leitura).
     */
    private void openRead(){
        db = this.getReadableDatabase();
    }

    /**
     * CLOSE_DB
     * Fecha o banco de dados.
     */
    private void closeDB() {
        db.close();
    }

    /**
     * DROP_DB
     * Funcao para testes que dropa a tabela user do DB (onde armazena o usuario logado)
     */
    public void dropDB() {
        openWrite();

        onCreate(db);
        db.execSQL("DROP TABLE " + TABLE_USER);
        db.execSQL("DROP TABLE " + TABLE_MATERIA);
        db.execSQL("DROP TABLE " + TABLE_HIST);
        onCreate(db);

        closeDB();
    }

    /**
     * PRINT_DB
     * Imprime o Banco de Dados inteiro.
     */
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
                Log.d("CURSO", cursor.getString(4) + " ");
                Log.d("PERIODO", cursor.getString(5) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

        String query2 = "SELECT * FROM " + TABLE_MATERIA;
        Cursor cursor2 = db.rawQuery(query2, null);

        Log.d("Printing Materia", " ");

        if (cursor2.moveToFirst()) {
            do {
                Log.d("MATRICULA", cursor2.getString(0) + " ");
                Log.d("IDM", cursor2.getString(1) + " ");
                Log.d("MATERIA", cursor2.getString(2) + " ");
            } while (cursor2.moveToNext() || cursor2.isLast() == true);
        }

        String query3 = "SELECT * FROM " + TABLE_HIST;
        Cursor cursor3 = db.rawQuery(query3, null);

        Log.d("Printing Historico", " ");

        if (cursor3.moveToFirst()) {
            do {
                Log.d("MATRICULA", cursor3.getString(0) + " ");
                Log.d("IDM", cursor3.getString(1) + " ");
                Log.d("HISTORICO", cursor3.getString(2) + " ");
            } while (cursor3.moveToNext() || cursor3.isLast() == true);
        }

        closeDB();
    }


    /**
     * ADD_USER
     * Adiciona um User ao Banco de Dados. (utilizado para o login)
     * O DB tera 1 usuario, por enquanto.
     * @param user              Usuario a ser adicionado.
     * @param materia           Materias atuais do usuario.
     * @param historico         Historico do usuario.
     */
    public void addUser(User user, ArrayList<Materia> materia, ArrayList<MateriaCursada> historico){
        openWrite();

        ContentValues values0 = new ContentValues();

        values0.put(KEY_ID, 0);
        values0.put(KEY_MATRICULA, user.getMatricula());
        values0.put(KEY_SENHA, user.getSenha());
        values0.put(KEY_NOME, user.getNome());
        values0.put(KEY_CURSO, user.getCurso());
        values0.put(KEY_PERIODO, user.getPeriodo());

        db.insert(TABLE_USER, null, values0);

        for (int i = 0; i < user.getMaterias().size(); i++){
            ContentValues values1 = new ContentValues();

            String materi = materia.get(i).getNome();
            int code = materia.get(i).getCodigo();

            values1.put(KEY_MATRICULA, user.getMatricula());
            values1.put(KEY_IDM, code);
            values1.put(KEY_MATERIA, materi);

            db.insert(TABLE_MATERIA, null, values1);
        }

        for (int i = 0; i < user.getMaterias().size(); i++){
            ContentValues values2 = new ContentValues();

            int code = materia.get(i).getCodigo();
            String materi = materia.get(i).getNome();

            values2.put(KEY_MATRICULA, user.getMatricula());
            values2.put(KEY_IDM, code);
            values2.put(KEY_HIST, materi);

            db.insert(TABLE_HIST, null, values2);
        }

        for (int i = 0; i < user.getHistorico().size(); i++){
            ContentValues values3 = new ContentValues();

            int codeH = historico.get(i).getCodigo();
            String hist = historico.get(i).getNome();

            values3.put(KEY_MATRICULA, user.getMatricula());
            values3.put(KEY_IDM, codeH);
            values3.put(KEY_HIST, hist);

            db.insert(TABLE_HIST, null, values3);
        }

        closeDB();
    }

    /**
     * GET_USER
     * faz pesquisa no db por um usuario dado seu index; sempre sera usado 0 pois so um usuario estara
     * presente no DB (o unico logado).
     * @param index         Indice do usuario a ser pesquisado.
     * @return              Resultado da busca.
     */
    public User getUser(int index){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_ID + " = " + index;
        return search(query);
    }

    /**
     * GET_USER
     * Procura por um usuario utilizando uma string (matricula ou nome) como chave.
     * @param string        Matricula ou nome do usuario.
     * @return              Resultado da busca.
     */
    public User getUser(String string){
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = " + string +
                " OR " + KEY_NOME + " = " + string;
        return search(query);
    }

    /**
     * SEARCH
     * Funcao que, dada uma query, busca um User no Banco de Dados.
     * @param query         String de comando de busca no Banco de Dados.
     * @return              Usuario a ser encontrado, ou null se o mesmo nao existir.
     */
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

        String query2 = "SELECT * FROM " + TABLE_MATERIA;
        Cursor cursor2 = db.rawQuery(query2, null);

        if (cursor2.moveToFirst()) {
            do {
                Materia m = new Materia();
                m.setCodigo(cursor2.getInt(1));
                m.setNome(cursor2.getString(2));
            } while (cursor2.moveToNext() || cursor2.isLast() == true);
        }

        String query3 = "SELECT * FROM " + TABLE_HIST;
        Cursor cursor3 = db.rawQuery(query3, null);

        Log.d("Printing Historico", " ");

        if (cursor3.moveToFirst()) {
            do {
                MateriaCursada m = new MateriaCursada();
                m.setCodigo(cursor2.getInt(1));
                m.setNome(cursor2.getString(2));
            } while (cursor3.moveToNext() || cursor3.isLast() == true);
        }


        closeDB();
        return  user;
    }

    /**
     * UPDATE
     * Funcao que atualiza o usuario no banco de dados. faz uma atualizacao do banco de dados de acordo com a escolha
     * do controller como matricula e uma chave que nao se muda, sera usado ela como forma de achar o usuario;
     * assim, o usuario podera alterar sua senha (caso queira) e seu nome (caso errado)
     * TODO adicionar update relacionando as materias
     * @param user      Usuario a ser atualizado.
     */
    public void updUser(User user){
        openWrite();

        String update = "UPDATE " + TABLE_USER + " SET " + KEY_SENHA + " = " + user.getSenha() +
                " , " + KEY_NOME + " = " + user.getNome() + " WHERE " + KEY_MATRICULA +
                " = " + user.getMatricula();

        db.execSQL(update);

        closeDB();
    }

    /**
     * DELETE
     * Funcao que deleta um User do DB, procurando pela matricula.
     * @param user      Usuario a ser deletado.
     */
    public void delUser(User user){
        openWrite();

        String deletar = "DELETE FROM " + TABLE_USER + " WHERE " + KEY_MATRICULA + " = "
                + user.getMatricula();

        db.execSQL(deletar);

        closeDB();
    }
/*
    public void delMateria(User user, ArrayList<Materia> materias){
        openWrite();

        String updateH = "UPDATE " + TABLE_HIST + " SET " + KEY_HIST + " = " + materias.get(0).getNome()
                + " WHERE " + KEY_MATRICULA + " = " + user.getMatricula();
        String deletarM = "DELETE * FROM " + TABLE_MATERIA + "WHERE ROWNUM <= 1";
        db.execSQL(deletarM);

        closeDB();
    }

    public void updMateria(User user, ArrayList<Materia> materias){
        openWrite();

        String updateM = "UPDATE " + TABLE_MATERIA + " SET " + KEY_MATERIA + " = " + materias.get(0).getNome()
                + " WHERE " + KEY_MATRICULA + " = " + user.getMatricula();
        db.execSQL(updateM);

        closeDB();
    }*/
//    ========================================================================================
}
