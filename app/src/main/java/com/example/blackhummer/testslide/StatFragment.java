package com.example.blackhummer.testslide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

public class StatFragment extends Fragment {


    CardView hum_button ,hum_air;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stat, container, false);

        hum_button= (CardView)rootView.findViewById(R.id.hum_button);
        hum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), stat_humdite.class);
                startActivity(intent);

            }
        });


        hum_air= (CardView)rootView.findViewById(R.id.hum_air);
        hum_air.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), stat_humdity_air.class);
                startActivity(intent);

            }
        });




        return rootView ;
    }
}
