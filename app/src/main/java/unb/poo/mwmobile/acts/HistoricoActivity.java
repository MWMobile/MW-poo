package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.MateriaCursada;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.adapters.HistoricoCard;

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

        recList.setAdapter(new HistoricoCard(hist, this));
    }
}

// TODO: adicionar professor nas materias cursadas
