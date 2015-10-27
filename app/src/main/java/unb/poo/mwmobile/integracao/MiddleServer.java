package unb.poo.mwmobile.integracao;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.location.internal.LocationRequestUpdateData;

import unb.poo.mwmobile.acts.MainActivity;

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
