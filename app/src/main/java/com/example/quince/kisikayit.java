package com.example.quince;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class kisikayit extends AsyncTask<Void,Void,Void> {

    String username;
    String email;
    String password;
    /*String descriptionN;
    String countryName;
    String name;
    Double enlem,boylam;
    */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        String registerUrl="https://mervfar.com:8080/oauth/token?username=mervfar&password=12345&grant_type=password";
        JSONObject jsonObject=null;
        try {
            String json=JSONParser.getJSONFromUrl(registerUrl);
            System.out.println("gelen veri:" + json);

            try {
                jsonObject=new JSONObject(json);
            }catch (JSONException e){
                Log.e("JSONPARSER", "Error creating Json Object" +e.toString());}



            //En baştaki json objesinden list adlı array'ı çek
            JSONArray listArray=jsonObject.getJSONArray("list");
            //list'in ilk objesini çek
            JSONObject firstObj=listArray.getJSONObject(0);

            //Bu alanda username'i çek
            username = firstObj.getString("username");
            //ilk objenin içindeki objelerden main'i çek
            JSONObject main=firstObj.getJSONObject("main");

            //Bu alanda email'i çek
            email=main.getString("email");



        }catch (JSONException e){
            Log.e("json","doINbackgrond");

        }

        return null;
    }
}
