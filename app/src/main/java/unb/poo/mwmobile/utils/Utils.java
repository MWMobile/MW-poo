package unb.poo.mwmobile.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Materia;

/**
 * Created by sousa on 05/10/2015.
 */
public class Utils {

    public void gradePopulate(GridView gridView, final Context context) {
        Materia[] materias = new Materia[15];
        for(int i=0; i< 15; i++) {
            materias[i] = new Materia();
            materias[i].setNome("materia " + String.valueOf(i));
        }

        String[] nomeMaterias = new String[15];
        for (int i = 0; i < 15; i++) {
            nomeMaterias[i] = materias[i].getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, nomeMaterias);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(context, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });

    };

}
