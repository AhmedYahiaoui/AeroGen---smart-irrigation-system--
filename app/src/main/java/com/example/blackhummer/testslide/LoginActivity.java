package com.example.blackhummer.testslide;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    ImageView bgapp, clover;
    LinearLayout textsplash, texthome, menus;
    Animation frombottom;
    RequestQueue queueVolley ;
    EditText username,password ;
    Button loginbtn;

   // String id ;
    private ConstraintLayout CLayout;
    ProgressDialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setHide(this, findViewById(R.id.a));

        CLayout = (ConstraintLayout ) findViewById(R.id.layout);

        username = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        loginbtn= (Button) findViewById(R.id.loginbtn);

        frombottom = AnimationUtils.loadAnimation(this, R.anim.frombottom);
       // bgapp = (ImageView) findViewById(R.id.bgapp);
        clover = (ImageView) findViewById(R.id.clover);
        textsplash = (LinearLayout) findViewById(R.id.textsplash);
        menus = (LinearLayout) findViewById(R.id.menus);
        bgapp.animate().translationY(-1900).setDuration(600).setStartDelay(300);
        clover.animate().alpha(0).setDuration(0).setStartDelay(0);
        textsplash.animate().translationY(140).alpha(0).setDuration(100).setStartDelay(100);
        getWindow().setBackgroundDrawableResource(R.drawable.back2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        menus.startAnimation(frombottom);

        queueVolley = Volley.newRequestQueue(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.43.181/irrigation/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("Failed"))
                        {
                            Toast.makeText(LoginActivity.this,"Login Failed" ,Toast.LENGTH_SHORT).show();
                            password.setText("");
                        }
                        else {
                           // Toast.makeText(LoginActivity.this,"Login sec" ,Toast.LENGTH_SHORT).show();



                            OpenActivity();
                            password.setText("");
                            username.setText("");
                         //   notificationcall();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("pppp","onError");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>map = new HashMap<>();
                        map.put("username",username.getText().toString());
                        map.put("password",password.getText().toString());
                      //  map.put("id",id);

                        return map ;
                    }
                };
                queueVolley.add(stringRequest);
            }
        });

        CLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });
    }

    public void OpenActivity() {
        Intent intent = new Intent(this, Home.class);
      //  intent.putExtra("EXTRA_SESSION_ID", id);
        startActivity(intent);
    }





    public void notificationcall()
    {
        NotificationCompat.Builder notificationbuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.user)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.bitcon))
                .setContentTitle("Notif from Reservoir ")
                .setContentText("Panne");
        NotificationManager notificationManagers = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManagers.notify(1,notificationbuilder.build());
        this.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
