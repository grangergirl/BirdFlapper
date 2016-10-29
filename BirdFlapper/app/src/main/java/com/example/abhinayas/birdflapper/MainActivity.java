package com.example.abhinayas.birdflapper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        MyView myView=new MyView(this);
        setContentView(myView);
    }
    public class MyView extends View
    {
        Paint paint;
       int x,y;
        int moveByX,moveByY;
        int radius;
        int[][] leftright,topbottom;

        public MyView(Context context)
        {
            super(context);

            leftright=new int[10][2];
            topbottom=new int[10][2];
            paint = new Paint();
             x = 0;
             y = 200;
            moveByX=5;
            moveByY=5;
            int left=750,right=900,top=350,bottom=650;
            for(int i=0;i<10;i++)
            {
                leftright[i][0]=left;
                leftright[i][1]=right;

                if(i%2==0) {
                    topbottom[i][0] = 50;
                    topbottom[i][1] = top;
                    top+=160;
                    bottom-=100;

                }
                else
                {
                    topbottom[i][0] = bottom;
                    topbottom[i][1] = 1000;
                    bottom+=200;
                    top-=100;

                }
                int randomNumber=(int)Math.random()*4;
                if(randomNumber==0)
                {
                    left=right+50;
                    right=left+150;
                }
                else if(randomNumber==1)
                {
                    left=right+100;
                    right=left+50;
                }
                else if(randomNumber==2)
                {
                    left=right+10;
                    right=left+200;
                }
                else
                {
                    left=right+45;
                    right=left+156;
                }

            }

        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.CYAN);
            canvas.drawPaint(paint);
            radius = 30;
            paint.setColor(Color.parseColor("#FF4E3F3F"));
            canvas.drawRect(100,100,100,100,paint);
           // Paint myPaint = new Paint();
            paint.setColor(Color.rgb(33,66,99));
            paint.setStrokeWidth(10);
            for(int i=0;i<10;i++)
            {
               canvas.drawRect(leftright[i][0],topbottom[i][0],leftright[i][1],topbottom[i][1],paint);
            }
            for(int i=0;i<10;i++)
            {
                if(leftright[i][1]<0)
                {
                    leftright[i][0]=getWidth();
                    leftright[i][1]=getWidth()+100;
                }
                else
                {
                    leftright[i][0]-=5;
                    leftright[i][1]-=5;

                }

            }
           /* canvas.drawRect(750,50,900,450,myPaint);
            canvas.drawRect(920,700,1050,950,myPaint);
            canvas.drawRect(1000,50,1200,400,myPaint);
            canvas.drawRect(1280,550,1380,1000,myPaint);
            canvas.drawRect(1450,10,1600,500,myPaint);
            canvas.drawRect(1650,650,1800,1000,myPaint);*/
            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawCircle(x + moveByX, y + moveByY, radius, paint);
            int flag=0;
            for(int i=0;i<10;i++)
            {
                if(i%2==0) {
                    if (x + radius > leftright[i][0] && x + radius < leftright[i][1] && y - radius < topbottom[i][1])
                    {
                        flag=1;
                        break;
                    }
                }
                else
                {
                    if (x + radius > leftright[i][0] && x + radius < leftright[i][1] && y +radius >topbottom[i][0])
                    {
                        flag=1;
                        break;

                    }

                }
                if(y+radius>getHeight())
                {
                    flag=1;
                    break;
                }
            }

           if(flag==1)
                Toast.makeText(getApplicationContext(),"game over",Toast.LENGTH_SHORT).show();
            else if(x>getWidth())
                Toast.makeText(getApplicationContext(),"You won!",Toast.LENGTH_SHORT).show();

            else {
                if (x <= 0)
                    moveByX = getWidth() / 1500;

                if (y <= 0)
                    moveByY = getHeight() / 400;

                x += moveByX;
                y += moveByY;
                canvas.drawCircle(x, y, radius, paint);
                invalidate();
           }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                x+=moveByX*10;
                y-=moveByY*20;

                return false;
            }


            return false;
        }
    }
}

