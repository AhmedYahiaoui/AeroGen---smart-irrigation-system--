package com.example.blackhummer.testslide;


import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.graphics.Color;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.spark.submitbutton.SubmitButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ControlFragment extends Fragment {

    private View parentView;
    private ListView listView;


    Runnable buttonRunnable,buttonRunnable1,buttonRunnable2,buttonRunnable3;
    Runnable buttonRunnable4;
    Runnable buttonRunnable5;
    Runnable buttonRunnable6;
    Runnable buttonRunnable7;
    Handler buttonHandler = new Handler();


    String window_action = "on";
    String door_action = "on";
    String tank_action = "on";
    String spot_action = "on";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.calendar, container, false);

        final SubmitButton window = (SubmitButton) parentView.findViewById(R.id.window);
        final SubmitButton window2 = (SubmitButton) parentView.findViewById(R.id.window2);

        final SubmitButton door = (SubmitButton) parentView.findViewById(R.id.door);
        final SubmitButton door2 = (SubmitButton) parentView.findViewById(R.id.door2);

        final SubmitButton tank = (SubmitButton) parentView.findViewById(R.id.tank);
        final SubmitButton tank2 = (SubmitButton) parentView.findViewById(R.id.tank2);

        final SubmitButton spot = (SubmitButton) parentView.findViewById(R.id.spot);
        final SubmitButton spot2 = (SubmitButton) parentView.findViewById(R.id.spot2);


        window2.setVisibility (View.INVISIBLE);
        door2.setVisibility (View.INVISIBLE);
        tank2.setVisibility (View.INVISIBLE);
        spot2.setVisibility (View.INVISIBLE);



        buttonRunnable = new Runnable() {
            public void run() {
                window.setVisibility(View.GONE);
            }
        };
        buttonRunnable1 = new Runnable() {
            public void run() {
                window2.setVisibility(View.VISIBLE);
            }
        };

        buttonRunnable2 = new Runnable() {
            public void run() {
                door.setVisibility(View.GONE);
            }
        };
        buttonRunnable3 = new Runnable() {
            public void run() {
                door2.setVisibility(View.VISIBLE);
            }
        };

        buttonRunnable4 = new Runnable() {
            public void run() {
                tank.setVisibility(View.GONE);
            }
        };
        buttonRunnable5 = new Runnable() {
            public void run() {
                tank2.setVisibility(View.VISIBLE);
            }
        };

        buttonRunnable6= new Runnable() {
            public void run() {
                spot.setVisibility(View.GONE);
            }
        };
        buttonRunnable7 = new Runnable() {
            public void run() {
                spot2.setVisibility(View.VISIBLE);
            }
        };




        window.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonHandler.postDelayed(buttonRunnable, 3500);
                buttonHandler.postDelayed(buttonRunnable1, 3500);
                notificationcall();
                update_window_open();

            }
        });

        window2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                window.setVisibility(View.VISIBLE);
                window2.setVisibility(View.GONE);

                update_window_close();

            }
        });






        door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonHandler.postDelayed(buttonRunnable2, 3500);
                buttonHandler.postDelayed(buttonRunnable3, 3500);
                notificationcall();


                update_door_open();

            }
        });

        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                door.setVisibility(View.VISIBLE);
                door2.setVisibility(View.GONE);

                update_door_close();

            }
        });




        tank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonHandler.postDelayed(buttonRunnable4, 3500);
                buttonHandler.postDelayed(buttonRunnable5, 3500);
                notificationcall();

                update_tank_open();

            }
        });
        tank2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tank.setVisibility(View.VISIBLE);
                tank2.setVisibility(View.GONE);

                update_tank_close();
            }
        });





        //allume spot
        spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buttonHandler.postDelayed(buttonRunnable6, 3500);
                buttonHandler.postDelayed(buttonRunnable7, 3500);
                notificationcall();

                update_spot_open_();

            }
        });


        //eteindte spot
        spot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spot.setVisibility(View.VISIBLE);
                spot2.setVisibility(View.GONE);

                update_spot_close();
            }
        });




        return parentView;
    }


    public void notificationcall()
    {
        NotificationCompat.Builder notificationbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getContext().getApplicationContext())
     .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.user)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logo))
                .setContentTitle("Notif from Aerogen App ")
                .setContentText("There is somethings activated");
        NotificationManager notificationManagers = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagers.notify(1,notificationbuilder.build());
        getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void update_window_open ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_window_open.php";
        final String hum_sol = this.window_action.trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("window_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_door_open ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_door_open.php";
        final String hum_sol = this.door_action.trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("door_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_spot_open_ ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_spot_open.php";
        final String hum_sol = this.spot_action.trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("spot_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_tank_open ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_tank_open.php";
        final String hum_sol = this.tank_action.trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("tank_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }



    // close

    private void update_window_close ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_window_open.php";
        final String hum_sol = "off";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("window_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_door_close ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_door_open.php";
        final String hum_sol = "off";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("door_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_spot_close ()
    {
        String url = "http://172.20.10.8/irrigation/ctrl_spot_open.php";
        final String hum_sol = "off";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("spot_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void update_tank_close ()
    {

        String url = "http://172.20.10.8/irrigation/ctrl_tank_open.php";
        final String hum_sol = "off";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1"))
                            { Toast.makeText(getActivity(),"Success !",Toast.LENGTH_SHORT).show(); }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(),"error 1!"+e.toString(),Toast.LENGTH_SHORT).show(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"error 2! "+error.toString(),Toast.LENGTH_SHORT).show(); }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String>  params = new HashMap<>();
                params.put("tank_action",hum_sol);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }





}
