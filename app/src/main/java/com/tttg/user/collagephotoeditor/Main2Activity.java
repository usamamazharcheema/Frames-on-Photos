package com.tttg.user.collagephotoeditor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.ValueBar;
import com.tttg.user.collagephotoeditor.adapter.FrameAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener, ColorPicker.OnColorChangedListener {

    Drawable a1, b, fr, b2;
    Bitmap bitmap, bitmap2, bitmap3, bitmap4, src;
    ImageView image1, /*image3*/ sticker_arabman;
    //    private StyleImageView image2;
    SeekBar seekbarBlurRadius;
    ImageView btn2, btn5, btn6, btn9, btn10;
    Boolean visi = true, visifrm = true, visibrig = true, visicont = true, visisat = true, visifil = true, save = true, can = false;
    //    FrameLayout reset;
    LinearLayout layoutvis, layoutframe, layoutbright, layoutcont, layoutsat, layoutfil;
    RelativeLayout saveLayout;
    TextView textvie;
    private ListViewHorizontal frames;
    ColorPicker color_picker;
    ValueBar value_Bar;
    FrameLayout canvas_view, textview;


    private DraggableImageView mImageView;
    private int mWidth;
    private int mHeight;
    private HashMap<Integer, Integer> replaceMap = new HashMap<Integer, Integer>();

    int prog1;
    TextView seatxt_view;

    int counter = 0;
    InterstitialAd mInterstitialAd;
    int ctime;
    File file;
    File f = null;
    StickerImageView iv_sticker;
    int count = 0;

    private Integer[] framsArray = {R.drawable.f_1, R.drawable.f_2, R.drawable.f_3, R.drawable.f_4,
            R.drawable.f_5, R.drawable.f_6,R.drawable.f_7,R.drawable.f_8,R.drawable.f_9,R.drawable.f_10,
            R.drawable.f_11,R.drawable.f_12,R.drawable.f_13,R.drawable.f_14,R.drawable.f_15,R.drawable.f_1,};

    Uri uri;
    private FrameAdapter frameAdapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        canvas_view = (FrameLayout) findViewById(R.id.canvasView2);
        iv_sticker = new StickerImageView(Main2Activity.this);
        canvas_view.addView(iv_sticker);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3444255945927869/6075843758");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();

            }
        });
        Intent intent = getIntent();
        uri = MainActivity.resultUri;
//                intent.getParcelableExtra("imageUri");
        count = Select_Activity_independence.count;

        mImageView = (DraggableImageView) findViewById(R.id.canvasImage);
        image1 = (ImageView) findViewById(R.id.imageView1);
//        image2 = (StyleImageView) findViewById(R.id.sticker_arabman);
//        image3 = (ImageView) findViewById(R.id.fram);


        DisplayMetrics displayMatrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMatrics);
        mWidth = displayMatrics.widthPixels;
        mHeight = displayMatrics.heightPixels;
        mImageView.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.trans), this.mWidth, this.mHeight, true));

//        reset = (FrameLayout) findViewById(R.id.canvasView);

        frames = (ListViewHorizontal) findViewById(R.id.frameslist);
        seatxt_view = (TextView) findViewById(R.id.seatxt_arabman);
        textview = (FrameLayout) findViewById(R.id.frame_arabman);
//        image3.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//
//            public boolean onTouch(View v, MotionEvent event) {
//                if (can)
//                    iv_sticker.setControlItemsHidden(true);
//                return false;
//
//            }
//
//        });
//        image2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//
//            public boolean onTouch(View v, MotionEvent event) {
//                if (can)
//                    iv_sticker.setControlItemsHidden(true);
//                return false;
//
//            }
//
//        });
        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (can)
                    iv_sticker.setControlItemsHidden(true);
                return false;
            }
        });


//        btn2 = (ImageView) findViewById(R.id.frame);

        btn5 = (ImageView) findViewById(R.id.save1);
        btn6 = (ImageView) findViewById(R.id.share);

        btn9 = (ImageView) findViewById(R.id.text);
        btn10 = (ImageView) findViewById(R.id.stick);

        layoutframe = (LinearLayout) findViewById(R.id.listfram);
        saveLayout = (RelativeLayout) findViewById(R.id.framelayout);


//        btn2.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);

        image1.setVisibility(View.VISIBLE);
