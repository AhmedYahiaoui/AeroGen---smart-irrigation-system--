package com.example.blackhummer.testslide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SerreFragment extends Fragment {

    private TextView valeur_hum,valeur_hum_2,valeur_hum_air,valeur_lum,valeur_tmp,valeur_co2;
    private LinearLayout linearLayout ;
    private RequestQueue mQueue ;
    private Button mNextBtn ;
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.serre, container, false);

        valeur_hum = (TextView)view.findViewById(R.id.valeur_hum);
        valeur_hum_2 = (TextView)view.findViewById(R.id.valeur_hum_2);
        valeur_hum_air= (TextView)view.findViewById(R.id.valeur_hum_air);
        valeur_lum= (TextView)view.findViewById(R.id.valeur_lum);
        valeur_tmp= (TextView)view.findViewById(R.id.valeur_tmp);
        valeur_co2= (TextView)view.findViewById(R.id.valeur_co2);
        linearLayout= (LinearLayout)view.findViewById(R.id.linearLayout);

        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        return view;
    }

    private void jsonParse()
    {


        String url = "http://172.20.10.8/irrigation/consulter.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("tbl_consultations");
                 for (int i = 0;i<jsonArray.length();i++)
                 {
                     JSONObject tbl_consultations = jsonArray.getJSONObject(i);
                     String Humidite_sol = tbl_consultations.getString("hum_SolZ1");
                     String Humidite_sol_2 = tbl_consultations.getString("hum_SolZ2");
                     String Humidite_air = tbl_consultations.getString("hum_Air");
                     String tank = tbl_consultations.getString("N_Eau");
                     String temperature = tbl_consultations.getString("temp");
                     String co2 = tbl_consultations.getString("co2");





                     valeur_hum.setText(null);
                     valeur_hum_2.setText(null);
                     valeur_hum_air.setText(null);
                     valeur_lum.setText(null);
                     valeur_tmp.setText(null);
                     valeur_co2.setText(null);

                     valeur_hum.append(Humidite_sol+" %");
                     valeur_hum_2.append(Humidite_sol_2+" %");
                     valeur_hum_air.append(Humidite_air+" %");
                     valeur_lum.append(tank+" %");
                     valeur_tmp.append(temperature+" °");
                     valeur_co2.append(co2+" %");
                 }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}