package com.tttg.user.collagephotoeditor;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.splash.SplashConfig;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.tttg.user.collagephotoeditor.R.drawable.camera;


public class MainActivity extends AppCompatActivity {
    static Uri resultUri;
    ImageView btn1, btn2, btn3;
    Display display;
    int height;
    NativeExpressAdView mAdView;
    int width;
    Bitmap bitmap;
    public static final String TAG = "MainActivity";
    private static int Camera_Request = 2;
    private static int Result = 1;
    private static final int REQUEST_CODE_GALLERY = 1;
    private Uri imgUriPath;
    private static Uri imageUri;
    final static int CAMERA_CAPTURE = 2;
    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_WALLPAPER, Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.CAMERA};



    final private int REQUEST_CODE_CAPTURE = 2;
    InterstitialAd mInterstitialAd;
    public static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (count == 0) {
            //               Developer ID    App ID
            StartAppSDK.init(this,"168784222", " 208434640", false);
            StartAppAd.showSplash(this, savedInstanceState, new SplashConfig()
                    .setTheme(SplashConfig.Theme.OCEAN).setLogo(R.drawable.icon)
                    .setAppName("Multan Sultan Photo Editor : PSL Themes 2018"));
            count++;
        }
        setContentView(R.layout.activity_main);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        Bannernative();
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
        btn1 = (ImageView) findViewById(R.id.cam);
        btn2 = (ImageView) findViewById(R.id.gal);
        btn3 = (ImageView) findViewById(R.id.work);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, setImageUri());
                startActivityForResult(intent, CAMERA_CAPTURE);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, Grid_Rla.class);
                    startActivity(intent);
                }

                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        requestNewInterstitial();
                        Intent intent = new Intent(MainActivity.this, Grid_Rla.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
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
    private void Bannernative() {

        mAdView = (NativeExpressAdView) findViewById(R.id.adVieww);
        mAdView.loadAd(new AdRequest.Builder().build());
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

        AlertDialog.Builder ad1 = new AlertDialog.Builder(this);
        ad1.setMessage("Do you want to rate this app? ");
        ad1.setCancelable(false);
        ad1.setTitle("Rate This App");

        ad1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        ad1.setNeutralButton("Exit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                finish();
            }
        });

        ad1.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id="
                                + "com.utilititesapps.multan.sultan.profilephoto.editor.maker")));

            }
        });
        AlertDialog alert = ad1.create();
        alert.setIcon(R.drawable.icon);
        alert.show();
    }


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private File getOutputMediaFile(int type) {

        String location;

        if (isSdPresent()) {
            location = Environment.getExternalStorageDirectory().toString();
        } else {
            location = getFilesDir().toString();
        }

        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMMddHmmss");
        String dateNow = formatter.format(currentDate.getTime());
        File file = new File(location + "/" + dateNow + ".beard09.JPEG");

        return file;
    }

    public static boolean isSdPresent() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode != RESULT_OK) {

            Log.d("ImageLog", String.valueOf(data));
            return;
        }


        Log.d("ImageLog", String.valueOf(resultCode));
        Log.d("ImageLog", String.valueOf(requestCode));

        switch (requestCode) {
            case REQUEST_CODE_GALLERY:

                try {

                    Uri selectedImageUri = data.getData();
                    CropImage.activity(selectedImageUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);

                } catch (Exception e) {

                    Log.e(TAG, "Error while creating temp file", e);
                }


                break;


            case REQUEST_CODE_CAPTURE:
                Log.d("ImageLog", "Request Code Capture");
                try {

                    Uri selectedImageUri = getImageUri();

                    if (selectedImageUri == null) {

                        Toast.makeText(MainActivity.this, "Error in loading image after capturing please try again!",
                                Toast.LENGTH_LONG).show();
                    }

                    CropImage.activity(selectedImageUri)
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(this);

                } catch (Exception e) {

                    Log.e("ImageLog", "Error while creating temp file", e);
                }

                break;

            case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                Log.e("ImageLog", "Crop Image Activity Request Code");
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                resultUri = result.getUri();
                startActivity(intent);
                break;

            case CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE:
                Log.e("ImageLog", "Crop Image Activity Result Error Code");
                Toast.makeText(MainActivity.this, "Error in loading image by caputring please try again!", Toast.LENGTH_LONG).show();
                break;
            default:
                Log.d("ImageLog", "default");
                Toast.makeText(MainActivity.this, "Error in loading image  by capture please try again!", Toast.LENGTH_LONG).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Uri getImageUri() {

        return imgUriPath;
    }

    public Uri setImageUri() {
        // Store image in dcim
        File file = new File(Environment.getExternalStorageDirectory()
                , "temp_ima" +
                "ge.png");

        Uri imgUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".my.package.name.provider", file);
        this.imgUriPath = imgUri;
        return imgUri;
    }

}
