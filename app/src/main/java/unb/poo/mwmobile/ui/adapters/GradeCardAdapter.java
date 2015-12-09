package unb.poo.mwmobile.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Materia;

/**
 * Created by Raphael on 07/12/2015.
 */
public class GradeCardAdapter extends RecyclerView.Adapter<GradeCardAdapter.CardViewHolder>{
    private String TAG = "HistoricoCardAdapter";

    private Context context;
    private ArrayList<Materia> grade;

    private int lastPos = -1;

    public GradeCardAdapter(ArrayList<Materia> gradeH, Context c) {
//        super();
        this.grade = gradeH;
        this.context = c;
    }

    //Contador de quantos itens serão criados. Esta funcao retorna o tamanho
    //da lista de historico para saber quantas cards view serão necessárias.
    @Override
    public int getItemCount() {
        return grade.size();
    }

    //Funcao onde sera adicionado as informacoes na card view. O holder corresponde
    //ao layout do recycler view, que contera o card view (vCode, vPeriodo, vTitle e vMencao)
    //O parametro de entrada i equivale ao valor retornado pelo getItemCount() acima, ou seja,
    //o numero de itens a ser, nesta funcao, preenchido. Como getItemCount corresponde ao tamanho
    //da lista de historico, i tambem corresponde ao tamanho da lista, sendo assim, nao ha
    //item a mais ou a menos
    @Override
    public void onBindViewHolder(CardViewHolder holder, int i) {
        String nome = grade.get(i).getNome();
        int dia = grade.get(i).getDia();
        int hora = grade.get(i).getHora();

        holder.vHora.setText(String.valueOf(hora) + "h    :   " + nome);
    }

    //Funcao que adiciona o card view no recycler view gracas ao lyout inflater.
    //Parametro de entrada i equivale ao getItemCount, ou seja, numero de card view que
    //sera criado e colocado dentro do recycler view
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_item_grade, viewGroup, false);
        return new CardViewHolder(view);
    }

    //Classe de identificacao do card view. Nesta classe instaciara os TextViews
    //presentes no card view e que serao utilizado no onBindViewHolder para preenchimento.
    //vProf nao utilizado, pois nao foi informado os professores das materias do historico e
    //caso haja algum valor nulo no card view, a activity nao funciona. Adicionado, ao final
    //do codigo, um TODO
    public class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vHora;

        public CardViewHolder(View v) {
            super(v);

            this.vHora = (TextView) v.findViewById(R.id.horaField);
        }
    }
}
