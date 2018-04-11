package com.lq.baseapplication.basic;

/**
 * Created by bang on 2018/4/10.
 * 加入了懒加载的Fragment基类
 */

public abstract class BasicLazyFragment extends BasicFragment {
    //是否只懒加载一次（true 只调用一次lazyLoadData（） false 由不可见到可见都调用lazyLoadData（）
    public boolean onlyLazyLoadOnce = true;
    boolean isFistTimeToShow = true;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isPageInitialized) {
            loadData();
        }
    }

    /**
     * 懒加载
     */
    public abstract void lazyLoadData();

    private void loadData() {
        if (onlyLazyLoadOnce) {
            if (isFistTimeToShow) {
                lazyLoadData();
                isFistTimeToShow = false;
            }
        } else {
            lazyLoadData();
        }
    }
}
