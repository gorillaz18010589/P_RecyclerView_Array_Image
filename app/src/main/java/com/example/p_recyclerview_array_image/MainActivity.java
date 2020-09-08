package com.example.p_recyclerview_array_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Object[][] imgs  ;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imgs = new Object[][]{{R.string.app_name}, {"R.drawable.ic_launcher_background"}};
//
//
//        for(int i=0; i<imgs.length; i++){
//            Log.v("hank","imgs" + imgs[i][i]);
//        }

        img = findViewById(R.id.img);
        DrawableUtil drawableUtil = new DrawableUtil(this);
        List<Drawable> imgs = drawableUtil.getDrawable("ic_");
        for(Drawable drawable : imgs){
            Bitmap bitmap = drawableUtil.drawableToBitmap(drawable);
            img.setImageBitmap(bitmap);
            Log.v("hank","integer:" );
        }
    }
}