package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;

/**
 * Created by Raphael on 24/11/2015.
 */
public class HistoricoActivity extends Activity {

    Activity context;
    User user;
    ArrayList<MateriaCursada> hist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hist);

        context = this;

        SingletonUser singletonUser = SingletonUser.getINSTANCE();

        this.user = singletonUser.getUser();

        hist = user.getHistorico();

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setLayoutManager(new LinearLayoutManager(this));
        recList.setHasFixedSize(true);

        recList.setAdapter(new CardAdapter());
    }

    //Adapter do CardView, classe para inserir os dados do historico na card view.
    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

        public CardAdapter() {
            super();
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
            int code = hist.get(i).getCodigo();
            int periodo = hist.get(i).getPeriodoCursado();
            String nome = hist.get(i).getNome();
            String mencao = hist.get(i).getMencao();

            holder.vCode.setText(String.format("Código: %d", code));
            //holder.vProf.setText("Professor: " + prof);
            holder.vPeriodo.setText(String.format("Periodo: %d", periodo));
            holder.vTitle.setText("Materia: " + nome);
            holder.vMencao.setText(mencao);
        }

        //Funcao que adiciona o card view no recycler view gracas ao lyout inflater.
        //Parametro de entrada i equivale ao getItemCount, ou seja, numero de card view que
        //sera criado e colocado dentro do recycler view
        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.cardlist, viewGroup, false);

            return new CardViewHolder(view);
        }

        //Classe de identificacao do card view. Nesta classe instaciara os TextViews
        //presentes no card view e que serao utilizado no onBindViewHolder para preenchimento.
        //vProf nao utilizado, pois nao foi informado os professores das materias do historico e
        //caso haja algum valor nulo no card view, a activity nao funciona. Adicionado, ao final
        //do codigo, um TODO
        public class CardViewHolder extends RecyclerView.ViewHolder {

            protected TextView vCode;
            protected TextView vProf;
            protected TextView vPeriodo;
            protected TextView vTitle;
            protected TextView vMencao;

            public CardViewHolder(View v) {
                super(v);
                this.vCode = (TextView) v.findViewById(R.id.codeField);
                this.vProf = (TextView) v.findViewById(R.id.profField);
                this.vPeriodo = (TextView) v.findViewById(R.id.periodoField);
                this.vTitle = (TextView) v.findViewById(R.id.title);
                this.vMencao = (TextView) v.findViewById(R.id.mencaoField);
            }
        }
    }
}

// TODO: adicionar professor nas materias cursadas
