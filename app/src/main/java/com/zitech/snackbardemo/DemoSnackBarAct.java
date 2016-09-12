package com.zitech.snackbardemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DemoSnackBarAct extends AppCompatActivity implements View.OnClickListener {

    private Button btn_util;
    private Button btn_simple;
    private Button btn_interactive;
    private Button btn_feedback;
    private Button btn_custom;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_demo_snackbar);
        initViews();
        initEvents();
    }


    private void initViews() {
        btn_simple = (Button) findViewById(R.id.btn_simple);
        btn_interactive = (Button) findViewById(R.id.btn_interactive);
        btn_feedback = (Button) findViewById(R.id.btn_feedback);
        btn_custom = (Button) findViewById(R.id.btn_custom);
        btn_util = (Button) findViewById(R.id.btn_util);
    }

    private void initEvents() {
        btn_simple.setOnClickListener(this);
        btn_interactive.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_custom.setOnClickListener(this);
        btn_util.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                startSnackbarSimple();
                break;
            case R.id.btn_interactive:
                startSnackbarInteractive();
                break;
            case R.id.btn_feedback:
                startSnackbarFeedback();
                break;
            case R.id.btn_custom:
                startSnackbarCustom();
                break;
            case R.id.btn_util:
                startSnackbarUtil();
                break;
        }
    }


    private void startSnackbarSimple() {
        Snackbar.make(findViewById(android.R.id.content), "像Toast一样", Snackbar.LENGTH_SHORT).show();
    }

    private void startSnackbarInteractive() {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "可以交互的Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("能交互吗", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoSnackBarAct.this, "真的能够交互", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();
    }

    private void startSnackbarFeedback() {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "反馈监听的Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("能交互吗", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoSnackBarAct.this, "真的能够交互", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar通过Action的点击事件退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar由于新的Snackbar显示而退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar通过调用dismiss()方法退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar右划退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar自然退出", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Toast.makeText(DemoSnackBarAct.this, "Snackbar显示", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();

    }

    private void startSnackbarCustom() {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "自定义Snackbar", Snackbar.LENGTH_LONG);
        snackbar.setAction("是否OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoSnackBarAct.this, "真的能够交互", Toast.LENGTH_SHORT).show();
            }
        });
        final Snackbar.SnackbarLayout snackbarView = (Snackbar.SnackbarLayout) snackbar.getView();
        if (snackbarView != null){
            //设置SnackBar背景与透明度
            snackbarView.setBackgroundResource(R.drawable.background);
            snackbarView.setAlpha((float) 0.4);

            //设置Action的字体颜色与大小
            final Button snackbar_action = (Button) snackbarView.findViewById(android.support.design.R.id.snackbar_action);
            snackbar_action.setTextColor(Color.RED);
            snackbar_action.setTextSize(convertSpToPixel(snackbarView.getContext(),10));

            //这是Text的文字颜色与大小
            final TextView snackbar_text = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
            snackbar_text.setTextColor(Color.RED);
            snackbar_text.setTextSize(convertSpToPixel(snackbarView.getContext(),10));
            //设置左侧icon
            Drawable drawable = ContextCompat.getDrawable(DemoSnackBarAct.this, R.mipmap.ic_core);
            if (drawable != null) {
                drawable = fitDrawable(getResources(), drawable, (int) convertDpToPixel(24, DemoSnackBarAct.this));
            } else {
                throw new IllegalArgumentException("resource_id is not a valid drawable!");
            }

            final Drawable[] compoundDrawables = snackbar_text.getCompoundDrawables();
            snackbar_text.setCompoundDrawables(drawable, compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        snackbar.show();
    }

    private void startSnackbarUtil() {
        final Snackbar snackbar = SnackbarUtil.longMake(findViewById(android.R.id.content), "测试工具类");
        snackbar.setDuration(5000);
        SnackbarUtil.setSnackbarBackgroudResource(snackbar, R.drawable.background);
        SnackbarUtil.setSnackbarAlpha(snackbar, (float) 0.4);

        snackbar.setAction("是否OK？", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DemoSnackBarAct.this, "Action事件触发！", Toast.LENGTH_SHORT).show();
            }
        });
        SnackbarUtil.setActionTextColor(snackbar, Color.RED);
        SnackbarUtil.setActionTextSize(snackbar, 8);

        SnackbarUtil.setTextColorAndSize(snackbar, Color.RED, 24);
        SnackbarUtil.setIconLeft(snackbar, R.mipmap.ic_core, 24);

        snackbar.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                switch (event) {
                    case Snackbar.Callback.DISMISS_EVENT_ACTION:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar通过Action的点击事件退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_CONSECUTIVE:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar由于新的Snackbar显示而退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_MANUAL:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar通过调用dismiss()方法退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_SWIPE:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar右划退出", Toast.LENGTH_SHORT).show();
                        break;
                    case Snackbar.Callback.DISMISS_EVENT_TIMEOUT:
                        Toast.makeText(DemoSnackBarAct.this, "Snackbar自然退出", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onShown(Snackbar snackbar) {
                super.onShown(snackbar);
                Toast.makeText(DemoSnackBarAct.this, "Snackbar显示", Toast.LENGTH_SHORT).show();
            }
        });
        snackbar.show();
    }


    /**
     * 方法描述：将drawable压缩为指定宽高的drawable
     *
     * @param resources
     * @param drawable  原始drawable
     * @param sizePx    指定的drawable压缩宽高
     * @return
     */
    private static Drawable fitDrawable(Resources resources, Drawable drawable, int sizePx) {
        if (drawable.getIntrinsicWidth() != sizePx || drawable.getIntrinsicHeight() != sizePx) {
            if (drawable instanceof BitmapDrawable) {
                drawable = new BitmapDrawable(resources, Bitmap.createScaledBitmap(getBitmap(drawable), sizePx, sizePx, true));
            }
        }
        drawable.setBounds(0, 0, sizePx, sizePx);

        return drawable;
    }

    /**
     * 方法描述：将Drawable转化为Bitmap
     *
     * @param drawable
     * @return
     */
    private static Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    /**
     * 方法描述：将VectorDrawable转化为Bitmap
     *
     * @param vectorDrawable
     * @return
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    /**
     * 方法描述：dp转化为px
     *
     * @param dpValue
     * @param context
     * @return
     */
    private static float convertDpToPixel(float dpValue, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int convertSpToPixel(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}

