package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.Professor;
import unb.poo.mwmobile.models.User;

// TODO modificar o autor e verificar a documentação Javadoc.

/**
 * @author Raphael Queiroz & Andrei Sousa
 */
public class DBCore extends SQLiteOpenHelper {

    private static final String TAG = "DBCORE";

    private static final String NOME_DB = "userStorage";
    private static final int VERSAO_DB = 1;

    private static final String TABLE_USER = "user";
    private static final String TABLE_MATERIA = "materia";
    private static final String TABLE_HIST = "historico";

    private static final String KEY_ID= "id";
    private static final String KEY_IDM = "idM";
    private static final String KEY_MATRICULA = "matricula";
    private static final String KEY_MATERIA = "materia";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_NOME = "nome";
    private static final String KEY_CURSO = "curso";
    private static final String KEY_PERIODO = "periodo";
    private static final String KEY_CREDITO = "creditos";
    private static final String KEY_MENCAO = "mencao";
    private static final String KEY_OBRIG = "obrigatoria";

    private static SQLiteDatabase db;

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

        String createDbM = "CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA + " (" +
                KEY_IDM + " INTEGER, " +
                KEY_MATERIA + " TEXT, " +
                KEY_CREDITO + " INTEGER" + ")";
        db.execSQL(createDbM);

        String createDbH = "CREATE TABLE IF NOT EXISTS " + TABLE_HIST +
                "(" + KEY_MATRICULA + " INTEGER, " + KEY_IDM + " INTEGER, " + KEY_MATERIA + " TEXT, "
                + KEY_MENCAO + " TEXT, " + KEY_OBRIG + " INTEGER, " + KEY_PERIODO + " INTEGER, "
                + KEY_CREDITO + " INTEGER)";
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
        DBUser.dropDB(db);
        DBMateria.dropDB(db);

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

        DBUser.printDB(db);

        DBMateria.printDB(db);

        String query3 = "SELECT * FROM " + TABLE_HIST;
        Cursor cursor3 = db.rawQuery(query3, null);

        Log.d("Printing Historico", " ");

        if (cursor3.moveToFirst()) {
            do {
                Log.d("MATRICULA", cursor3.getString(0) + " ");
                Log.d("IDM", cursor3.getInt(1) + " ");
                Log.d("MATERIA", cursor3.getString(2) + " ");
                Log.d("MENCAO", cursor3.getString(3) + " ");
                Log.d("OBRIGATORIA", cursor3.getString(4) + " ");
                Log.d("PERIODO", cursor3.getInt(5) + " ");
                Log.d("CREDITOS", cursor3.getInt(6) + " ");
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

        DBUser.addUser(db, user);

        DBMateria.addMaterias(db, user);

        for (int i = 0; i < user.getHistorico().size(); i++){
            ContentValues values2 = new ContentValues();

            values2.put(KEY_MATRICULA, user.getMatricula());
            values2.put(KEY_IDM, historico.get(i).getCodigo());
            values2.put(KEY_MATERIA, historico.get(i).getNome());
            values2.put(KEY_MENCAO, historico.get(i).getMencao());
            values2.put(KEY_OBRIG, historico.get(i).getObrigatoria());
            values2.put(KEY_PERIODO, historico.get(i).getPeriodoCursado());
            values2.put(KEY_CREDITO, historico.get(i).getCreditos());

            db.insert(TABLE_HIST, null, values2);
        }

        closeDB();
    }

    /**
     * GET_USER
     * faz pesquisa no db por um usuario dado seu index; sempre sera usado 0 pois so um usuario estara
     * presente no DB (o unico logado).
     * @param id         Indice do usuario a ser pesquisado.
     * @return              Resultado da busca.
     */
    public User getUser(int id){
        openRead();

        User user = search(DBUser.getUser(id, db));

        closeDB();

        return user;
    }

    /**
     * GET_USER
     * Procura por um usuario utilizando uma string (matricula ou nome) como chave.
     * @param string        Matricula ou nome do usuario.
     * @return              Resultado da busca.
     */
    public User getUser(String string){
        openRead();

        User user = search(DBUser.getUser(string, db));

        closeDB();

        return user;
    }

    /**
     * SEARCH
     * Funcao que, dada uma query, busca um User no Banco de Dados.
     * @param user         String de comando de busca no Banco de Dados.
     * @return              Usuario a ser encontrado, ou null se o mesmo nao existir.
     */
    private User search(User user) {

        if(user != null) {

            DBMateria.getMaterias(db, user);

            String query = "SELECT * FROM " + TABLE_HIST;
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                ArrayList<MateriaCursada> h = new ArrayList<>();
                do {
                    MateriaCursada mat2 = new MateriaCursada();

                    String nomeM = cursor.getString(2);
                    int codigoM = cursor.getInt(1);
                    int creditM = cursor.getInt(6);
                    String mencao = cursor.getString(3);
                    int obrigatoria = cursor.getInt(4);
                    int period = cursor.getInt(5);

                    if (obrigatoria == 1) {
                        boolean obrig = true;
                        mat2.setObrigatoria(obrig);
                    } else {
                        boolean obrig = false;
                        mat2.setObrigatoria(obrig);
                    }

                    mat2.setCodigo(codigoM);
                    mat2.setNome(nomeM);
                    mat2.setCreditos(creditM);
                    mat2.setMencao(mencao);
                    mat2.setPeriodoCursado(period);

                    h.add(mat2);
                } while (cursor.moveToNext() || cursor.isLast() == true);
                user.setHistorico(h);
            }
        }

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

        DBUser.updUser(db, user);

        closeDB();
    }

    /**
     * DELETE
     * Funcao que deleta um User do DB, procurando pela matricula.
     * @param user      Usuario a ser deletado.
     */
    public void delUser(User user){
        openWrite();

        DBUser.delUser(db, user);
        DBMateria.dropDB(db);

        closeDB();
    }

    public void addMaterias(ArrayList<Materia> materias){
        openWrite();

        DBMateria.addMaterias(db, materias);

        closeDB();
    }

    public Materia getMateria(String nome){
        openRead();

        Materia materia = DBMateria.getMateria(db, nome);

        closeDB();

        return materia;
    }

    public Materia getMateria(int codigo){
        openRead();

        Materia materia = DBMateria.getMateria(db, codigo);

        closeDB();

        return materia;
    }

    public void updMateria(Materia materia){
        openWrite();

        DBMateria.updMateria(db, materia);

        closeDB();
    }

    public void delMateria(Materia materia){
        openWrite();

        DBMateria.delMateria(db, materia);

        closeDB();
    }

}
