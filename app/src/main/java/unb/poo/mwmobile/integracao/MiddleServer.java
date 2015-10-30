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

/**
 * Created by sousa on 27/10/2015.
 */
public class MiddleServer extends Service {

    private static final String URL = "http://104.131.63.41/echo";
    private static String TAG = "MiddleServer";

    private StringRequest request;
    private RequestQueue queue;
    private Context context;

    private String resposta;

    public MiddleServer(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String get(final Map<String,String> header, final Map<String, String > params ) {

        Log.d("INIT","Get");

        request = new StringRequest(Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TEST", response);
                        resposta = response;

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

        return resposta;

    }







    public interface PostCommentResponseListener {
        public void requestStarted();
        public void requestCompleted();
        public void requestEndedWithError(VolleyError error);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
