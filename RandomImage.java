package com.rhyy;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class RandomImage extends View
{
    GeometricPuzzle geometricpuzzle=new GeometricPuzzle();
    Random rnd = new Random();//乱数
    //０〜３の乱数
    //imgnum=rnd.nextInt(4);//表示されるimgの値	
    public Bitmap imgbase;//画像を置くスペースの画像
    public Bitmap img0;//黒
    public Bitmap img1;//赤
    public Bitmap img2;//右上黒、逆赤
    public Bitmap img3;//右上赤、逆黒
    public int imgnum=0;
    public boolean savePieace=false;
    //コンストラクタ
    public RandomImage(Context context){
        super(context);
        setFocusable(true);
        setBackgroundColor(Color.alpha(0));//背景を黒色に設定
    }
    //描画
    protected void onDraw(Canvas canvas){
        //描画オブジェクトの生成
        Paint p=new Paint();
        p.setAntiAlias(true);
        p.setTextSize(16);//フォントを16に設定

        if(savePieace){
            savePieace=false;//表示されるimgの値
        }
        else{
            imgnum=rnd.nextInt(4);
        }

        //変数に画像を設定
        Resources res = this.getContext().getResources();
        imgbase = BitmapFactory.decodeResource(res, R.drawable.imgbase);
        img0 = BitmapFactory.decodeResource(res, R.drawable.img0);
        img1 = BitmapFactory.decodeResource(res, R.drawable.img1);
        img2 = BitmapFactory.decodeResource(res, R.drawable.img2);
        img3 = BitmapFactory.decodeResource(res, R.drawable.img3);

        //画像を右上に表示
        canvas.drawBitmap(imgbase,0,0,p);//0,0
        //imgtがimg0のとき
        if(imgnum==0){
            canvas.drawBitmap(img0,5,5,p);//5,5
        }
        //imgtがimg1のとき
        else if(imgnum==1){
            canvas.drawBitmap(img1,5,5,p);
        }
        //imgtがimg2のとき
        else if(imgnum==2){
            canvas.drawBitmap(img2,5,5,p);
        }
        //imgtがimg3のとき
        else if(imgnum==3){
            canvas.drawBitmap(img3,5,5,p);
        }
    }
}