package com.tu.example.watermark;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.tu.example.R;

/**
 *  水印
 */
public class WatermarkActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_watermark);
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN) {
      findViewById(android.R.id.content).setBackground(background("水印"));
    }else{
      findViewById(android.R.id.content).setBackgroundDrawable(background("水印"));
    }
  }
  BitmapDrawable background(String watermark){
    Bitmap bitmap = Bitmap.createBitmap(240, 240, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    canvas.drawColor(Color.WHITE);
    Paint paint = new Paint();
    paint.setColor(Color.GRAY);
    paint.setAlpha(80);
    paint.setAntiAlias(true);
    paint.setTextAlign(Paint.Align.LEFT);
    paint.setTextSize(40);
    Path path = new Path();
    path.moveTo(30, 150);
    path.lineTo(300, 0);
    canvas.drawTextOnPath(watermark, path, 0, 30, paint);

    BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
    bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    bitmapDrawable.setDither(true);
    return  bitmapDrawable;
  }
}
