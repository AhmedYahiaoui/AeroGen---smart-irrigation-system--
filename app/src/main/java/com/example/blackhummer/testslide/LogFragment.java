package com.example.blackhummer.testslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;


public class LogFragment extends Fragment {

    Button go ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log, container, false);

//        username = (TextView) view.findViewById(R.id.username);

        go = (Button) view.findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), Log_file.class);
                startActivity(intent);

            }
        });

        return view;

    }


    }
