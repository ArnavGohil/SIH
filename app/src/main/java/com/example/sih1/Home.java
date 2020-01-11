package com.example.sih1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final CardView crd1 = findViewById(R.id.cardView1) , crd2 = findViewById(R.id.cardView2);
        final ProgressBar progressBar = findViewById(R.id.pb);
        crd1.setVisibility(View.INVISIBLE);
        crd2.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crd1.setVisibility(View.VISIBLE);
                crd1.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.slide));
            }
        },1100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                crd2.setVisibility(View.VISIBLE);
                crd2.startAnimation(AnimationUtils.loadAnimation(Home.this, R.anim.slide));
            }
        },1150);


    }
}
