package com.example.shreeram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class SetWallpaper extends AppCompatActivity {
    ImageView iv;
    Button setWP;
    ImageButton save, share;
    WallpaperManager wallpaperManager;
    Bitmap bitmap, bitmap1;
    DisplayMetrics displayMetrics;
    int width, height;
    BitmapDrawable bitmapDrawable;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        iv = findViewById(R.id.iv);
        setWP = findViewById(R.id.setWP);
        save = findViewById(R.id.save);
        share = findViewById(R.id.share);

        Intent intent = getIntent();
        int image = intent.getIntExtra("img", 0);
        iv.setImageResource(image);

        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        bitmapDrawable = (BitmapDrawable) iv.getDrawable();

        bitmap = bitmapDrawable.getBitmap();

        setWP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Wallpaper applied successfully!", Toast.LENGTH_SHORT).show();
                getScreenSize();

                setBitmapSize();

                wallpaperManager = WallpaperManager.getInstance(SetWallpaper.this);

                try {
                    wallpaperManager.setBitmap(bitmap1);
                    wallpaperManager.suggestDesiredDimensions(width, height);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SetWallpaper.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    saveToGallery();
                } else {
                    ActivityCompat.requestPermissions(SetWallpaper.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
                }
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
    }

    private void setBitmapSize() {
        bitmap1 = Bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    private void getScreenSize() {
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveToGallery();
            } else {
                Toast.makeText(SetWallpaper.this, "Please provide required permission.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void saveToGallery() {
        Uri images;
        ContentResolver contentResolver = getContentResolver();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            images = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        } else {
            images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, System.currentTimeMillis() + ".jpg");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "images/*");
        Uri uri = contentResolver.insert(images, contentValues);

        try {
            OutputStream outputStream = contentResolver.openOutputStream(Objects.requireNonNull(uri));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            Objects.requireNonNull(outputStream);

            Toast.makeText(SetWallpaper.this, "Image saved to gallery.",
                    Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(SetWallpaper.this, "Image not saved.",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void shareImage() {
        Uri uri = getImagesToShare(bitmap);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("image/*");
        startActivity(Intent.createChooser(intent, "Share image."));

    }

    private Uri getImagesToShare(Bitmap bitmap) {
        File folder = new File(getCacheDir(), "images");
        Uri uri = null;

        try {
            folder.mkdirs();
            File file = new File(folder, "shared_image.jpg");
            FileOutputStream fileOutputStream = null;

            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();

            uri = FileProvider.getUriForFile(this, "com.example.shreeram", file);
        } catch (Exception e) {
            Toast.makeText(SetWallpaper.this, e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        return uri;
    }
}