//        frames.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//                // TODO Auto-generated method stub
//
//
//                bitmap3 = BitmapFactory.decodeResource(getResources(), framsArray[arg2]);
//                fr = new BitmapDrawable(getResources(), bitmap3);
//                image3.setBackground(fr);
//
//            }
//
//        });
//        switch (count) {
//            case 1:
//                image3.setBackgroundResource(R.drawable.f_1);
//                break;
//            case 2:
//                image3.setBackgroundResource(R.drawable.f_2);
//                break;
//            case 3:
//                image3.setBackgroundResource(R.drawable.f_3);
//                break;
//            case 4:
//                image3.setBackgroundResource(R.drawable.f_4);
//
//                break;
//            case 5:
//                image3.setBackgroundResource(R.drawable.f_5);
//                break;
//            case 6:
//                image3.setBackgroundResource(R.drawable.f_6);
//                break;
//            case 7:
//                image3.setBackgroundResource(R.drawable.f_7);
//                break;
//            case 8:
//                image3.setBackgroundResource(R.drawable.f_8);
//                break;
//            case 9:
//                image3.setBackgroundResource(R.drawable.f_9);
//                break;
//            case 10:
//                image3.setBackgroundResource(R.drawable.f_10);
//                break;
//            case 11:
//                image3.setBackgroundResource(R.drawable.f_11);
//                break;
//            case 12:
//                image3.setBackgroundResource(R.drawable.f_12);
//                break;
//            case 13:
//                image3.setBackgroundResource(R.drawable.f_13);
//                break;
//            case 14:
//                image3.setBackgroundResource(R.drawable.f_14);
//                break;
//            case 15:
//                image3.setBackgroundResource(R.drawable.f_15);
//                break;
//            case 16:
//                image3.setBackgroundResource(R.drawable.f_16);
//                break;
//
//
//        }
        seatxt_view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                drag(event, v);
                return true;
            }
        });

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap2 = createBitmap_ScriptIntrinsicBlur(bitmap, 15.0f);
        a1 = new BitmapDrawable(getResources(), bitmap2);
        image1.setBackground(a1);

//        File f = new File(getRealPathFromURI(uri));
//        b = Drawable.createFromPath(f.getAbsolutePath());
        DraggableBitmap stamp1 = new DraggableBitmap(bitmap);
        mImageView.addOverlayBitmap(stamp1);
