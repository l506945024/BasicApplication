package com.lq.baseapplication.basic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.lq.baseapplication.R;
import com.lq.baseapplication.utils.ScreenUtil;
import com.lq.baseapplication.utils.ToastUtil;

public abstract class BasicActivity extends AppCompatActivity {
    CoordinatorLayout base_container;
    AppBarLayout layout_appBar;

    //是否使用默认的标题栏布局
    private boolean isUseBasicLayout = true;
    //是否使用沉侵式
    private boolean isUseFlow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityConfig();

        if (isUseBasicLayout) {
            setContentView(R.layout.activity_basic);
            base_container = findViewById(R.id.base_container);
            layout_appBar = findViewById(R.id.layout_appBar);
            if (isUseFlow) {

            }
            setContentLayout();
            initTitle();
        } else {
            setContentView(getContentView());
        }
        initView();
        initData();
    }

    public void initActivityConfig() {
//        this.isUseBasicLayout = isUseBasicLayout;
//        this.isUseFlow = isUseFlow;
    }

    /**
     * 将布局加入到主布局
     */
    private void setContentLayout() {
        FrameLayout containerLayout = findViewById(R.id.layout_container);
        View contentView = getLayoutInflater().inflate(getContentView(), null, false);
        FrameLayout.LayoutParams layoutParams =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        if (containerLayout.getChildCount() > 0) {
            containerLayout.removeAllViews();
        }
        containerLayout.addView(contentView, layoutParams);

    }


    private void initTitle() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }

    /**
     * 设置内容布局
     *
     * @return
     */
    @LayoutRes
    public abstract int getContentView();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 初始化页面数据
     */
    public abstract void initData();

    /**
     * 使用默认的系统布局
     * 将stateBar背景颜色设置为appbar的背景颜色
     */
    public void setStateBarColorAppBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setStateBarTranslucent();
            int statusBarHeight = ScreenUtil.getStatusBarHeight(this);
            //相同颜色的状态栏直接设置padding
            layout_appBar.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    /**
     * 使状态栏透明
     */
    public void setStateBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }


    public void showToast(String text) {
        ToastUtil.INSTANCE.showToast(this, text);
    }

    public void showToast(@StringRes int stringTextId) {
        ToastUtil.INSTANCE.showToast(this, stringTextId);
    }

    public void logD(String str) {
        Log.d(this.getClass().getName(), str);
    }

    /**
     * 显示键盘
     *
     * @param view
     */
    public void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromInputMethod(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 跳转页面
     *
     * @param cls
     */
    public void skipPage(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * 跳转页面（带参数）
     *
     * @param cls
     * @param bundle
     */
    public void skipPage(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 跳转页面（待返回值）
     *
     * @param cls
     * @param reqCode
     */
    public void skipPageForResult(Class<?> cls, int reqCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, reqCode);
    }

    /**
     * 跳转页面（带参数、待返回值）
     *
     * @param cls
     * @param bundle
     * @param reqCode
     */
    public void skipPageForResult(Class<?> cls, Bundle bundle, int reqCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, reqCode);
    }


}
