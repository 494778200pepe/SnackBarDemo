package com.zitech.snackbardemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.zitech.snackbardemo.coloredsnackbar.ColoredSnackbar;

/**
 * Created by pepe on 2016/9/12.
 */
public class ColoredSnackBarAct extends AppCompatActivity implements View.OnClickListener {
    CoordinatorLayout coordinatorLayout;
    DoubleClickExitHelper mDoubleClickExitHelper;
    LinearLayout coloredlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_colored_snackbar);
        AppManager.getAppManager().addActivity(this);
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
        coloredlayout = (LinearLayout) findViewById(R.id.coloredlayout);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                Snackbar snackbar1 = Snackbar.make(coloredlayout, "info", Snackbar.LENGTH_SHORT);
                ColoredSnackbar.info(snackbar1).show();
                break;
            case R.id.btn_2:
                Snackbar snackbar2 = Snackbar.make(coloredlayout, "warning", Snackbar.LENGTH_SHORT);
                ColoredSnackbar.warning(snackbar2).show();
                break;
            case R.id.btn_3:
                Snackbar snackbar3 = Snackbar.make(coloredlayout, "alert", Snackbar.LENGTH_SHORT);
                ColoredSnackbar.alert(snackbar3).show();
                break;
            case R.id.btn_4:
                Snackbar snackbar4 = Snackbar.make(coloredlayout, "confirm", Snackbar.LENGTH_SHORT);
                ColoredSnackbar.confirm(snackbar4).show();
                break;
            case R.id.btn_5:
                Snackbar snackbar5 = Snackbar.make(coloredlayout, "TextColor", Snackbar.LENGTH_SHORT);
                snackbar5=ColoredSnackbar.setSnackbarMessageTextColor(snackbar5, Color.GREEN);
                snackbar5.show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean flag = true;
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return mDoubleClickExitHelper.onKeyDown(keyCode, coloredlayout);
        }
        return flag;

    }
}
