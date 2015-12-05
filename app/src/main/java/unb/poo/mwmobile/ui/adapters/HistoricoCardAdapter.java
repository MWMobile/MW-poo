package unb.poo.mwmobile.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.MateriaCursada;

/**
 * Created by sousa on 04/12/2015.
 */
//Adapter do CardView, classe para inserir os dados do historico na card view.
public class HistoricoCardAdapter extends RecyclerView.Adapter<HistoricoCardAdapter.CardViewHolder> {

    private String TAG = "HistoricoCardAdapter";

    private Context context;
    private ArrayList<MateriaCursada> hist;

    private int lastPos = -1;

    public HistoricoCardAdapter(ArrayList<MateriaCursada> historico, Context c) {
//        super();
        this.hist = historico;
        this.context = c;
    }

    //Contador de quantos itens serão criados. Esta funcao retorna o tamanho
    //da lista de historico para saber quantas cards view serão necessárias.
    @Override
    public int getItemCount() {
        return hist.size();
    }

    //Funcao onde sera adicionado as informacoes na card view. O holder corresponde
    //ao layout do recycler view, que contera o card view (vCode, vPeriodo, vTitle e vMencao)
    //O parametro de entrada i equivale ao valor retornado pelo getItemCount() acima, ou seja,
    //o numero de itens a ser, nesta funcao, preenchido. Como getItemCount corresponde ao tamanho
    //da lista de historico, i tambem corresponde ao tamanho da lista, sendo assim, nao ha
    //item a mais ou a menos
    @Override
    public void onBindViewHolder(CardViewHolder holder, int i) {
        String nome = hist.get(i).getNome();
        String mencao = hist.get(i).getMencao();

        int code = hist.get(i).getCodigo();
        int periodo = hist.get(i).getPeriodoCursado();
//        String prof = hist.get(i).getProfessor().getNome();

        holder.vTitle.setText(nome);
        holder.vMencao.setText(mencao);

        holder.vCode.setText(String.format("Código: %d", code));
        //holder.vProf.setText("Professor: " + prof);
        holder.vPeriodo.setText(String.format("Periodo: %d", periodo));

        setAnimation(holder.container, i);
    }

    //Funcao que adiciona o card view no recycler view gracas ao lyout inflater.
    //Parametro de entrada i equivale ao getItemCount, ou seja, numero de card view que
    //sera criado e colocado dentro do recycler view
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardlist, viewGroup, false);
        return new CardViewHolder(view);
    }

    //Classe de identificacao do card view. Nesta classe instaciara os TextViews
    //presentes no card view e que serao utilizado no onBindViewHolder para preenchimento.
    //vProf nao utilizado, pois nao foi informado os professores das materias do historico e
    //caso haja algum valor nulo no card view, a activity nao funciona. Adicionado, ao final
    //do codigo, um TODO
    public class CardViewHolder extends RecyclerView.ViewHolder {

        protected TextView vTitle;
        protected TextView vMencao;

        protected TextView vCode;
        protected TextView vProf;
        protected TextView vPeriodo;

        protected CardView container;

        public CardViewHolder(View v) {
            super(v);

            this.vTitle = (TextView) v.findViewById(R.id.title);
            this.vMencao = (TextView) v.findViewById(R.id.mencaoField);

            this.vCode = (TextView) v.findViewById(R.id.codeField);
            this.vProf = (TextView) v.findViewById(R.id.profField);
            this.vPeriodo = (TextView) v.findViewById(R.id.periodoField);

            this.container = (CardView) v.findViewById(R.id.cardView);
        }
    }

    private void setAnimation(View toAnimate, int position) {
        if(position >= lastPos + 1) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            toAnimate.startAnimation(animation);
            lastPos = position;
        }
    }
}