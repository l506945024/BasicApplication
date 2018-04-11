package com.lq.baseapplication.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by bang on 2018/4/10.
 */

public enum ToastUtil {
    INSTANCE;
    Toast mToast;
    public void showToast(Context context,String text){
        if (mToast== null) {
            mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(text);
        }
        mToast.show();
    }

    public void showToast(Context context,@StringRes int textResId){
        if (mToast== null) {
            mToast = Toast.makeText(context,textResId,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(textResId);
        }
        mToast.show();
    }
}
