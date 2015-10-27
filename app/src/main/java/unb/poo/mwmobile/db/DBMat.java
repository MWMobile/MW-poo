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

/**
 * Created by Raphael on 25/10/2015.
 */
public class DBMat extends SQLiteOpenHelper {

    private static final String NOME_DB = "materiaStorage";
    private static final int VERSAO_DB = 2;

    private static final String TABLE_MATERIA = "materia";
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

    public DBMat(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDb = "(CREATE TABLE IF NOT EXISTS " + TABLE_MATERIA + "(" + KEY_IDM + " INTEGER, "
                + KEY_MATERIA + " TEXT, " + KEY_CREDITO + " INTEGER, " + KEY_PROFESSOR + " TEXT, "
                + KEY_TURMA + " TEXT, " + KEY_SALA + " TEXT)";
        db.execSQL(createDb);

        String createDbH = "(CREATE TABLE IF NOT EXISTS " + TABLE_HORARIO + "(" + KEY_IDM + " INTEGER, "
                + KEY_MATERIA + " TEXT, " + KEY_HORARIO + " INTEGER, " + KEY_DIA + " INTEGER)";
        db.execSQL(createDbH);

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATERIA);
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

    public void dropDB() {
        openWrite();

        onCreate(db);
        db.execSQL("DROP TABLE " + TABLE_MATERIA);
        onCreate(db);

        closeDB();
    }

    public void printDbM(){
        openRead();

        String query = "SELECT * FROM "+ TABLE_MATERIA;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printint Materia", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("ID", cursor.getString(1) + " ");
                Log.d("MATERIA", cursor.getString(2) + " ");
                Log.d("CREDITO", cursor.getString(3) + " ");
                Log.d("PROFESSOR", cursor.getString(4) + " ");
                Log.d("TURMA", cursor.getString(5) + " ");
                Log.d("SALA", cursor.getString(6) + " ");
            } while(cursor.moveToNext() || cursor.isLast() == true);
        }

        String query2 = "SELECT * FROM "+ TABLE_HORARIO;
        Cursor cursor2 = db.rawQuery(query2, null);

        Log.d("Printint Horario", " ");

        if (cursor2.moveToFirst()) {
            do {
                Log.d("ID", cursor2.getString(1) + " ");
                Log.d("MATERIA", cursor2.getString(2) + " ");
                Log.d("HORARIO", cursor2.getString(3) + " ");
                Log.d("DIA", cursor2.getString(4) + " ");
            } while(cursor2.moveToNext() || cursor2.isLast() == true);
        }

        closeDB();
    }

    /*Funcao que adicona as materia com seus campos nas duas tabelas desse db.
    * O formato esta do mesmo jeito do db de User (ate a explicacao de
    * pq duas TABLES)*/
    public void addMat(Materia materia, ArrayList<Horario> horarios){
        ContentValues values = new ContentValues();

        values.put(KEY_IDM, materia.getCodigo());
        values.put(KEY_MATERIA, materia.getNome());
        values.put(KEY_CREDITO, materia.getCreditos());
        values.put(KEY_PROFESSOR, materia.getProfessor().getNome());
        values.put(KEY_TURMA, materia.getTurma());
        values.put(KEY_SALA, materia.getSala());

        db.insert(TABLE_MATERIA, null, values);

        for (int i = 0; i < materia.getHorarios().size(); i++){
            ContentValues values1 = new ContentValues();

            int hora = horarios.get(i).getHora();
            int dia = horarios.get(i).getDia();

            values1.put(KEY_IDM, materia.getCodigo());
            values1.put(KEY_MATERIA, materia.getNome());
            values1.put(KEY_HORARIO, hora);
            values1.put(KEY_DIA, dia);

            db.insert(TABLE_HORARIO, null, values1);
        }
    }

    /*public Materia getMateria(String string){
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_MATERIA + " = " + string
                + " OR " + KEY_IDM + " = " + string;
        return search(query);
    }*/

    /*Funcao de procura da materia solicitada na funcao acima*/
    /*public Materia search(String query){
        openRead();

        Cursor cursor = db.rawQuery(query, null);

        Materia materias = null;
        if (cursor.moveToFirst()) {
            do {
                //materias = new Materia(cursor.getString(1));
                materias.setNome(cursor.getString(1));
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

        closeDB();
        return  materias;
    }*/

//    Falta passar o Context para a criacao do vando de dados
}
