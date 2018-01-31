package com.rhyy;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
public class HelpJ extends View
{

    public GeometricPuzzle geometricpuzzle=new GeometricPuzzle();//geometricpuzzle
    public boolean savePieace;
    public HelpJ(Context context){
        super(context);
        setFocusable(true);
        setBackgroundColor(Color.WHITE);//背景を黒色に設定
    }//描画
    protected void onDraw(Canvas canvas){
        //描画オブジェクトの生成
        Paint p=new Paint();
        p.setAntiAlias(true);
        //p.setTypeface(Typeface.createFromAsset(geometricpuzzle.getAssets(), "tomnr.ttf"));
        p.setColor(Color.RED);//色
        p.setTextSize(40);//40
        canvas.drawText("ヘルプ", 180, 60, p);
        p.setTextSize(13);//15
        canvas.drawText("幾何学模様を楽しむゲームです。", 150, 85, p);
        p.setColor(Color.BLACK);//黒色
        canvas.drawText("1,  フィールドは２４マスあります。", 20, 110, p);
        canvas.drawText("    そこに右上に表示されているピースを置いていきます。", 23, 130, p);
        canvas.drawText("2,  １マス、１マスの中にある黒く四角いポイントをタッチで選択し、", 20, 160, p);
        canvas.drawText("     Setボタンを押すと右上のピースが選択されたマスに置かれます。", 23, 180, p);
        canvas.drawText("3,  ２４マスすべてを埋めるとスコアが表示されます。", 20, 200, p);
        canvas.drawText("4,  スコアは同じ色がとなり合う部分が多いほど高くなります。", 20, 220, p);
        canvas.drawText("5,  Restartボタンで最初からやり直せます。", 20, 250, p);
        canvas.drawText("6,  Pauseボタンで一時停止できます。", 20, 270, p);

    }
}