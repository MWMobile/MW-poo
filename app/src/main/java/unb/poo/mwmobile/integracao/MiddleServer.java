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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sousa on 27/10/2015.
 */
public class MiddleServer extends Service {

    private StringRequest stringRequest;
    private RequestQueue queue;
    private String url;
    private Context context;

    private static String TAG = "MiddleServer";

    public MiddleServer(Context c) {
        context = c;
        url = "http://104.131.63.41/echo";
        queue = Volley.newRequestQueue(context);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void get() {

        // Request a string response from the provided URL.
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "That didn't work! " + error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        queue.start();
        queue.start();
    }

    public void getIRA(final int matricula){

        stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.d(TAG,"IRA" + s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d(TAG, "ERRO IRA " + volleyError);
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(TAG,"IRA");
                params.put("matricula",matricula+"");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","post");
                return params;
            }
        };

        queue.add(stringRequest);
        queue.start();
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
