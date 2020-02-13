package com.example.blackhummer.testslide;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] nDots ;

    private SlideAdapter slideAdapter ;


    private Button mNextBtn ;
    private Button mBqckBtn ;
    public int currentPage ;


    Context context ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.suivBtn);
        mBqckBtn = (Button) findViewById(R.id.prevBtn);
        slideAdapter = new SlideAdapter (this);

        mSlideViewPager.setAdapter(slideAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSlideViewPager.getCurrentItem() == nDots.length -1){
                    openLoginActivtiy();
                }else {
                    mSlideViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });


        mBqckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(currentPage -1 );
            }
        });


    }


    public void openLoginActivtiy(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);

    }

    public void addDotsIndicator(int position){


        nDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i =0 ; i<nDots.length ; i++) {
            nDots[i] = new TextView(this);
            nDots[i].setText(Html.fromHtml("&#8226;"));
            nDots[i].setTextSize(35);
            nDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(nDots[i]);
        }
        if (nDots.length>0){

            nDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }

    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i ;
            if (i== 0)
            {

                mNextBtn.setEnabled(true);
                mBqckBtn.setEnabled(false);
                mBqckBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBqckBtn.setText("");
            }

            else if (i == nDots.length -1)
            {

                mNextBtn.setEnabled(true);
                mBqckBtn.setEnabled(true);
                mBqckBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Finish >>");
                mBqckBtn.setText("Back");
            }

            else
            {

                mNextBtn.setEnabled(true);
                mBqckBtn.setEnabled(true);
                mBqckBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBqckBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
