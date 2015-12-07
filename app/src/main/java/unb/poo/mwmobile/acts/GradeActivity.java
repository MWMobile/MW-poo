package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonSemana;
import unb.poo.mwmobile.singleton.SingletonUser;

public class GradeActivity extends Activity {

    private static String TAG = "GradeActivity";

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        SingletonUser singletonUser = SingletonUser.getInstance();

        this.user = singletonUser.getUser();

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        recList.setLayoutManager(new LinearLayoutManager(this));
        recList.setHasFixedSize(true);

        recList.setAdapter(new CardAdapter());
    }

    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

        SingletonSemana singletonSemana;

        public CardAdapter() {
            super();
            singletonSemana = SingletonSemana.getInstance();
        }

        @Override
        public int getItemCount() {
//            return user.getMaterias().size();
//            System.out.println(singletonSemana.getDias().size());
            return singletonSemana.getDias().size();
        }

        @Override
        public void onBindViewHolder(CardViewHolder holder, int i) {
            holder.dia.setText(singletonSemana.getDias().get(i));
        }

        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_item_grade, viewGroup, false);

            return new CardViewHolder(view);
        }

        public class CardViewHolder extends RecyclerView.ViewHolder {

            protected TextView dia;

            public CardViewHolder(View v) {
                super(v);

                this.dia = (TextView) v.findViewById(R.id.dia);
            }
        }
    }
}
// TODO fazer sliding para as materias