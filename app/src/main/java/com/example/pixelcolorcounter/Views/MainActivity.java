package com.example.pixelcolorcounter.Views;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.pixelcolorcounter.Presenters.MainActivityPresenter;
import com.example.pixelcolorcounter.Presenters.PixelColorCounterView;
import com.example.pixelcolorcounter.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PixelColorCounterView {
    ImageView imageView;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private MainActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainActivityPresenter(this);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        Button takePicture = findViewById(R.id.button);
        takePicture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button) {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");


            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);

            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 1, bos);
            byte[] bitmapdata = bos.toByteArray();

            Bitmap resized = Bitmap.createScaledBitmap(imageBitmap, imageBitmap.getHeight() / 4, imageBitmap.getWidth() / 4, true);
            test(resized);

            System.out.println(bitmapdata[0]);

            imageView.setImageBitmap(resized);
        }

    }

    public void test(Bitmap imageBitmap) {

        assert imageBitmap != null;
        @ColorInt
        int[] pixels = new int[imageBitmap.getWidth() * imageBitmap.getHeight()];

        imageBitmap.getPixels(pixels, 0, imageBitmap.getWidth(), 0, 0, imageBitmap.getWidth(), imageBitmap.getHeight());

        Arrays.sort(pixels);

//
//        int A = (pixels[0] >> 24) & 0xff; // or color >>> 24
//        int R = (pixels[0] >> 16) & 0xff;
//        int G = (pixels[0] >>  8) & 0xff;
//        int B = (pixels[0]      ) & 0xff;
//        int aa = (pixels[1] >> 24) & 0xff; // or color >>> 24
//        int rr = (pixels[1] >> 16) & 0xff;
//        int gg = (pixels[1] >>  8) & 0xff;
//        int bb = (pixels[1]      ) & 0xff;

//        System.out.println(A+R+G+B);


        for (int i = 0; i < pixels.length; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int A = (pixels[0] >> 24) & 0xff; // or color >>> 24
            int R = (pixels[0] >> 16) & 0xff;
            int G = (pixels[0] >> 8) & 0xff;
            int B = (pixels[0]) & 0xff;
//https://stackoverflow.com/questions/21037241/how-to-determine-a-point-is-inside-or-outside-a-cube/21037466#21037466










            double ok = Math.sqrt(Math.pow(16 - 8, 2) + Math.pow(16 - 8, 2) + Math.pow(16 - 8, 2));
            if (ok < 16)
                System.out.println("THIS IS THE CALC: " + ok);
            else
                System.out.println("FAILED FINDING ONE");

        }

    }
}