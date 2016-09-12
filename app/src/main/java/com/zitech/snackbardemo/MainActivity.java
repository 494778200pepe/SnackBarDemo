package com.zitech.snackbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relative_layout_main;
    CoordinatorLayout coordinatorLayout;
    DoubleClickExitHelper mDoubleClickExitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppManager.getAppManager().addActivity(this);
        mDoubleClickExitHelper=new DoubleClickExitHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                startActivity(new Intent(MainActivity.this,TopSnackBarAct.class));
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
