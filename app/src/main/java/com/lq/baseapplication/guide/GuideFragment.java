package com.lq.baseapplication.guide;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lq.baseapplication.R;
import com.lq.baseapplication.basic.BasicFragment;


/**
 * description: 引导页
 * <p>
 * author: mickban.
 * createtime: 2018/4/17 下午7:14.
 * update: 2018/4/17.
 * version:
 */
public class GuideFragment extends BasicFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IMAGE_ID = "picture";
    private static final String ARG_SHOW_BTN = "isShowBtn";

    ImageView iv_img;
    Button btn_start;

    boolean isShowBtn;
    int imageRes = R.mipmap.ic_launcher;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param imageRes  Parameter 1.
     * @param isShowBtn Parameter 2.
     * @return A new instance of fragment GuideFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuideFragment newInstance(@DrawableRes int imageRes, boolean isShowBtn) {
        GuideFragment fragment = new GuideFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_ID, imageRes);
        args.putBoolean(ARG_SHOW_BTN, isShowBtn);
        fragment.setArguments(args);
        return fragment;
    }


    public GuideFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        if (bundle != null) {
            isShowBtn = bundle.getBoolean(ARG_SHOW_BTN);
            imageRes = bundle.getInt(ARG_IMAGE_ID);
        }
    }

    @Override
    public int setContentView() {
        return R.layout.fragment_guide;
    }

    @Override
    public void initView() {
        iv_img = findView(R.id.iv_img);
        btn_start = findView(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStartBtnClick();
            }
        });
    }

    @Override
    public void initData() {
        if (isShowBtn) {
            btn_start.setVisibility(View.VISIBLE);
        }

        iv_img.setImageResource(imageRes);
    }


    private void onStartBtnClick() {

    }


}
