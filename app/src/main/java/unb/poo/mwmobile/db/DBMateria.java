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
import unb.poo.mwmobile.models.User;

/**
 * Created by Raphael on 25/10/2015.
 */
public class DBMateria {

    private static final String TABLE_MATERIA = "materia";

    private static final String KEY_IDM = "codigo";
    private static final String KEY_MATERIA = "materia";
    private static final String KEY_CREDITO = "creditos";
    private static final String KEY_OBRIG = "obrigatoria";

    private static final String KEY_PROFESSOR = "professor";
    private static final String KEY_TURMA = "turma";
    private static final String KEY_SALA = "sala";

    private static final String KEY_HORARIO = "horario";
    private static final String KEY_DIA = "dia";

    /**
     * DROP_DB
     * Funcao para testes que dropa a tabela materia do DB (onde armazena materias do usuario logado)
     * @param db
     */
    protected static void dropDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_MATERIA);
    }


    /**
     * PRINT_DB
     * Imprime o Banco de Dados de materias.
     */
    protected static void printDB(SQLiteDatabase db){

        String query = "SELECT * FROM " + TABLE_MATERIA;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing Materias", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("IDM", cursor.getInt(0) + " ");
                Log.d("MATERIA", cursor.getString(1) + " ");
                Log.d("CREDITOS", cursor.getInt(2) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

    }

    protected static void addMaterias(SQLiteDatabase db, User user){
        ArrayList<Materia> materias = user.getMaterias();
        addMaterias(db, materias);
    }

    protected static void addMaterias(SQLiteDatabase db, ArrayList<Materia> materias){

        ContentValues values;

        for (int i = 0; i < materias.size(); i++) {
            values = new ContentValues();

            values.put(KEY_IDM, materias.get(i).getCodigo());
            values.put(KEY_MATERIA, materias.get(i).getNome());
            values.put(KEY_CREDITO, materias.get(i).getCreditos());
//            values.put(KEY_PROFESSOR, materias.get(i).getProfessor().getNome());
//            values.put(KEY_TURMA, materias.get(i).getTurma());
//            values.put(KEY_SALA, materias.get(i).getSala());

            db.insert(TABLE_MATERIA, null, values);
        }
    }

    protected static Materia getMateria(SQLiteDatabase db, String string){
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_MATERIA + " = '" + string + "'";

        ArrayList<Materia> result = search(db, query);

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    protected static Materia getMateria(SQLiteDatabase db, int codigo){
        String query = "SELECT * FROM " + TABLE_MATERIA + " WHERE " + KEY_IDM + " = " + codigo;

        ArrayList<Materia> result = search(db, query);

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    protected static void getMaterias(SQLiteDatabase db, User user) {
        String query = "SELECT * FROM " + TABLE_MATERIA;
        user.setMaterias(search(db, query));
    }

    private static ArrayList<Materia> search(SQLiteDatabase db, String query){

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Materia> materias = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Materia materia = new Materia();

                materia.setCodigo(cursor.getInt(0));
                materia.setNome(cursor.getString(1));
                materia.setCreditos(cursor.getInt(2));
//                materia.setProfessor(new Professor(cursor.getString(3)));
//                materia.setTurma(cursor.getString(4));
//                materia.setSala(cursor.getString(5));

                materias.add(materia);
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }
        return materias;
    }

    /**
     * Atualiza uma materia no db.  As possiveis atualizacoes sao com
     * referencia ao nome, codigo ou turma nesse caso, mas podem ser alteradas
     * para atualizarmos outros dados.
     * @param materia   materia que devera ser atualizada.
     */
    protected static void updMateria(SQLiteDatabase db, Materia materia){
        String update = "UPDATE " + TABLE_MATERIA + " SET " +
                KEY_MATERIA + " = '" + materia.getNome() + "', " +
                KEY_CREDITO + " = '" + materia.getCodigo() +
//                KEY_HORARIO + " = '" + materia.getHorarios() + "', " +
//                KEY_PROFESSOR + " = '" + materia.getProfessor() + "', " +
//                KEY_TURMA + " = '" + materia.getTurma() + "', " +
//                KEY_SALA + " = '" + materia.getSala() + "', " +
                "' WHERE " + KEY_IDM + " = " + materia.getCodigo() + "";

        db.execSQL(update);

    }

    /**
     * Metodo que deleta uma materia alvo.
     * @param materia materia que sera deletada dentro do db.
     */
    protected static void delMateria(SQLiteDatabase db, Materia materia) {
        String deletar = "DELETE FROM " + TABLE_MATERIA + " WHERE " + KEY_MATERIA + " = '"
                + materia.getNome() + "'";

        db.execSQL(deletar);

    }
}