//        image2.setBackground(b);


    }

    public void drag(MotionEvent event, View v) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) v
                .getLayoutParams();

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                params.topMargin = (int) event.getRawY() - (v.getHeight() / 2);
                params.leftMargin = (int) event.getRawX() - (v.getWidth() / 2);
                v.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_UP: {
                params.topMargin = (int) event.getRawY() - (v.getHeight() / 2);
                params.leftMargin = (int) event.getRawX() - (v.getWidth() / 2);
                v.setLayoutParams(params);
                break;
            }
            case MotionEvent.ACTION_DOWN: {
                v.setLayoutParams(params);
                break;
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View view) {
        if (can)
            iv_sticker = new StickerImageView(Main2Activity.this);
        switch (view.getId()) {
//            case R.id.frame:
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                }
//                if (visifrm) {
//                    visi = true;
//                    visibrig = true;
//                    visisat = true;
//                    visicont = true;
//                    visifil = true;
//                    layoutframe.setVisibility(View.VISIBLE);
//                    frameAdapter = new FrameAdapter(Main2Activity.this, R.layout.frame_adapter, framsArray);
//                    frames.setAdapter(frameAdapter);
//                    visifrm = false;
//                } else {
//                    layoutframe.setVisibility(View.GONE);
//                    visifrm = true;
//                }
//
//                break;
            case R.id.text:
                layoutframe.setVisibility(View.GONE);
                textview.setVisibility(View.VISIBLE);
                final Dialog text_dialog = new Dialog(
                        Main2Activity.this);
                text_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                text_dialog.setContentView(R.layout.custom_arabman);
                text_dialog.setCanceledOnTouchOutside(false);

                ImageView addButton = (ImageView) text_dialog.findViewById(R.id.addtxt);
                ImageView style_arab_man = (ImageView) text_dialog.findViewById(R.id.style_arabman);
                ImageView size_arab_man = (ImageView) text_dialog.findViewById(R.id.size_arabman);
                ImageView color_arab_man = (ImageView) text_dialog.findViewById(R.id.color_arabman);
                ImageView apply_arab_man = (ImageView) text_dialog.findViewById(R.id.tapply_arabman);

                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditDialog();
                    }
                });

                style_arab_man.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        styleDialog();
                    }
                });

                apply_arab_man.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub

                        text_dialog.dismiss();


                    }
                });
                size_arab_man.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        DisplayFontDialog();
                    }
                });

                color_arab_man.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        FontColorDialog();
                    }
                });

                text_dialog.show();
                break;

            case R.id.stick:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                visifrm = true;
                visi = true;
                visibrig = true;
                visicont = true;
                visisat = true;
                layoutframe.setVisibility(View.GONE);
                canvas_view.setVisibility(View.VISIBLE);
                final Dialog sticker_dialog = new Dialog(Main2Activity.this);
                sticker_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                sticker_dialog.setContentView(R.layout.custom_sticker_arabman);
                sticker_dialog.setCanceledOnTouchOutside(true);
                if (can)
                    canvas_view.addView(iv_sticker);
                iv_sticker.setControlItemsHidden(true);
                final ImageView imgv1 = (ImageView) sticker_dialog.findViewById(R.id.stik1);
                final ImageView imgv2 = (ImageView) sticker_dialog.findViewById(R.id.stik2);
                final ImageView imgv3 = (ImageView) sticker_dialog.findViewById(R.id.stik3);
                final ImageView imgv4 = (ImageView) sticker_dialog.findViewById(R.id.stik4);
                final ImageView imgv5 = (ImageView) sticker_dialog.findViewById(R.id.stik5);
                final ImageView imgv6 = (ImageView) sticker_dialog.findViewById(R.id.stik6);
                final ImageView imgv7 = (ImageView) sticker_dialog.findViewById(R.id.stik7);
                final ImageView imgv8 = (ImageView) sticker_dialog.findViewById(R.id.stik8);
                final ImageView imgv9 = (ImageView) sticker_dialog.findViewById(R.id.stik9);
                final ImageView imgv10 = (ImageView) sticker_dialog.findViewById(R.id.stik10);
                final ImageView imgv11 = (ImageView) sticker_dialog.findViewById(R.id.stik11);
                final ImageView imgv12 = (ImageView) sticker_dialog.findViewById(R.id.stik12);
                final ImageView imgv13 = (ImageView) sticker_dialog.findViewById(R.id.stik13);
                final ImageView imgv14 = (ImageView) sticker_dialog.findViewById(R.id.stik14);
                final ImageView imgv15 = (ImageView) sticker_dialog.findViewById(R.id.stik15);
                final ImageView imgv16 = (ImageView) sticker_dialog.findViewById(R.id.stik16);
                final ImageView imgv17 = (ImageView) sticker_dialog.findViewById(R.id.stik17);
                final ImageView imgv18 = (ImageView) sticker_dialog.findViewById(R.id.stik18);
                final ImageView imgv19 = (ImageView) sticker_dialog.findViewById(R.id.stik19);
                final ImageView imgv20 = (ImageView) sticker_dialog.findViewById(R.id.stik20);

                final ImageView imgv61 = (ImageView) sticker_dialog.findViewById(R.id.stik61);
                final ImageView imgv62 = (ImageView) sticker_dialog.findViewById(R.id.stik62);
                final ImageView imgv63 = (ImageView) sticker_dialog.findViewById(R.id.stik63);
                final ImageView imgv64 = (ImageView) sticker_dialog.findViewById(R.id.stik64);
                final ImageView imgv65 = (ImageView) sticker_dialog.findViewById(R.id.stik65);
                final ImageView imgv66 = (ImageView) sticker_dialog.findViewById(R.id.stik66);
                final ImageView imgv67 = (ImageView) sticker_dialog.findViewById(R.id.stik67);
                final ImageView imgv68 = (ImageView) sticker_dialog.findViewById(R.id.stik68);
                final ImageView imgv69 = (ImageView) sticker_dialog.findViewById(R.id.stik69);
                final ImageView imgv70 = (ImageView) sticker_dialog.findViewById(R.id.stik70);
                final ImageView imgv71 = (ImageView) sticker_dialog.findViewById(R.id.stik71);
                final ImageView imgv72 = (ImageView) sticker_dialog.findViewById(R.id.stik72);

                final ImageView imgv73 = (ImageView) sticker_dialog.findViewById(R.id.stik73);
                final ImageView imgv74 = (ImageView) sticker_dialog.findViewById(R.id.stik74);
                final ImageView imgv75 = (ImageView) sticker_dialog.findViewById(R.id.stik75);
                final ImageView imgv76 = (ImageView) sticker_dialog.findViewById(R.id.stik76);
                final ImageView imgv77 = (ImageView) sticker_dialog.findViewById(R.id.stik77);
                final ImageView imgv78 = (ImageView) sticker_dialog.findViewById(R.id.stik78);
                final ImageView imgv79 = (ImageView) sticker_dialog.findViewById(R.id.stik79);
                final ImageView imgv80 = (ImageView) sticker_dialog.findViewById(R.id.stik80);
                final ImageView imgv81 = (ImageView) sticker_dialog.findViewById(R.id.stik81);
                final ImageView imgv82 = (ImageView) sticker_dialog.findViewById(R.id.stik82);
                final ImageView imgv83 = (ImageView) sticker_dialog.findViewById(R.id.stik83);
                final ImageView imgv84 = (ImageView) sticker_dialog.findViewById(R.id.stik84);

                can = false;


                imgv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_1);
                        sticker_dialog.dismiss();
                        //  iv_sticker.setControlItemsHidden(false);

                    }
                });


                imgv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_2);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }
                });
                imgv3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_3);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setImageResource(R.drawable.s_4);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_5);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_6);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_7);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_8);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_9);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.jai_hind);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.bharat);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_10);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_11);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_12);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_13);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_14);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_15);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv18.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_16);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_17);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv20.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.s_18);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv61.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker61);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });

                imgv62.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker62);
                        sticker_dialog.dismiss();
                        //  iv_sticker.setControlItemsHidden(false);
                    }
                });


                imgv63.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker63);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv64.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker64);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv65.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker65);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv66.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker66);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv67.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker67);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv68.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker68);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv69.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker69);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv70.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker70);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv71.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker71);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv72.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker72);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv73.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker73);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });

                imgv74.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker74);
                        sticker_dialog.dismiss();
                        //  iv_sticker.setControlItemsHidden(false);
                    }
                });


                imgv75.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker75);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv76.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker76);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv77.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker77);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv78.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker78);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);

                    }
                });
                imgv79.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker79);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv80.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker80);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv81.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker81);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv82.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker82);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv83.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker83);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                imgv84.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //StickerDialog();
                        can = true;
                        iv_sticker.setControlItemsHidden(false);
                        iv_sticker.setImageResource(R.drawable.sticker84);
                        sticker_dialog.dismiss();
                        // iv_sticker.setControlItemsHidden(false);


                    }

                });
                sticker_dialog.show();
                break;

            case R.id.save1:
                counter++;
                if (save) {

                    ctime = Calendar.getInstance().get(Calendar.MILLISECOND);
                    saveLayout.setDrawingCacheEnabled(true);
                    bitmap4 = saveLayout.getDrawingCache();

                    file = new File(
                            android.os.Environment.getExternalStorageDirectory(),
                            "Multan Sultan");
                    if (!file.exists()) {
                        file.mkdirs();

                    }
                    f = new File(file.getAbsolutePath() + file.separator + "frm"
                            + ctime + ".jpg");

                    FileOutputStream ostream = null;
                    try {
                        ostream = new FileOutputStream(f);
                        bitmap4.compress(Bitmap.CompressFormat.PNG, 10, ostream);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        ostream.flush();
                        ostream.close();
                        Toast.makeText(getApplicationContext(), "Image Saved to Multan Sultan Folder",
                                Toast.LENGTH_SHORT).show();
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();

                    }
                    save = false;
                } else
                    Toast.makeText(Main2Activity.this, "Image is already saved", Toast.LENGTH_SHORT).show();


                break;
            case R.id.share:
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                if (counter >= 1) {
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);

                    share.setType("image/*");

                    share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + f));

                    try {

                        startActivity(Intent.createChooser(share, "Share photo"));

                    } catch (Exception e) {

                    }
                } else {
                    Toast.makeText(Main2Activity.this, "Image not save", Toast.LENGTH_SHORT).show();
                }
