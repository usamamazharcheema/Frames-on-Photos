package com.tttg.user.collagephotoeditor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.IOException;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;

public class View_Activity_Rla extends Activity {
    ImageView imageview, more, like, wall;

    AlertDialog.Builder alertDialogBuilder;
    WallpaperManager wallpaperManager;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.view_layout);
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

        Intent i = getIntent();
        more = (ImageView) findViewById(R.id.delete);
        like = (ImageView) findViewById(R.id.share);
        wall = (ImageView) findViewById(R.id.wall);
        alertDialogBuilder = new AlertDialog.Builder(this);
        wallpaperManager = WallpaperManager.getInstance(this);

        final int position = i.getExtras().getInt("position");

        final String[] filepath = i.getStringArrayExtra("filepath");

        String[] filename = i.getStringArrayExtra("filename");

        imageview = (ImageView) findViewById(R.id.full_image_view);
        final Bitmap bmp = BitmapFactory.decodeFile(filepath[position]);
        wall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                alertDialogBuilder.setMessage("You wanted to set wallpaper!");
                alertDialogBuilder.setIcon(R.drawable.icon);
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                DisplayMetrics metrics = new DisplayMetrics();
                                getWindowManager().getDefaultDisplay()
                                        .getMetrics(metrics);

                                int height = metrics.heightPixels;
                                int width = metrics.widthPixels;

                                try {
                                    wallpaperManager.setBitmap(bmp);

                                    Toast.makeText(getApplicationContext(),
                                            "Wallpaper Set", Toast.LENGTH_SHORT)
                                            .show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        like.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM,
                        Uri.parse(filepath[position]));
                try {
                    startActivity(Intent.createChooser(share, "Share photo"));
                } catch (Exception e) {

                }

            }
        });

        imageview.setImageBitmap(bmp);
        more.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                File file = new File(filepath[position]);
                boolean deleted = file.delete();
                Intent i = new Intent(View_Activity_Rla.this, Grid_Rla.class);
                startActivity(i);
                finish();
            }
        });

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
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onBackPressed();
    }
}
