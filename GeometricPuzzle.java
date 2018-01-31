package com.rhyy;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
public class GeometricPuzzle extends Activity implements OnClickListener,DialogInterface.OnClickListener{
    //public TickHandler tickHandler;//定期処理ハンドラ
    public TouchView touchView;//タッチビュー
    public RandomImage randomImage;//イメージを乱数で表示するクラス
    public Help help;
    public HelpJ helpJ;
    public Button button1;//Setボタン
    public Button button2;//Restartボタン
    public Button button3;//Helpボタン
    public Button buttonC;//Closeボタン
    public Button buttonCJ;//CloseJボタン
    public Button buttonExE;//英語の説明に変わるボタン
    public Button buttonExJ;//日本語の説明に変わるボタン
    public int Pieace;//ピースの値を確保
    public boolean sdialog;
    public AlertDialog _alertDialog;
    LinearLayout Layout;//グローバルライナーレイアウト
    LinearLayout Explanation;//グローバルヘルプレイアウト
    LinearLayout ExplanationJ;//グローバルヘルプJレイアウト
    LinearLayout Res;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//タイトルバーを表示させない
        touchView = new TouchView(this);//インスタンス化
        randomImage = new RandomImage(this);//インスタンス化
        help = new Help(this);
        helpJ = new HelpJ(this);

