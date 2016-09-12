package com.zitech.snackbardemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Android Support Design 库 之 Snackbar使用及源码分析 - OPEN 开发经验库
 * http://www.open-open.com/lib/view/open1437460246974.html
 *
 * Android SnackBar使用方法-android程序员-微头条(wtoutiao.com)
 * http://www.wtoutiao.com/p/h6dlCN.html
 *1. make()方法的第一个参数的view,不能是有一个ScrollView.
 因为SnackBar的实现逻辑是往这个View去addView.而ScrollView我们知道,是只能有一个Child的.否则会Exception.
 2. 如果大家在想把Toast替换成SnackBar.需要注意的是,Toast和SnackBar的区别是,前者是悬浮在所有布局之上的包括键盘,而SnackBar是在View上直接addView的.
 所以SnackBar.show()的时候,要注意先把Keyboard.hide()了.不然,键盘就会遮住SnackBar.

 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CoordinatorLayout coordinatorLayout;
    DoubleClickExitHelper mDoubleClickExitHelper;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        mDoubleClickExitHelper=new DoubleClickExitHelper(this);
        mainLayout= (LinearLayout) findViewById(R.id.mainLayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this,TopSnackBarAct.class));
                break;
            case R.id.btn_5:
                startActivity(new Intent(MainActivity.this,ColoredSnackBarAct.class));
                break;
            case R.id.btn_2:
                Snackbar.make(mainLayout, "NormalSnackbar", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btn_3:
                Snackbar snackbar3=Snackbar.make(mainLayout, "BackgroundColor", Snackbar.LENGTH_SHORT);
                snackbar3.getView().setBackgroundColor(Color.RED);
                snackbar3.show();
                break;
            case R.id.btn_4:
                Snackbar snackbar4= Snackbar.make(mainLayout, "Action", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Perform anything for the action selected
                            }
                        });
                snackbar4  .show();
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        boolean flag=true;
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return mDoubleClickExitHelper.onKeyDown(keyCode,coordinatorLayout);
        }
        return flag;

    }
}
