package unb.poo.mwmobile.acts;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.StringTokenizer;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.animations.WelcomeAct;

public class WelcomeActivity extends Activity {

    AnimatorSet animateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SingletonUser singletonUser = SingletonUser.getINSTANCE();

        StringTokenizer st = new StringTokenizer(singletonUser.getUser().getNome());

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(st.nextToken());

        animateText = WelcomeAct.setScreen(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        animateText.start();
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