        //ライナーレイアウト（レイアウトの底）
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.BLACK);//黒色
        setContentView(layout);//ライナーレイアウトを表示

        //タッチビュー 
        LinearLayout.LayoutParams tv = new LinearLayout.LayoutParams(400,320);//横：400px 縦：320px
        tv.gravity=Gravity.CENTER_VERTICAL;//水平方向の中心に
        layout.addView(touchView, tv);//LinearLayoutにTouchViewを乗せる（自動的に左寄せ）

        //右上のイメージのレイアウト
        LinearLayout.LayoutParams img = new LinearLayout.LayoutParams(70,70);//横：70px 縦：70px
        img.topMargin=30;//上に30pxの余白を空ける
        img.leftMargin=4;//左に4pxの余白を空ける
        layout.addView(randomImage, img);//LinearLayoutにRandomImageを乗せる（タッチビューの右）


        //ボタン
        //ボタン１のレイアウト
        button1 = new Button(this);
        LinearLayout.LayoutParams b1 = new LinearLayout.LayoutParams(76,60);//横：76px 縦：60px
        b1.topMargin=120;//上に130pxの余白を空ける
        b1.leftMargin=-74;//左に-74pxの余白を空ける
        button1.setText("Set");//テキストを設定
        button1.setOnClickListener(this);//ボタン１が押されたときに起こる処理をOnClickListenerに設定
        layout.addView(button1, b1);//LinearLayoutにbutton1を乗せる（RandomImageの下）

        //ボタン２のレイアウト
        button2 = new Button(this);
        LinearLayout.LayoutParams b2 = new LinearLayout.LayoutParams(76,45);//横：76px 縦：45px
        b2.topMargin=190;//上に185pxの余白を空ける
        b2.leftMargin=-76;//←固定
        button2.setText("Reset");//テキストを設定
        button2.setOnClickListener(this);//button2が押されたときに起こる処理をOnClickListenerに設定
        layout.addView(button2, b2);//LinearLayoutにbutton2を乗せる（Setボタンの下）

        //ボタン3のレイアウト
        button3 = new Button(this);
        LinearLayout.LayoutParams b3 = new LinearLayout.LayoutParams(76,45);//横：76px 縦：45px
        b3.topMargin=235;
        b3.leftMargin=-76;//←固定
        button3.setText("Help");
        button3.setOnClickListener(this);
        layout.addView(button3, b3);//LinearLayoutにbutton3を乗せる（Restartボタンの下）


        //helpのレイアウト
        LinearLayout explanation = new LinearLayout(this);
        explanation.setBackgroundColor(Color.alpha(0));//透明
        setContentView(explanation);

        LinearLayout.LayoutParams h = new LinearLayout.LayoutParams(480,320);//横：480px 縦：320px
        h.gravity=Gravity.CENTER_VERTICAL;//水平方向の中心に
        explanation.addView(help, h);

        buttonC = new Button(this);
        LinearLayout.LayoutParams hb = new LinearLayout.LayoutParams(76,50);//横：76px 縦：50px
        hb.topMargin=230;//上に230pxの余白を空ける
        hb.leftMargin=-80;//←固定
        buttonC.setText("Close");//テキストを設定
        buttonC.setOnClickListener(this);//buttonCが押されたときに起こる処理をOnClickListenerに設定
        explanation.addView(buttonC, hb);//LinearLayoutにbuttonCを乗せる（Setボタンの下）

        buttonExJ = new Button(this);
        LinearLayout.LayoutParams exj = new LinearLayout.LayoutParams(76,50);//横：76px 縦：50px
        exj.topMargin=170;//上に230pxの余白を空ける
        exj.leftMargin=-76;//←固定
        buttonExJ.setText("日本語");//テキストを設定
        buttonExJ.setOnClickListener(this);//buttonCが押されたときに起こる処理をOnClickListenerに設定
        explanation.addView(buttonExJ, exj);//LinearLayoutにbuttonCを乗せる（Setボタンの下）

        //helpJのレイアウト

        LinearLayout explanationJ= new LinearLayout(this);
        explanationJ.setBackgroundColor(Color.alpha(0));//透明
        setContentView(explanationJ);

        LinearLayout.LayoutParams hJ = new LinearLayout.LayoutParams(480,320);//横：480px 縦：320px
        hJ.gravity=Gravity.CENTER_VERTICAL;//水平方向の中心に
        explanationJ.addView(helpJ, hJ);

        buttonCJ = new Button(this);
        LinearLayout.LayoutParams hbJ = new LinearLayout.LayoutParams(76,50);//横：76px 縦：50px
        hbJ.topMargin=230;//上に185pxの余白を空ける
        hbJ.leftMargin=-80;//←固定
        buttonCJ.setText("閉じる");//テキストを設定
        buttonCJ.setOnClickListener(this);//buttonCJが押されたときに起こる処理をOnClickListenerに設定
        explanationJ.addView(buttonCJ, hbJ);//LinearLayoutにbuttonCJを乗せる（Setボタンの下）

        buttonExE = new Button(this);
        LinearLayout.LayoutParams exe = new LinearLayout.LayoutParams(76,50);//横：76px 縦：50px
        exe.topMargin=170;//上に230pxの余白を空ける
        exe.leftMargin=-76;//←固定
        buttonExE.setText("English");//テキストを設定
        buttonExE.setOnClickListener(this);//buttonCが押されたときに起こる処理をOnClickListenerに設定
        explanationJ.addView(buttonExE, exe);//LinearLayoutにbuttonCを乗せる（Setボタンの下）

        Layout=layout;
        Explanation=explanation;
        ExplanationJ=explanationJ;
        setContentView(layout);

        touchView.pref = getSharedPreferences("pref",MODE_PRIVATE);
    }//アプリを初期化
    public void onClick(View v) {
        if (v == button1)//Setボタンが押されたとき
        {
            if(touchView.t){
                touchView.imgt=randomImage.imgnum;//TouchViweに置く画像の番号をRandomImageから取得する
                touchView.s=true;//TouchView上のマスのどこかが選択された
                randomImage.invalidate();
                Pieace=randomImage.imgnum;//表示されるimgの値の確保
                touchView.t=false;
                touchView.a=touchView.a-1;
                touchView.touchX=0;
                touchView.touchY=0;
                touchView.invalidate();//TouchViewを再描画
            }
        }
        else if (v == button2)//Resetボタンが押されたとき
        {
            onResume();
        }
        else if (v == button3)//Helpボタンが押されたとき
        {
            randomImage.savePieace=true;
            help.savePieace=true;
            Pieace=randomImage.imgnum;
            setContentView(Explanation);//helpレイアウトを表示
            help.invalidate();
        }
        else if (v == buttonC)//Closeボタンが押されたとき
        {
            randomImage.imgnum=Pieace;
            setContentView(Layout);//ライナーレイアウトを最前面に
        }
        else if (v == buttonCJ)//Closeボタンが押されたとき
        {
            randomImage.imgnum=Pieace;
            setContentView(Layout);//ライナーレイアウトを最前面に
        }
        else if (v == buttonExJ)//日本語ボタンが押されたとき
        {
            setContentView(ExplanationJ);//ライナーレイアウトを最前面に
        }
        else if (v == buttonExE)//Englishボタンが押されたとき
        {
            setContentView(Explanation);//ライナーレイアウトを最前面に
        }

    }
    public void onResume(){
        super.onResume();
        if(sdialog==true){
            _alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Really?")
                    .setCancelable(false)
                    .setMessage("Restart the game")
                    .setPositiveButton("OK",this)
                    .setNegativeButton("CANCEL",this).show();
        }
        sdialog=true;
    }//アプリの再開
    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        if(_alertDialog == dialog){
            switch(which){
                case DialogInterface.BUTTON_POSITIVE:
                    //変数の初期化
                    //タッチされた座標の初期化
                    touchView.touchX=0;
                    touchView.touchY=0;
                    touchView.touchAction=-999;
                    touchView.a=24;
                    touchView.s = false;
                    touchView.t = false;
                    touchView.d = false;
                    touchView.score=0;
                    for(int i=0;i<24;i++){
                        touchView.blackpoint[i]=false;
                        touchView.whitecell[i]=4;
                    }
                    //再描画
                    randomImage.invalidate();
                    touchView.invalidate();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    }
    public void onPause(){
        super.onPause();
    }//アプリの一時停止
    public class TickHandler extends Handler{
        //スリープ
        public void sleep(long delayMills)
        {
            removeMessages(0);
            sendMessageDelayed(obtainMessage(0),delayMills);
        }
    }//定期処理
}