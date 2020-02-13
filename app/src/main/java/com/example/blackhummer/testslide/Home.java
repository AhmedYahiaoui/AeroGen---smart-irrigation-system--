package com.example.blackhummer.testslide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class Home extends AppCompatActivity implements View.OnClickListener{


    private ResideMenu resideMenu;
    private Context mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemSerre;
    private ResideMenuItem itemControl;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemLogout;
    private ResideMenuItem itemPicture;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemTable;
    private ResideMenuItem itemLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new HomeFragment());

    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(Home.this);
        builder.setMessage("would you want to back to Login");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }



    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_profile,    "Home");
        itemSerre  = new ResideMenuItem(this, R.drawable.serre,  "GreenHouse");
        itemControl = new ResideMenuItem(this, R.drawable.ctrl, "Control");
        itemTable = new ResideMenuItem(this, R.drawable.database, "Parametre");

     //   itemProfile = new ResideMenuItem(this, R.drawable.user, "Profile");
     //   itemSettings = new ResideMenuItem(this, R.drawable.stat_n, "Statistique");
        itemPicture = new ResideMenuItem(this, R.drawable.cam, "Picture");
        itemLog = new ResideMenuItem(this, R.drawable.log, "Log file ");

        itemLogout = new ResideMenuItem(this, R.drawable.logout, "Logout");



        itemHome.setOnClickListener(this);
        itemSerre.setOnClickListener(this);
        itemControl.setOnClickListener(this);
        itemTable.setOnClickListener(this);

//        itemProfile.setOnClickListener(this);
 //       itemSettings.setOnClickListener(this);
        itemLogout.setOnClickListener(this);
        itemPicture.setOnClickListener(this);
        itemLog.setOnClickListener(this);


        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSerre, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemControl, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTable, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLog, ResideMenu.DIRECTION_LEFT);


       // resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_RIGHT);
       // resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemPicture, ResideMenu.DIRECTION_RIGHT);

        resideMenu.addMenuItem(itemLogout, ResideMenu.DIRECTION_RIGHT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }


    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new HomeFragment());
        }else if (view == itemSerre){
            changeFragment(new SerreFragment());
        }else if (view == itemControl){
            changeFragment(new ControlFragment());


        }
        /*
        else if (view == itemSettings){
            changeFragment(new StatFragment());
        }
        else if (view == itemProfile){
            changeFragment(new ProfileFragment());
        }

        */
        else if (view==itemLogout){
            finish();
        }
        else if (view==itemPicture){
            changeFragment(new PictureFragment());
        }
        else if (view==itemTable){
            changeFragment(new TableFragment());
        }
        else if (view==itemLog){
            changeFragment(new LogFragment());
        }
        resideMenu.closeMenu();
    }


    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {

        }

        @Override
        public void closeMenu() {

        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
    }


}