//                }

                break;
            default:
                break;
        }

    }

    public void styleDialog() {

        final Dialog dialog = new Dialog(Main2Activity.this, R.style.CustomStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.style_dialogue_arabman);
        dialog.setCanceledOnTouchOutside(false);
        TextView style_text1 = (TextView) dialog.findViewById(R.id.style1_arabman);
        TextView style_text2 = (TextView) dialog.findViewById(R.id.style2_arabman);
        TextView style_text3 = (TextView) dialog.findViewById(R.id.style3_arabman);
        TextView style_text4 = (TextView) dialog.findViewById(R.id.style4_arabman);
        TextView style_text5 = (TextView) dialog.findViewById(R.id.style5_arabman);
        TextView style_text6 = (TextView) dialog.findViewById(R.id.style6_arabman);
        TextView style_text7 = (TextView) dialog.findViewById(R.id.style7_arabman);
        TextView style_text8 = (TextView) dialog.findViewById(R.id.style8_arabman);
        TextView style_text9 = (TextView) dialog.findViewById(R.id.style9_arabman);
        TextView style_text10 = (TextView) dialog.findViewById(R.id.style10_arabman);
        TextView style_text11 = (TextView) dialog.findViewById(R.id.style11_arabman);
        TextView style_text12 = (TextView) dialog.findViewById(R.id.style12_arabman);
        TextView style_text13 = (TextView) dialog.findViewById(R.id.style13_arabman);
        TextView style_text14 = (TextView) dialog.findViewById(R.id.style14_arabman);
        TextView style_text15 = (TextView) dialog.findViewById(R.id.style15_arabman);


        Typeface blockFonts1 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/wheatland-demo.otf");
        style_text1.setTypeface(blockFonts1);

        Typeface blockFonts2 = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Wedding Chardonnay.ttf");
        style_text2.setTypeface(blockFonts2);
        Typeface blockFonts3 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Autograf_PersonalUseOnly.ttf");
        style_text3.setTypeface(blockFonts3);
        Typeface blockFonts4 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Smoothie Shoppe.ttf");
        style_text4.setTypeface(blockFonts4);
        Typeface blockFonts5 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/seriously.ttf");
        style_text5.setTypeface(blockFonts5);
        Typeface blockFonts6 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/MorrisRomanBlack.ttf");
        style_text6.setTypeface(blockFonts6);
        Typeface blockFonts7 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Monthoers.ttf");
        style_text7.setTypeface(blockFonts7);
        // TODO Auto-generated method stub
        Typeface blockFonts8 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/James_Fajardo.ttf");
        style_text8.setTypeface(blockFonts8);
        Typeface blockFonts9 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Florence-Regular.ttf");
        style_text9.setTypeface(blockFonts9);
        Typeface blockFonts10 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Deadly Inked.ttf");
        style_text10.setTypeface(blockFonts10);
        Typeface blockFonts11 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/daniel.ttf");
        style_text11.setTypeface(blockFonts11);
        Typeface blockFonts12 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/carolingia.ttf");
        style_text12.setTypeface(blockFonts12);

        Typeface blockFonts13 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Blazed.ttf");
        style_text13.setTypeface(blockFonts13);
        Typeface blockFonts14 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Ardeco.ttf");
        style_text14.setTypeface(blockFonts14);

        Typeface blockFonts15 = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/Anothershabby_trial.ttf");
        style_text15.setTypeface(blockFonts15);

        style_text1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/wheatland-demo.otf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();

            }
        });
        style_text2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Wedding Chardonnay.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Autograf_PersonalUseOnly.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Smoothie Shoppe.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/seriously.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/MorrisRomanBlack.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Monthoers.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/James_Fajardo.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Florence-Regular.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Deadly Inked.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/daniel.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/carolingia.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Blazed.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Ardeco.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });
        style_text15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Typeface blockFonts = Typeface.createFromAsset(getApplicationContext().getAssets(),
                        "fonts/Anothershabby_trial.ttf");
                seatxt_view.setTypeface(blockFonts);
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void EditDialog() {

        final Dialog edit_dialog = new Dialog(Main2Activity.this, R.style.CustomStyle);
        edit_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        edit_dialog.setContentView(R.layout.edittxt_custom_arabman);
        edit_dialog.setCanceledOnTouchOutside(false);
        final EditText ed = (EditText) edit_dialog.findViewById(R.id.edit_arabman);

        ImageView ok = (ImageView) edit_dialog.findViewById(R.id.ok_arabman);
        ImageView cancel = (ImageView) edit_dialog.findViewById(R.id.cancel_arabman);
        ed.setText("Enter Some Text!");

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                edit_dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    String edname = ed.getText().toString();
                    seatxt_view.setText(edname);

                } catch (Exception e) {
                    // TODO: handle exception
                }
                edit_dialog.dismiss();
            }
        });

        edit_dialog.show();

    }

    private void DisplayFontDialog() {
        // TODO Auto-generated method stub
        final Dialog dialog = new Dialog(this, R.style.CustomStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fontsize_arabman);
        SeekBar font_seekbar = (SeekBar) dialog
                .findViewById(R.id.setting_font_seekbar);
        final TextView show_progress = (TextView) dialog
                .findViewById(R.id.Show_progress_view);
        final TextView sampleTextView = (TextView) dialog
                .findViewById(R.id.sampleText);

        font_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                show_progress.setText(Integer.toString(progress + 15));
                sampleTextView.setTextSize(progress + 15);
                prog1 = progress;
            }
        });

        Button ok = (Button) dialog.findViewById(R.id.bOk);

        Button cancel = (Button) dialog.findViewById(R.id.bCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatxt_view.setTextSize(prog1 + 15);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void FontColorDialog() {

        final Dialog font_color_dialog = new Dialog(Main2Activity.this, R.style.CustomStyle);
        font_color_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        font_color_dialog.setContentView(R.layout.dialog_fontcolor_arabman);
        font_color_dialog.setCanceledOnTouchOutside(false);
        Button ok = (Button) font_color_dialog.findViewById(R.id.bok_arabman);
        Button cancel = (Button) font_color_dialog.findViewById(R.id.bcancel_arabman);

        color_picker = (ColorPicker) font_color_dialog.findViewById(R.id.picker_arabman);
        value_Bar = (ValueBar) font_color_dialog.findViewById(R.id.valuebar_arabman);

        color_picker.addValueBar(value_Bar);
        color_picker.setOnColorChangedListener(this);

        color_picker.setOldCenterColor(color_picker.getColor());

        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                font_color_dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    seatxt_view.setTextColor(color_picker.getColor());

                    color_picker.setOldCenterColor(color_picker.getColor());
                } catch (Exception e) {
                    // TODO: handle exception
                }
                font_color_dialog.dismiss();
            }
        });

        font_color_dialog.show();

    }


    private void saveBitmap(Bitmap bmp, String fileName) {
        try {
            b2 = new BitmapDrawable(getResources(), bmp);
            mImageView.setBackground(b2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap createBitmap_ScriptIntrinsicBlur(Bitmap src, float r) {

        //Radius range (0 < r <= 25)
        if (r <= 0) {
            r = 0.1f;
        } else if (r > 25) {
            r = 25.0f;
        }

        Bitmap bitmap = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(this);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, src);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(r);
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();
        return bitmap;
    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(Main2Activity.this)
//                .setTitle("Want to save Image?")
//
//                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        if (save) {
//
//                            ctime = Calendar.getInstance().get(Calendar.MILLISECOND);
//                            saveLayout.setDrawingCacheEnabled(true);
//                            bitmap4 = saveLayout.getDrawingCache();
//
//                            file = new File(
//                                    android.os.Environment.getExternalStorageDirectory(),
//                                    "Multan Sultan");
//                            if (!file.exists()) {
//                                file.mkdirs();
//
//                            }
//                            f = new File(file.getAbsolutePath() + file.separator + "frm"
//                                    + ctime + ".jpg");
//
//                            FileOutputStream ostream = null;
//                            try {
//                                ostream = new FileOutputStream(f);
//                                bitmap4.compress(Bitmap.CompressFormat.PNG, 10, ostream);
//
//                            } catch (FileNotFoundException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                            try {
//                                ostream.flush();
//                                ostream.close();
//                                Toast.makeText(getApplicationContext(), "Image Saved to Photo Filter",
//                                        Toast.LENGTH_SHORT).show();
//                        if (mInterstitialAd.isLoaded()) {
//                            mInterstitialAd.show();
//                        }
//                            } catch (IOException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//
//                            }
//                            save = false;
//                        } else
//                            Toast.makeText(Main2Activity.this, "Image is already saved", Toast.LENGTH_SHORT).show();
//                        Main2Activity.super.onBackPressed();
//                    }
//                })
//                .setPositiveButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Main2Activity.super.onBackPressed();
//                    }
//                })
//                .show();
//    }

    @Override
    public void onColorChanged(int color) {

    }
}

