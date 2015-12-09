package unb.poo.mwmobile.acts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.User;
import unb.poo.mwmobile.singleton.SingletonUser;

/**
 * Created by Raphael on 01/12/2015.
 */
public class QuadroResumoActivity extends Activity {

    User user;

    /**
     *  Funcao para area de quadro resumo. Nela esta contida algumas infos do usuario
     *  como IRA, quantidade de cada mencoes (SR, II, MI, MM, MS, SS) e os creditos
     *  (obtidos, a obter, exigidos e concedidos).
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadro_resumo);

        TextView alunoView = (TextView) findViewById(R.id.alunoField);
        TextView cursoView = (TextView) findViewById(R.id.cursoField);
        TextView iraView = (TextView) findViewById(R.id.iraField);
        TextView srField = (TextView) findViewById(R.id.srField);
        TextView iiField = (TextView) findViewById(R.id.iiField);
        TextView miField = (TextView) findViewById(R.id.miField);
        TextView mmField = (TextView) findViewById(R.id.mmField);
        TextView msField = (TextView) findViewById(R.id.msField);
        TextView ssField = (TextView) findViewById(R.id.ssField);
        TextView ccField = (TextView) findViewById(R.id.ccField);
        TextView obterView = (TextView) findViewById(R.id.obterField);
        TextView obtidoView = (TextView) findViewById(R.id.obtidoField);
        TextView exigView = (TextView) findViewById(R.id.exigField);

        SingletonUser singletonUser = SingletonUser.getInstance();

        this.user = singletonUser.getUser();

        alunoView.setText(String.format("Aluno:  %d  -   " + user.getNome(), user.getMatricula()));
        cursoView.setText("Curso:  " + user.getCurso());
        iraView.setText(String.format("IRA:  %.4f", user.getIRA()));

        int countSR = 0;
        int countII = 0;
        int countMI = 0;
        int countMM = 0;
        int countMS = 0;
        int countSS = 0;
        int credObt = 0;
        int credC = 0;
        int credExg = 274;

        for(int i = 0; i < user.getHistorico().size(); i++){
            String mencao = user.getHistorico().get(i).getMencao();
            credObt = credObt + user.getHistorico().get(i).getCreditos();

            switch(mencao){
                default:
                case "SR":
                    countSR++;
                    break;
                case "II":
                    countII++;
                    break;
                case "MI":
                    countMI++;
                    break;
                case "MM":
                    countMM++;
                    break;
                case "MS":
                    countMS++;
                    break;
                case "SS":
                    countSS++;
                    break;
            }
        }

        srField.setText(String.format("SR =     %d", countSR));
        iiField.setText(String.format("II =     %d", countII));
        miField.setText(String.format("MI =     %d", countMI));
        mmField.setText(String.format("MM =     %d", countMM));
        msField.setText(String.format("MS =     %d", countMS));
        ssField.setText(String.format("SS =     %d", countSS));
        ccField.setText(String.format("CC =     %d", credC));

        obtidoView.setText(String.format("- Obtidos:    %d", credObt));
        obterView.setText(String.format("- A obter:     %d", (credExg - credObt)));
        exigView.setText(String.format("- Exigido:  %d", credExg));
    }
}
