package com.example.blackhummer.testslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.special.ResideMenu.ResideMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProfileFragment extends Fragment {

    private TextView username, fisrt, last, email, phone;
    private LinearLayout linearLayout;
    private RequestQueue mQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);

        username = (TextView) view.findViewById(R.id.username);
        fisrt = (TextView) view.findViewById(R.id.fisrt);
        last = (TextView) view.findViewById(R.id.last);
        email = (TextView) view.findViewById(R.id.email);
        phone = (TextView) view.findViewById(R.id.phone);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);

        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  jsonParse();
            }
        });
        return view;
    }











}
