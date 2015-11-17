package unb.poo.mwmobile.acts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.StringTokenizer;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.singleton.SingletonUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SingletonUser singletonUser = SingletonUser.getINSTANCE();


        StringTokenizer st = new StringTokenizer(singletonUser.getUser().getNome());

        TextView textView = (TextView) findViewById(R.id.nome);
        textView.setText(st.nextToken());
    }

    @Override
    protected void onResume() {
        super.onResume();

//        try {
//            Thread.sleep(10000);
            Intent homeAct = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(homeAct);

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
