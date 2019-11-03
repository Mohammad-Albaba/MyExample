package com.example.myexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView txt;
    private Button btn;
    private RequestQueue queue;
    boolean moh=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.txt);
        btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if( !moh){
//                    moh=true;
                   UploadJson();
             //  }

            }

        });
    }
    public void UploadJson(){
        queue = Volley.newRequestQueue(this);
        String Url="https://api.myjson.com/bins/kp9wz?fbclid=IwAR2y0zX_kJak7rw-29_wXp0uWjxjjiJV6_kcEJwU1CnPV41QS2KyXwNAyQk";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                JSONArray array = null;
                try {

                    array = response.getJSONArray("employees");
                    for (int i =0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String m =object.getString("firstname");
                        int mm=object.getInt("age");
                        String x=object.getString("mail");
                        txt.append(m+" ,"+mm+", "+x+" ,"+"\n\n");



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

queue.add(request);

    }
}
