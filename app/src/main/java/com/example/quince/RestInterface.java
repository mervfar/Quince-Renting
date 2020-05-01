package com.example.quince;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;



public class RestInterface extends AsyncTask<Void,Void,Void> {
    private String BaseURL = "http://142.93.139.123:8080";
    private Context mContext;
    private JSONArray result;
    private String token;
    private JsonObjectRequest tokenRequest;
    private JsonArrayRequest jsonArrayPostRequest;
    private RequestQueue requestQueue;
    public RestInterface() {
    }



    private void setResult(JSONArray result) {
        this.result = result;
    }



    public JSONArray getResult() {
        return result;
    }



    public void setToken(String token) {
        this.token = token;
    }



    private void setHeader(String token){
        // TODO: 30.04.2020
    }
    private String getToken(){
        // TODO: 30.04.2020
        return null;
    }



    public void GetToken(String url,Context which){
        requestQueue = Volley.newRequestQueue(which);
        tokenRequest=new JsonObjectRequest(
                Request.Method.GET,
                this.BaseURL + url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            setToken(response.getString("access_token"));
                            System.out.println(response.getString("access_token"));
                            System.out.println(response);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error); // Error Ekrana basıldı
                System.out.println("#################################"); // Error Ekrana basıldı
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String credentials = "quince" + ":" + "1234";
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Basic " + base64EncodedCredentials);
                return headers;
            }
        };


    }



    public JSONArray PostRequest(String url,Context which){
        requestQueue = Volley.newRequestQueue(which);
        jsonArrayPostRequest=new JsonArrayRequest(
                Request.Method.POST,
                this.BaseURL + url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            setResult(response);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error); // Error Ekrana basıldı
            }
        });
        return result;
    }

    @Override
    protected void onPostExecute(Void aVoid) {



    }


    @Override
    protected Void doInBackground(Void... voids) {
        requestQueue.add(tokenRequest);



        return null;
    }
}
