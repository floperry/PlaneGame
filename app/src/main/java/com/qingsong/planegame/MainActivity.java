package com.qingsong.planegame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private int speed = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉窗口标题
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //创建PlaneView组件
        final PlaneView planeView = new PlaneView(this);
        setContentView(planeView);
        //获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        //获取屏幕宽和高
        display.getMetrics(metrics);

        System.out.println("width = " + planeView.plane.getWidth() + ", height = " + planeView.plane.getHeight());

        //设置飞机的初始位置
        planeView.currentX = (metrics.widthPixels - planeView.plane.getWidth()) / 2;
        planeView.currentY = metrics.heightPixels - planeView.plane.getHeight();
        //为draw组件键盘事件绑定监听器
        planeView.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //获取由哪个按键触发的事件
                switch (event.getKeyCode()) {
                    //控制飞机下移
                    case KeyEvent.KEYCODE_S:
                        planeView.currentY += speed;
                        break;
                    //控制飞机上移
                    case KeyEvent.KEYCODE_W:
                        planeView.currentY -= speed;
                        break;
                    //控制飞机左移
                    case KeyEvent.KEYCODE_A:
                        planeView.currentX -= speed;
                        break;
                    //控制飞机右移
                    case KeyEvent.KEYCODE_D:
                        planeView.currentX += speed;
                        break;
                }
                //通知planeView组件重绘
                planeView.invalidate();
                return true;
            }
        });
    }
}
