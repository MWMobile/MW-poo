package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.db.DBCore;
import unb.poo.mwmobile.db.DBMateria;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;

public class HomeActivity extends Activity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SingletonUser singletonUser = SingletonUser.getINSTANCE();

        this.user = singletonUser.getUser();

        RecyclerView recList = (RecyclerView) findViewById(R.id.cardList2);
        recList.setLayoutManager(new LinearLayoutManager(this));
        recList.setHasFixedSize(true);

        recList.setAdapter(new CardAdapter());
    }

    public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

        public CardAdapter() {
            super();
        }

        @Override
        public int getItemCount() {
            return user.getMaterias().size();
        }

        @Override
        public void onBindViewHolder(CardViewHolder holder, int i) {

        }

        @Override
        public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.card_item_grade, viewGroup, false);

            return new CardViewHolder(view);
        }

        public class CardViewHolder extends RecyclerView.ViewHolder {

            public CardViewHolder(View v) {
                super(v);
            }
        }
    }
}
