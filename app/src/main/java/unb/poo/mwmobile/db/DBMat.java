package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Horario;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.Professor;

/**
 * Created by Raphael on 25/10/2015.
 */
public class DBMat extends SQLiteOpenHelper {

    private static final String NOME_DB = "materiaStorage";
    private static final int VERSAO_DB = 3;

    private static final String TABLE_MATERIA = "materiaDB";
    private static final String TABLE_HORARIO = "horario";

    private static final String KEY_IDM = "idM";
    private static final String KEY_MATERIA = "materia";
    private static final String KEY_CREDITO = "credito";
    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_TURMA = "turma";
    private static final String KEY_SALA = "sala";
    private static final String KEY_HORARIO = "horario";
    private static final String KEY_DIA = "dia";

    private static SQLiteDatabase db;

    /**
     * Construtor do DB de materias.
     * @param context           Contexto.
     */
    public DBMat(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    /**
     * onCreate
     * Cria um banco de dados de materias, se nao existir.
     * @param db                Banco de Dados.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA + "(" + KEY_IDM + " INTEGER, "
                + KEY_MATERIA + " TEXT, " + KEY_CREDITO + " INTEGER, " + KEY_PROFESSOR + " TEXT, "
                + KEY_TURMA + " TEXT, " + KEY_SALA + " TEXT)";
        db.execSQL(createDb);

        String createDbH = "CREATE TABLE IF NOT EXISTS " + TABLE_HORARIO + "(" + KEY_IDM + " INTEGER, "
                + KEY_MATERIA + " TEXT, " + KEY_HORARIO + " INTEGER, " + KEY_DIA + " INTEGER)";
        db.execSQL(createDbH);

        this.db = db;
    }

    /**
     * onUpgrade
     * Troca o Banco de Dados, dropando o anterior, se existir.
     * @param db                Banco de Dados.
     * @param oldVersion        Versao antiga do DB.
     * @param newVersion        Versao nova do DB.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HORARIO);
        onCreate(db);
    }

    /**
     * openWrite
     * Abre o Banco de Dados (escrita).
     */
    private void openWrite(){
        db = this.getWritableDatabase();
    }

    /**
     * openRead
     * Abre o Banco de Dados (leitura).
     */
    private void openRead(){
        db = this.getReadableDatabase();
    }

    /**
     * closeDB
     * Fecha o banco de dados.
     */
    private void closeDB() {
        db.close();
    }

    /**
     * dropDB
     * Dropa o Banco de Dados.
     */
    public void dropDB() {
        openWrite();

        onCreate(db);
        db.execSQL("DROP TABLE " + TABLE_MATERIA);
        db.execSQL("DROP TABLE " + TABLE_HORARIO);
        onCreate(db);

        closeDB();
    }

    /**
     * printDbM
     * Imprime o banco de dados das materias.
     */
    public void printDbM(){
        openRead();

        String query = "SELECT * FROM " + TABLE_MATERIA;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing Materia", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", cursor.getString(0) + " ");
                Log.d("MATERIA", cursor.getString(1) + " ");
                Log.d("CREDITO", cursor.getString(2) + " ");
                Log.d("PROFESSOR", cursor.getString(3) + " ");
                Log.d("TURMA", cursor.getString(4) + " ");
                Log.d("SALA", cursor.getString(5) + " ");
            } while(cursor.moveToNext() || cursor.isLast() == true);
        }

        String query2 = "SELECT * FROM "+ TABLE_HORARIO;
        Cursor cursor2 = db.rawQuery(query2, null);

        Log.d("Printing Horario", " ");

        if (cursor2.moveToFirst()) {
            do {
                Log.d("ID", cursor2.getString(0) + " ");
                Log.d("MATERIA", cursor2.getString(1) + " ");
                Log.d("HORARIO", cursor2.getString(2) + " ");
                Log.d("DIA", cursor2.getString(3) + " ");
            } while(cursor2.moveToNext() || cursor2.isLast() == true);
        }

        closeDB();
    }

    /**
     * addMat
     * Funcao que adicona as materias com seus campos nas duas tabelas desse db.
     * O formato esta do mesmo jeito do db de User (ate a explicação de porque duas TABLES)
     * @param materia               Materia a ser adicionada.
     */
    public void addMat(ArrayList<Materia> materia){
        openWrite();

        for (int i = 0; i < materia.size(); i++) {
            ContentValues values = new ContentValues();

            int codeM = materia.get(i).getCodigo();
            String nomeM = materia.get(i).getNome();
            int credM = materia.get(i).getCreditos();
            String nomeP = materia.get(i).getProfessor().getNome();
            String turmaM = materia.get(i).getTurma();
            String salaM = materia.get(i).getSala();

            values.put(KEY_IDM, codeM);
            values.put(KEY_MATERIA, nomeM);
            values.put(KEY_CREDITO, credM);
            values.put(KEY_PROFESSOR, nomeP);
            values.put(KEY_TURMA, turmaM);
            values.put(KEY_SALA, salaM);

            db.insert(TABLE_MATERIA, null, values);

            ContentValues values1 = new ContentValues();

            int horaM = materia.get(i).getHora();
            int diaM = materia.get(i).getDia();

            values1.put(KEY_IDM, codeM);
            values1.put(KEY_MATERIA, nomeM);
            values1.put(KEY_HORARIO, horaM);
            values1.put(KEY_DIA, diaM);

            db.insert(TABLE_HORARIO, null, values1);
        }

        closeDB();
    }
    // TODO metodos getMateria, delMateria


    /**
     * Metodo ainda nao descrito, criado apenas para simular o acesso utilizado nos testes.
     * @return retorna a mareria cujo nome foi passado inicialmente.
     */
    public Materia getMateria(){
        String query = "SELECT * FROM " + TABLE_MATERIA;
        return search(query);
    }

    public Materia getMateria(String string){
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_MATERIA + " = " + string;
        return search(query);
    }

    public Materia getMateria(int codigo){
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_IDM + " = " + codigo;
        return search(query);
    }

    /**
     * Funcao de busca de Materia dentro do DB
     * @return Retorna a materia caracterizada pelos parametros da busca
     */

    public Materia search(String query){
        openRead();

        String query2 = "SELECT * FROM " + TABLE_HORARIO;
        Cursor cursor2 = db.rawQuery(query2, null);
        Cursor cursor = db.rawQuery(query, null);

        Materia materias = null;
        if (cursor.moveToFirst() && cursor2.moveToFirst()) {
            do {
                materias = new Materia();
                materias.setCodigo(cursor.getInt(0));
                materias.setNome(cursor.getString(1));
                materias.setCreditos(cursor.getInt(2));
                materias.setProfessor(new Professor(cursor.getString(3)));
                materias.setTurma(cursor.getString(4));
                materias.setSala(cursor.getString(5));
                materias.setDia(cursor2.getInt(3));
                materias.setHora(cursor2.getInt(2));
            } while (cursor.moveToNext() || cursor.isLast() == true && cursor2.moveToNext() || cursor2.isLast() == true);
        }

        closeDB();
        return  materias;
      }

    /**
     * Metodo que deleta uma materia alvo.
     * @param materia materia que sera deletada dentro do db.
     */
    public void delMateria(Materia materia) {
        openWrite();

        String deletar = "DELETE FROM " + TABLE_MATERIA + " WHERE " + KEY_MATERIA + " = '"
                + materia.getNome() + "'";


        db.execSQL(deletar);

        closeDB();

    }

    /**
     * Atualiza uma materia no db.  As possiveis atualizacoes sao com
     * referencia ao nome, codigo ou turma nesse caso, mas podem ser alteradas
     * para atualizarmos outros dados.
     * @param materia   materia que devera ser atualizada.
     */
    public void updMateria(Materia materia){
        openWrite();

        String update = "UPDATE " + TABLE_MATERIA + " SET " + KEY_MATERIA + " = '" + materia.getNome() +
                "', " + KEY_IDM + " = '" + materia.getCodigo() + "' WHERE " + KEY_TURMA +
                " = '" + materia.getTurma() + "'";

        db.execSQL(update);

        closeDB();
    }


//    Falta passar o Context para a criacao do banco de dados



}
