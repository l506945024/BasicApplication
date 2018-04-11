package com.lq.baseapplication.basic;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lq.baseapplication.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicFragment#} factory method to
 * create an instance of this fragment.
 *
 * Fragment基类
 */
public abstract class BasicFragment extends Fragment {

    private Context mContext;
    private Activity mActivity;

    boolean isPageInitialized;//是否初始化完成


    public BasicFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(setContentView(), null, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        isPageInitialized = true;
        initData();
    }

    /**
     * 设置布局
     *
     * @return 布局id
     */
    @LayoutRes
    public abstract int setContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    public abstract void initData();


    public void showToast(String text) {
        ToastUtil.INSTANCE.showToast(mContext, text);
    }

    public void showToast(@StringRes int stringTextId) {
        ToastUtil.INSTANCE.showToast(mContext, stringTextId);
    }

    public void logD(String str) {
        Log.d(getClass().getName(), str);
    }
}
