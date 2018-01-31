package com.rhyy;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
//タッチイベント
public class TouchView extends View
{
    public int touchX;//タッチｘ座標
    public int touchY;//タッチｙ座標
    public int touchAction=-999;//タッチアクション
    public boolean s = false;//tがtrueでSetボタンが押されたかどうか
    public boolean t = false;//２４個の黒のポイントのうち　どこかが　選択されているか
    public boolean d = false;//スコアを表示するか
    public boolean[] blackpoint = new boolean[24];//２４個の黒のポイントのうち　どこが　選択されているか
    public int[] whitecell = new int[24];//２４個の白いマスのうちどこが埋まってるか
    public int a =24;//ピースが置かれるごとに１ずつ減っていく
    public int score=0;//スコア
    //画像
    public Bitmap t0;//黒
    public Bitmap t1;//赤
    public Bitmap t2;//右上黒、逆赤
    public Bitmap t3;//右上赤、逆黒
    public int imgt;//RandomImageの画像の値を入れる
    public SharedPreferences pref;
    //コンストラクタ
    public TouchView(Context context){
        super(context);
        setFocusable(true);
        setBackgroundColor(Color.WHITE);//TouchViewの背景を白色に設定
        setBackgroundResource(R.drawable.bg);//TouchViewの背景の上にbg.pngを乗せる
        for(int i=0;i<24;i++){whitecell[i]=4;}//画像の値が０〜３なので、４が何も入ってない状態
    }
    //描画
    protected void onDraw(Canvas canvas){
        //描画オブジェクトの生成
        Paint p=new Paint();
        p.setAntiAlias(true);//描画をきれいにする

        //黒いポイントの描画
        p.setColor(Color.argb(255, 0, 0, 0));//黒色
        for(int i=0;i<24;i++)
        {
            //一列目
            if(i<6) canvas.drawRect(new Rect(42+(60*i),62,58+(60*i),78),p);
                //二列目
            else if(6<=i&&i<12)canvas.drawRect(new Rect(42+(60*(i-6)),62+60,58+(60*(i-6)),78+60),p);
                //三列目
            else if(12<=i&&i<18)canvas.drawRect(new Rect(42+(60*(i-12)),62+(60*2),58+(60*(i-12)),78+(60*2)),p);
                //四列目
            else if(18<=i&&i<24)canvas.drawRect(new Rect(42+(60*(i-18)),62+(60*3),58+(60*(i-18)),78+(60*3)),p);
        }

        Bitmap strong;//選択されたますを強調するための画像の変数
        //変数に画像を設定
        Resources res = this.getContext().getResources();
        strong = BitmapFactory.decodeResource(res, R.drawable.strong);
        for(int n=0;n<24;n++){
            //２４個の黒いポイントのうち選択されているところを探す
            if(blackpoint[n]&&whitecell[n]==4){
                if(n<6){//一列目の場合
                    canvas.drawBitmap(strong,20+60*n,40,p);
                }
                else if(n<12){//二列目の場合
                    canvas.drawBitmap(strong,20+60*(n-6),40+60,p);
                }
                else if(n<18){//三列目の場合
                    canvas.drawBitmap(strong,20+60*(n-12),40+60*2,p);
                }
                else if(n<24){//四列目の場合
                    canvas.drawBitmap(strong,20+60*(n-18),40+60*3,p);
                }
            }
        }

        //変数に画像を設定
        Resources res1 = this.getContext().getResources();
        t0 = BitmapFactory.decodeResource(res1, R.drawable.img0);
        t1 = BitmapFactory.decodeResource(res1, R.drawable.img1);
        t2 = BitmapFactory.decodeResource(res1, R.drawable.img2);
        t3 = BitmapFactory.decodeResource(res1, R.drawable.img3);
        //黒のポイントが選択されていてSetボタンが押された場合
        if(s==true)
        {
            for(int n=0;n<24;n++){
                //２４個の黒いポイントのうち選択されているところを探す
                if(blackpoint[n]){
                    if(whitecell[n]==4){
                        whitecell[n]=imgt;//黒のポイントがtrueのところのマスにrandomImageに表示されている画像の値を入れる
                    }
                    else{}
                }
            }
            for(int i=0;i<24;i++){blackpoint[i]=false;}//黒のポイントの選択の初期化
            s=false;//Setボタンを初期化
        }

        for(int m=0;m<24;m++){//whitecellの配列に入っている画像の描画
            if(whitecell[m]==0){
                if(m<6)canvas.drawBitmap(t0,20+60*m,40,p);//一列目
                else if(m<12)canvas.drawBitmap(t0,20+60*(m-6),40+60,p);//二列目の場合
                else if(m<18)canvas.drawBitmap(t0,20+60*(m-12),40+60*2,p);//三列目の場合
                else if(m<24)canvas.drawBitmap(t0,20+60*(m-18),40+60*3,p);//四列目の場合
            }
            else if(whitecell[m]==1){
                if(m<6)canvas.drawBitmap(t1,20+60*m,40,p);//一列目
                else if(m<12)canvas.drawBitmap(t1,20+60*(m-6),40+60,p);//二列目
                else if(m<18)canvas.drawBitmap(t1,20+60*(m-12),40+60*2,p);//三列目
                else if(m<24)canvas.drawBitmap(t1,20+60*(m-18),40+60*3,p);//四列目
            }
            else if(whitecell[m]==2){
                if(m<6)canvas.drawBitmap(t2,20+60*m,40,p);//一列目
                else if(m<12)canvas.drawBitmap(t2,20+60*(m-6),40+60,p);//二列目
                else if(m<18)canvas.drawBitmap(t2,20+60*(m-12),40+60*2,p);//三列目
                else if(m<24)canvas.drawBitmap(t2,20+60*(m-18),40+60*3,p);//四列目
            }
            else if(whitecell[m]==3){
                if(m<6)canvas.drawBitmap(t3,20+60*m,40,p);//一列目
                else if(m<12)canvas.drawBitmap(t3,20+60*(m-6),40+60,p);//二列目
                else if(m<18)canvas.drawBitmap(t3,20+60*(m-12),40+60*2,p);//三列目
                else if(m<24)canvas.drawBitmap(t3,20+60*(m-18),40+60*3,p);//四列目
            }
        }

        //点数は同じ色が隣り合うところに加点される
        if(a==0){
            for(int b=0;b<24;b++){
                if(b<6&&b>=0){//一列目
                    if(whitecell[b]==0){//黒のとき
                        if(b!=5){//右端のとき以外
                            if(whitecell[b+1]==0)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==3)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==1){//赤のとき
                        if(b!=5){//右端のとき以外
                            if(whitecell[b+1]==1)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==2)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==2){//　赤\黒のとき
                        if(b!=5){//右端のとき以外
                            if(whitecell[b+1]==0)score+=3;//四角形と三角形
                            if(whitecell[b+1]==3)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=3;//四角形と三角形
                        if(whitecell[b+6]==3)score+=2;//三角形と三角形
                    }
                    if(whitecell[b]==3){// 黒\赤のとき
                        if(b!=5){//右端のとき以外
                            if(whitecell[b+1]==1)score+=3;//四角形と三角形
                            if(whitecell[b+1]==2)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=3;//四角形と三角形
                        if(whitecell[b+6]==2)score+=2;//三角形と三角形
                    }
                }
                if(b<12&&b>=6){//２列目
                    if(whitecell[b]==0){//黒のとき
                        if(b!=11){//右端のとき以外
                            if(whitecell[b+1]==0)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==3)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==1){//赤のとき
                        if(b!=11){//右端のとき以外
                            if(whitecell[b+1]==1)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==2)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==2){//　赤\黒のとき
                        if(b!=11){//右端のとき以外
                            if(whitecell[b+1]==0)score+=3;//四角形と三角形
                            if(whitecell[b+1]==3)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=3;//四角形と三角形
                        if(whitecell[b+6]==3)score+=2;//三角形と三角形
                    }
                    if(whitecell[b]==3){// 黒\赤のとき
                        if(b!=11){//右端のとき以外
                            if(whitecell[b+1]==1)score+=3;//四角形と三角形
                            if(whitecell[b+1]==2)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=3;//四角形と三角形
                        if(whitecell[b+6]==2)score+=2;//三角形と三角形
                    }
                }
                if(b<18&&b>=12){//３列目
                    if(whitecell[b]==0){//黒のとき
                        if(b!=17){//右端のとき以外
                            if(whitecell[b+1]==0)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==3)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==1){//赤のとき
                        if(b!=17){//右端のとき以外
                            if(whitecell[b+1]==1)score+=4;//四角形と四角形が隣り合う
                            if(whitecell[b+1]==2)score+=3;//四角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+6]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==2){//　赤\黒のとき
                        if(b!=17){//右端のとき以外
                            if(whitecell[b+1]==0)score+=3;//四角形と三角形
                            if(whitecell[b+1]==3)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==1)score+=3;//四角形と三角形
                        if(whitecell[b+6]==3)score+=2;//三角形と三角形
                    }
                    if(whitecell[b]==3){// 黒\赤のとき
                        if(b!=17){//右端のとき以外
                            if(whitecell[b+1]==1)score+=3;//四角形と三角形
                            if(whitecell[b+1]==2)score+=2;//三角形と三角形
                        }
                        if(whitecell[b+6]==0)score+=3;//四角形と三角形
                        if(whitecell[b+6]==2)score+=2;//三角形と三角形
                    }
                }
                if(b<23&&b>=18){//４列目
                    if(whitecell[b]==0){//黒のとき
                        if(whitecell[b+1]==0)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+1]==3)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==1){//赤のとき
                        if(whitecell[b+1]==1)score+=4;//四角形と四角形が隣り合う
                        if(whitecell[b+1]==2)score+=3;//四角形と三角形
                    }
                    if(whitecell[b]==2){//　赤\黒のとき
                        if(whitecell[b+1]==0)score+=3;//四角形と三角形
                        if(whitecell[b+1]==3)score+=2;//三角形と三角形
                    }
                    if(whitecell[b]==3){// 黒\赤のとき
                        if(whitecell[b+1]==1)score+=3;//四角形と三角形
                        if(whitecell[b+1]==2)score+=2;//三角形と三角形
                    }
                }
            }
            a=24;//a=0でなくす
            d=true;//スコアを表示できるようにする
        }
        p.setColor(Color.WHITE);//白色
        p.setTextSize(40);//40
        //点数は１００倍する
        //点数は最高が１５２００点だが、キリが悪いので−２００
        if(d){
            Editor e = pref.edit();
            int oldscore = pref.getInt("Score", 0);
            if(oldscore<score){
                canvas.drawText("NEW RECORD!!", 75, 120, p);
                e.putInt("Score", score);
                e.commit();
            }
            if(score*100-200<0){
                canvas.drawText("score="+0, 100, 205, p);//点数を表示
            }
            else{
                canvas.drawText("score="+(score*100-200), 100, 205, p);//点数を表示
            }

        }

    }
    //タッチイベントの処理
    public boolean onTouchEvent(MotionEvent event){
        //if(event.getAction()==MotionEvent.ACTION_UP){
        //タッチされた座標のx座標を取得
        touchX=(int)event.getX();
        //タッチされた座標のy座標を取得
        touchY=(int)event.getY();
        //}
        //座標が黒のポイントの中かを判定
        for(int k=0;k<24;k++){
            if(k<6){
                if(touchX>=25+(60*k)&&touchX<=75+(60*k)&&touchY>=40&&touchY<=100){//一列目の場合
                    //一度黒のポイントの選択を初期化
                    for(int m=0;m<24;m++){blackpoint[m]=false;}
                    if(whitecell[k]==4){//画像が入っていない時
                        blackpoint[k]=true;//bt[k]のところが選択された
                        t=true;//黒のポイントのうちどこかが選択された
                        break;
                    }
                    else{t=false;}
                }
            }
            else if(k>=6&&k<12){
                if(touchX>=25+(60*(k-6))&&touchX<=75+(60*(k-6))&&touchY>=40+60&&touchY<=100+60){//二列目の場合
                    //一度黒のポイントの選択を初期化
                    for(int m=0;m<24;m++){blackpoint[m]=false;}
                    if(whitecell[k]==4){//画像が入っていない時
                        blackpoint[k]=true;//bt[k]のところが選択された
                        t=true;//黒のポイントのうちどこかが選択された
                        break;
                    }
                    else{t=false;}
                }
            }
            else if(k>=12&&k<18){
                if(touchX>=25+(60*(k-12))&&touchX<=75+(60*(k-12))&&touchY>=40+60*2&&touchY<=100+60*2){//三列目の場合
                    //一度黒のポイントの選択を初期化
                    for(int m=0;m<24;m++){blackpoint[m]=false;}
                    if(whitecell[k]==4){//画像が入っていない時
                        blackpoint[k]=true;//bt[k]のところが選択された
                        t=true;//黒のポイントのうちどこかが選択された
                        break;
                    }
                    else{t=false;}
                }
            }
            else if(k>=18&&k<24){
                if(touchX>=25+(60*(k-18))&&touchX<=75+(60*(k-18))&&touchY>=40+60*3&&touchY<=100+60*3){//四列目の場合
                    //一度黒のポイントの選択を初期化
                    for(int m=0;m<24;m++){blackpoint[m]=false;}
                    if(whitecell[k]==4){//画像が入っていない時
                        blackpoint[k]=true;//bt[k]のところが選択された
                        t=true;//黒のポイントのうちどこかが選択された
                        break;
                    }
                    else{t=false;}
                }
            }
        }
        touchAction=event.getAction();
        //再描画
        invalidate();
        return true;
    }
}