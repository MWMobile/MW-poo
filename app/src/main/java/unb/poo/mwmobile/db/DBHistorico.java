package unb.poo.mwmobile.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;

/**
 * Created by sousa on 10/11/2015.
 */
public class DBHistorico {
    private static final String TABLE_HIST = "historico";
    private static final String KEY_IDM = "codigo";
    private static final String KEY_MATRICULA = "matricula";
    private static final String KEY_MATERIA = "materia";
    private static final String KEY_SENHA = "senha";
    private static final String KEY_NOME = "nome";
    private static final String KEY_CURSO = "curso";
    private static final String KEY_PERIODO = "periodo";
    private static final String KEY_CREDITO = "creditos";
    private static final String KEY_MENCAO = "mencao";
    private static final String KEY_OBRIG = "obrigatoria";

    /**
     * DROP_DB
     * Funcao para testes que dropa a tabela materia do DB (onde armazena materias do usuario logado)
     * @param db
     */
    protected static void dropDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE " + TABLE_HIST);
    }


    /**
     * PRINT_DB
     * Imprime o Banco de Dados de materias.
     */
    protected static void printDB(SQLiteDatabase db){

        String query = "SELECT * FROM " + TABLE_HIST;
        Cursor cursor = db.rawQuery(query, null);

        Log.d("Printing Materias", " ");

        if (cursor.moveToFirst()) {
            do {
                Log.d("IDM", cursor.getInt(1) + " ");
                Log.d("MATERIA", cursor.getString(2) + " ");
                Log.d("CREDITOS", cursor.getInt(6) + " ");
                Log.d("MENCAO",cursor.getString(3) + " ");
                Log.d("PERIODO",cursor.getInt(5) + " ");
            } while (cursor.moveToNext() || cursor.isLast() == true);
        }

    }

    protected static void addMaterias(SQLiteDatabase db, User user){
        ArrayList<MateriaCursada> materias = user.getHistorico();
        addMaterias(db, materias, user.getMatricula());
    }

    protected static void addMaterias(SQLiteDatabase db, ArrayList<MateriaCursada> materias, int matricula){

        ContentValues values;

        for (int i = 0; i < materias.size(); i++) {
            values = new ContentValues();
            values.put(KEY_MATRICULA,matricula);
            values.put(KEY_IDM, materias.get(i).getCodigo());
            values.put(KEY_MATERIA, materias.get(i).getNome());
            values.put(KEY_MENCAO, materias.get(i).getMencao());
            values.put(KEY_OBRIG, materias.get(i).getObrigatoria() ? 1 : 0);
            values.put(KEY_PERIODO, materias.get(i).getPeriodoCursado());
            values.put(KEY_CREDITO, materias.get(i).getCreditos());

//            values.put(KEY_PROFESSOR, materias.get(i).getProfessor().getNome());
//            values.put(KEY_TURMA, materias.get(i).getTurma());
//            values.put(KEY_SALA, materias.get(i).getSala());

            db.insert(TABLE_HIST, null, values);
        }
    }

    protected static MateriaCursada getMateria(SQLiteDatabase db, String string){
        String query = "SELECT * FROM " + TABLE_HIST + " WHERE " + KEY_MATERIA + " = '" + string + "'";

        ArrayList<MateriaCursada> result = search(db, query);

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    protected static MateriaCursada getMateria(SQLiteDatabase db, int codigo){
        String query = "SELECT * FROM " + TABLE_HIST + " WHERE " + KEY_IDM + " = " + codigo;

        ArrayList<MateriaCursada> result = search(db, query);

        if(!result.isEmpty())
            return result.get(0);
        else
            return null;
    }

    protected static void getMaterias(SQLiteDatabase db, User user) {
        String query = "SELECT * FROM " + TABLE_HIST;
        user.setHistorico(search(db, query));
    }

    private static ArrayList<MateriaCursada> search(SQLiteDatabase db, String query){

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<MateriaCursada> materias = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                MateriaCursada materia = new MateriaCursada();

                materia.setCodigo(cursor.getInt(1));
                materia.setNome(cursor.getString(2));
                materia.setCreditos(cursor.getInt(6));
                materia.setPeriodoCursado(cursor.getInt(5));
                materia.setMencao(cursor.getString(3));
                materia.setObrigatoria(cursor.getInt(4) == 1);
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
        String update = "UPDATE " + TABLE_HIST + " SET " +
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
        String deletar = "DELETE FROM " + TABLE_HIST + " WHERE " + KEY_MATERIA + " = '"
                + materia.getNome() + "'";

        db.execSQL(deletar);

    }

}
