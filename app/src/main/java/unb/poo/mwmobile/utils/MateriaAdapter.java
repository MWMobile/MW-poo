package unb.poo.mwmobile.utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.models.User;

/**
 * Created by Alon-PC on 09/10/2015.
 */
public class MateriaAdapter extends BaseAdapter {

    private Context mContext;
    private final User user;

    public MateriaAdapter(Context c, User user) {
        mContext = c;
        this.user = user;
    }

    @Override
    public int getCount() {

        return user.getMaterias().size();
    }

    @Override
    public Object getItem(int position) {

        return user.getMaterias().get(position);
    }

    @Override
    public long getItemId(int position) {

        return  user.getMaterias().get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            
            grid = inflater.inflate(R.layout.grid_materias, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_materia);
            textView.setText(user.getMaterias().get(position).getNome());
            textView.setTextColor(Color.parseColor("#000000"));


        } else {
            grid = convertView;
        }

        return grid;
    }
}
