package com.tttg.user.collagephotoeditor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.tttg.user.collagephotoeditor.adapter.Adapter_Rla;

import java.io.File;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.InterstitialAd;

public class Grid_Rla extends Activity {
    private String[] FilePathStrings;
    private String[] FileNameStrings;
    private File[] listFile;
    GridView grid;
    Adapter_Rla adapter;
    int PERMISSION_ALL = 1;
    File file;
    InterstitialAd mInterstitialAd;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        String[] PERMISSION = {Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!hasPermissions(this, PERMISSION)) {
            ActivityCompat.requestPermissions(this, PERMISSION, PERMISSION_ALL);
        }
        setContentView(R.layout.grid_layout_rla);
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
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                    .show();
        } else {

            file = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "Multan Sultan");

            file.mkdirs();
        }

        if (file.isDirectory()) {
            listFile = file.listFiles();

            FilePathStrings = new String[listFile.length];

            FileNameStrings = new String[listFile.length];

            for (int i = 0; i < listFile.length; i++) {

                FilePathStrings[i] = listFile[i].getAbsolutePath();

                FileNameStrings[i] = listFile[i].getName();
            }
        }

        grid = (GridView) findViewById(R.id.gridview);

        adapter = new Adapter_Rla(this, FilePathStrings, FileNameStrings);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (FileNameStrings[position] == null) {


                } else {
                    Intent i = new Intent(Grid_Rla.this, View_Activity_Rla.class);

                    i.putExtra("filepath", FilePathStrings);

                    i.putExtra("filename", FileNameStrings);

                    i.putExtra("position", position);
                    startActivity(i);

                    finish();
                }

            }

        });

    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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