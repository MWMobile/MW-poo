package unb.poo.mwmobile.integracao;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import de.greenrobot.event.EventBus;
import unb.poo.mwmobile.eventBus.MessageServerEB;

/**
 * Classe para fazer o request ao server
 * que faz o intermedio entre a aplicação e
 * o CPD.
 *
 * Created by scartezini on 27/10/2015.
 */
public class MiddleServer extends Service {

    private static final String URL = "http://104.131.63.41/echo";
    private static String TAG = "MiddleServer";

    private StringRequest request;
    private RequestQueue queue;
    private Context context;
    private String resposta;

    /**
     *  Construtor Tem que ser passado o contexto e quem
     *  vai 'ouvir' o listener
     *
     * @param context
     */
    public MiddleServer(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        //EventBus Register
        EventBus.getDefault().register(MiddleServer.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //EventBus unRegister
        EventBus.getDefault().unregister(MiddleServer.this);
    }

    /**
     *  Metodo que pega informações do servidor
     *
     * @param header
     *      paramentro para verificar o que esta sendo pedido
     * @param params
     *      paramntro que deve ser enviado para qualquer push do servidor
     */
    public void get(final Map<String,String> header, final Map<String, String > params ) {

        Log.d("INIT","Get");

        request = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TEST", response);

                        MessageServerEB message = new MessageServerEB();
                        //TODO pegar apenas o value com getValue, nao esta dando
                        message.setHeader(header);
                        message.setResponse(response);

                        //Postando Evento
                        EventBus.getDefault().post(message);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("ERRO:" , volleyError.getMessage());
                    }
                }){
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return header;
                }
        };

        request.setTag("tag");
        queue.add(request);
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
