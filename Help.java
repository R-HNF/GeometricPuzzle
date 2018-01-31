package com.rhyy;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class Help extends View
{
    public GeometricPuzzle geometricpuzzle=new GeometricPuzzle();//geometricpuzzle
    public boolean savePieace;
    public Help(Context context){
        super(context);
        setFocusable(true);
        setBackgroundColor(Color.WHITE);//背景を黒色に設定
    }
    protected void onDraw(Canvas canvas){
        //描画オブジェクトの生成
        Paint p=new Paint();
        p.setAntiAlias(true);
        //p.setTypeface(Typeface.createFromAsset(geometricpuzzle.getAssets(), "tomnr.ttf"));
        p.setColor(Color.RED);//色
        p.setTextSize(40);//40
        canvas.drawText("HELP", 200, 60, p);
        p.setTextSize(15);//15
        canvas.drawText("This is the geometric game", 150, 90, p);
        p.setColor(Color.BLACK);//黒色
        canvas.drawText("1,  Fieald have 24 lots.", 20, 110, p);
        canvas.drawText("    We put a block which are shown in right up position in the field.", 23, 130, p);
        canvas.drawText("2,  Select to touch a black sequare dot in each lot, ", 20, 160, p);
        canvas.drawText("    and press the set button.", 23, 180, p);
        canvas.drawText("3,  Show a score after you fill in the every lot.", 20, 200, p);
        canvas.drawText("4,  Get a high score if you are adjacent", 20, 220, p);
        canvas.drawText("    same color blocks as many as possible", 23, 240, p);
        canvas.drawText("5,  Reset button to start new game", 20, 270, p);
        canvas.drawText("6,  Pause button to pause the game", 20, 290, p);

    }//描画
}