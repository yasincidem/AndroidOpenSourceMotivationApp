package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;

public class WallpaperFullscreen extends AppCompatActivity {

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private String url;
    private int currentItem;
    ImageView imageView;
    LinearLayout buttons;
    boolean mVisible;
    static View decorView;
    Button setButton, shareButton;
    WallpaperManager wallpaperManager;
    BitmapDrawable bitmapDrawable;
    Bitmap bitmap,bitmap2;
    private int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_fullscreen);

        verifyStoragePermissions(this);

        imageView = findViewById(R.id.wallpaper_fullscreen_imageview);
        buttons = findViewById(R.id.buttons);
        setButton = findViewById(R.id.setButton);
        shareButton = findViewById(R.id.shareButton);
        decorView = getWindow().getDecorView();
        mVisible = true;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("WALLPAPER");
            currentItem = extras.getInt("CURRENTITEM");
        }
        Glide.with(this)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);


        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visiblity) {
                if ((visiblity & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0 ) {
                    buttons.setVisibility(View.VISIBLE);
                } else {
                    buttons.setVisibility(View.INVISIBLE);
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doIt();
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        wallpaperManager = WallpaperManager.getInstance(WallpaperFullscreen.this);
                        bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
                        bitmap = bitmapDrawable.getBitmap();
//                        bitmap2 = Bitmap.createScaledBitmap(bitmap, 720, 1280, false);
                        getScreenWidthHeight();
                        try {
                            wallpaperManager.suggestDesiredDimensions(WallpaperFullscreen.this.width, WallpaperFullscreen.this.height);
                            wallpaperManager.setBitmap(bitmap);
                            WallpaperFullscreen.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(WallpaperFullscreen.this, "Wallpaper is set", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        wallpaperManager = WallpaperManager.getInstance(WallpaperFullscreen.this);
                        bitmapDrawable = ((BitmapDrawable) imageView.getDrawable());
                        bitmap = bitmapDrawable.getBitmap();

                        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, String.valueOf(currentItem), null);
                        Uri bitmapUri = Uri.parse(bitmapPath);
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("image/png");
                        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                        startActivity(Intent.createChooser(intent, "Share"));
                    }
                }).start();
            }
        });
    }

    public void getScreenWidthHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        this.width = dm.widthPixels;
        this.height = dm.heightPixels;
    }

    @Override
    protected void onRestart() {
        Log.i("mVisibleOnRestart", String.valueOf(mVisible));
        super.onRestart();
        if (!mVisible){
            hideStatusBarOnStart();
            buttons.setVisibility(View.INVISIBLE);
        }

    }


    public void hideStatusBarOnStart(){
        mVisible = false;
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    public void hideStatusBar(){
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        mVisible = false;
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        buttons.setVisibility(View.INVISIBLE);
    }

    public void showStatusBar(){
        buttons.setVisibility(View.VISIBLE);

        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        mVisible = true;
    }

    public void doIt(){
        if (mVisible){
            hideStatusBar();
        }else{
            showStatusBar();
        }
    }

    private class AsWallpaper extends AsyncTask<Object, Void, Void> {

        private Context context;
        private int currentItem;

        public AsWallpaper(Context context, int currentItem) {
            this.context = context;
            this.currentItem = currentItem;
        }

        @Override
        protected Void doInBackground(Object... strings) {
            final WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .centerCrop()
                        .into(720, 1280)
                        .get();
                Log.i("üüqq", url);
                wallpaperManager.setBitmap(bitmap);


                WallpaperFullscreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Wallpaper is set", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
                WallpaperFullscreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Sorry, an error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
                WallpaperFullscreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Sorry, an error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                WallpaperFullscreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Sorry, an error occured", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            return null;
        }
    }


    private class ShareWallpaper extends AsyncTask<Object, Void, Void> {
        private Context context;
        private int currentItem;

        public ShareWallpaper(Context context, int currentItem) {
            this.context = context;
            this.currentItem = currentItem;
        }

        @Override
        protected Void doInBackground(Object... objects) {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .into(720, 1280)
                        .get();
                String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, String.valueOf(currentItem), null);
                Uri bitmapUri = Uri.parse(bitmapPath);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
                startActivity(Intent.createChooser(intent, "Share"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
// Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
// We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}

