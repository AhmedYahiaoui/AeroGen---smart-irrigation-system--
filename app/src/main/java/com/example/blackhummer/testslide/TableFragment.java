package com.example.blackhummer.testslide;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.spark.submitbutton.SubmitButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TableFragment extends Fragment {
   // private View parentView;

    private EditText valeur_hum_max,valeur_hum_air_max,valeur_lum_max,valeur_tmp_max,valeur_co2_max;
    private LinearLayout linear ;
    private RequestQueue mQueue ,queue;
    String id = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.table, container, false);

        final SubmitButton updateBtn = (SubmitButton) view.findViewById(R.id.updateBtn);



        valeur_hum_max = (EditText) view.findViewById(R.id.valeur_hum);
        valeur_hum_air_max= (EditText)view.findViewById(R.id.valeur_hum_air);
        valeur_lum_max= (EditText)view.findViewById(R.id.valeur_lum);
        valeur_tmp_max= (EditText)view.findViewById(R.id.valeur_tmp);
        valeur_co2_max= (EditText)view.findViewById(R.id.valeur_co2);
        linear= (LinearLayout)view.findViewById(R.id.linearLayout1);


        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());  // this = context


        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });



        return view;
    }


    private void jsonParse()
    {
        String url = "http://172.20.10.8/irrigation/parametre.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("parametres");
                            for (int i = 0;i<jsonArray.length();i++)
                            {
                                JSONObject tbl_consultations = jsonArray.getJSONObject(i);
                                String Humidite_sol = tbl_consultations.getString("Humidite_sol_max");
                                String Humidite_air = tbl_consultations.getString("Humidite_air_max");
                                String lumiere = tbl_consultations.getString("lumiere_max");
                                String temperature = tbl_consultations.getString("temperature_max");
                                String co2 = tbl_consultations.getString("co2_max");

                                valeur_hum_max.setText(null);
                                valeur_hum_air_max.setText(null);
                                valeur_lum_max.setText(null);
                                valeur_tmp_max.setText(null);
                                valeur_co2_max.setText(null);

                                valeur_hum_max.append(Humidite_sol);
                                valeur_hum_air_max.append(Humidite_air);
                                valeur_lum_max.append(lumiere);
                                valeur_tmp_max.append(temperature);
                                valeur_co2_max.append(co2);
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




    private void update ()
    {
        String url = "http://172.20.10.8/irrigation/update.php";
        final String hum_sol = this.valeur_hum_max.getText().toString().trim();
        final String hum_air = this.valeur_hum_air_max.getText().toString().trim();
        final String lum = this.valeur_lum_max.getText().toString().trim();
        final String tmp = this.valeur_tmp_max.getText().toString().trim();
        final String co2 = this.valeur_co2_max.getText().toString().trim();
       // final String id = getId();

//        final ProgressDialog progressDialog = new ProgressDialog(getActivity().getApplicationContext());
 //       progressDialog.setMessage("Saving ... ");
 //       progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1"))
                            {
                                Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("Humidite_sol_max",hum_sol);
                params.put("Humidite_air_max",hum_air);
                params.put("lumiere_max",lum);
                params.put("temperature_max",tmp);
                params.put("co2_max",co2);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }


}
