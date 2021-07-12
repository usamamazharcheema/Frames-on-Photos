package com.tttg.user.collagephotoeditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Select_Activity_independence extends Activity implements
        OnClickListener {
    ImageView mid1_arabman, mid2_arabman, mid3_arabman, mid4_arabman, mid5_arabman,
            mid6_arabman, mid7_arabman, mid8_arabman, mid9_arabman, mid10_arabman, mid11_arabman,
            mid12_arabman, mid13_arabman, mid14_arabman, mid15_arabman, mid16_arabman;
    public static int count = 0;
    Intent j;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_fram);

        BannerAdmob();
        mInterstitialAd = new InterstitialAd(this);

        mInterstitialAd.setAdUnitId("ca-app-pub-3444255945927869/6075843758");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });


        j = new Intent(Select_Activity_independence.this,
                Main2Activity.class);

        mid1_arabman = (ImageView) findViewById(R.id.mid1_rla);
        mid2_arabman = (ImageView) findViewById(R.id.mid2_rla);
        mid3_arabman = (ImageView) findViewById(R.id.mid3_rla);
        mid4_arabman = (ImageView) findViewById(R.id.mid4_rla);
        mid5_arabman = (ImageView) findViewById(R.id.mid5_rla);
        mid6_arabman = (ImageView) findViewById(R.id.mid6_rla);
        mid7_arabman = (ImageView) findViewById(R.id.mid7_rla);
        mid8_arabman = (ImageView) findViewById(R.id.mid8_rla);
        mid9_arabman = (ImageView) findViewById(R.id.mid9_rla);
        mid10_arabman = (ImageView) findViewById(R.id.mid10_rla);
        mid11_arabman = (ImageView) findViewById(R.id.mid11_rla);
        mid12_arabman = (ImageView) findViewById(R.id.mid12_rla);
        mid13_arabman = (ImageView) findViewById(R.id.mid13_rla);
        mid14_arabman = (ImageView) findViewById(R.id.mid14_rla);
        mid15_arabman = (ImageView) findViewById(R.id.mid15_rla);
        mid16_arabman = (ImageView) findViewById(R.id.mid16_rla);


        mid1_arabman.setOnClickListener(this);
        mid2_arabman.setOnClickListener(this);
        mid3_arabman.setOnClickListener(this);
        mid4_arabman.setOnClickListener(this);
        mid5_arabman.setOnClickListener(this);
        mid6_arabman.setOnClickListener(this);
        mid7_arabman.setOnClickListener(this);
        mid8_arabman.setOnClickListener(this);
        mid9_arabman.setOnClickListener(this);
        mid10_arabman.setOnClickListener(this);
        mid11_arabman.setOnClickListener(this);
        mid12_arabman.setOnClickListener(this);
        mid13_arabman.setOnClickListener(this);
        mid14_arabman.setOnClickListener(this);
        mid15_arabman.setOnClickListener(this);
        mid16_arabman.setOnClickListener(this);

    }


    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView = (AdView) this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.mid1_rla:
                count = 1;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid2_rla:
                count = 2;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid3_rla:
                count = 3;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid4_rla:

                count = 4;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid5_rla:
                count = 5;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid6_rla:
                count = 6;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid7_rla:
                count = 7;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid8_rla:
                count = 8;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid9_rla:
                count = 9;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid10_rla:
                count = 10;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid11_rla:
                count = 11;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid12_rla:
                count = 12;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid13_rla:
                count = 13;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid14_rla:
                count = 14;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid15_rla:
                count = 15;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;
            case R.id.mid16_rla:
                count = 16;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                startActivity(j);

                break;


        }

    }
}
