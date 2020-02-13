package com.example.blackhummer.testslide;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyDialogFragment extends AppCompatDialogFragment {


    public MyDialogFragment()
    {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.custompopup, new LinearLayout(getActivity()), false);
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.custompopup,null);


        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TAG","you clicked ");
            }
        };


        return new AlertDialog.Builder(getActivity())
                .setTitle("changing")
                .setView(v)
                .setPositiveButton(android.R.string.ok,listener)
                .create();

/*
            TextView txtclose;
            Button btnFollow;
        Dialog builder = new Dialog(getActivity());


        builder.setContentView(R.layout.custompopup);
            txtclose =(TextView) builder.findViewById(R.id.txtclose);
            txtclose.setText("M");
            btnFollow = (Button) builder.findViewById(R.id.btnfollow);
            txtclose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dismiss();
                }
            });


        builder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
*/

    }


}
