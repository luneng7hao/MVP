package com.mvp.view.fragment.base;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mvp.app.MyApplication;
import com.mvp.view.R;
import com.mvp.view.activity.base.BaseActivity;
import com.mvp.view.layout.base.LoadLayout;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * date：     2017/3/3
 * description Fragment基类
 * 继承后该类后，不需要再绑定ButterKnife。当fragment可见时才会进行初始化工作(懒加载)
 * 实现setContentLayout来设置并返回布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {

    private static final String SAVED_STATE = "saved_state";

    protected BaseActivity mActivity;
    //加载布局，可用于设置各种加载状态，也是根布局视图
    private LoadLayout mLoadLayout;
    //根布局视图
    private View mContentView;
    //保存数据
    private Bundle mSavedState;
    //视图是否已经初始化完毕
    private boolean isViewReady;
    //fragment是否处于可见状态
    private boolean isFragmentVisible;
    //是否已经加载过
    protected boolean isLoaded;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mContentView == null) {
            try {
                mContentView = inflater.inflate(R.layout.fragment_base, container, false);
            } catch (Resources.NotFoundException e) {

            }

            if (mContentView == null) {
                throw new NullPointerException("根布局的id非法导致根布局为空,请检查后重试!");
            }

            View view = inflater.inflate(setContentLayout(), null);
            mLoadLayout = (LoadLayout) mContentView;
            mLoadLayout.addSuccessView(view);

            ButterKnife.bind(this, mContentView);

        }
        return mContentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化操作
         onFragmentVisiable();
    }

//    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isFragmentVisible = isVisibleToUser;
        //如果视图准备完毕且Fragment处于可见状态，则开始初始化操作
        if (isViewReady && isFragmentVisible) onFragmentVisiable();

    }

    //设置并返回布局ID
    protected abstract int setContentLayout();

    //做视图相关的初始化
    protected abstract void initView();

    //来做数据的初始化
    protected abstract void initData();

    //做事件监听的初始化
    protected abstract void initEvent();

    public void onFragmentVisiable() {
            initView();
            initData();
            initEvent();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 这个函数用于移除根视图
     * 因为已经有过父布局的View是不能再次添加到另一个新的父布局上面的
     */
//    @Override
//    public void onDestroyView() {
//        if (mContentView != null) {
//            ViewGroup parent = (ViewGroup) mContentView.getParent();
//            if (parent != null) {
//                parent.removeView(mContentView);
//            }
//        }
//        isViewReady = false;
//        //保存数据
//        saveStateToArguments();
//        super.onDestroyView();
//
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存数据
//        saveStateToArguments();
    }

    //获取加载布局，从而设置各种加载状态
    public LoadLayout getLoadLayout() {
        return mLoadLayout;
    }

    /**
     * 该函数可以Find一个被定义在XML中的根视图上的控件
     *
     * @param id 资源id
     * @return 这个id对应的控件
     */
    @CheckResult
    public View findViewById(@IdRes int id) {
        if (mContentView == null) {
            throw new NullPointerException("请检查你的根布局id合法性或view不为空后再调用此方法!");
        }
        return mContentView.findViewById(id);
    }


    /**
     * 可重写这个方法来恢复获取之前保存了的数据
     */
    protected void onRestoreState(@Nullable Bundle savedInstanceState) {

    }

    /**
     * 可重写这个方法来保存数据，将要保存的数据放入bundle中
     */
    protected void onSaveState(@Nullable Bundle outState) {

    }

    /**
     * 如果之前有保存数据，则恢复数据
     *
     * @return false表示第一次加载，true表示有保存的数据
     */
//    private boolean restoreStateFromArguments() {
//        Bundle b = getArguments();
//        if (b != null) {
//            mSavedState = b.getBundle(SAVED_STATE);
//            if (mSavedState != null) {
//                restoreState();
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void restoreState() {
//        if (mSavedState != null) {
//            onRestoreState(mSavedState);
//        }
//    }

    //保存数据
//    private void saveStateToArguments() {
//        if (getView() != null) {
//            mSavedState = saveState();
//        }
//        if (mSavedState != null) {
//            Bundle b = getArguments();
//            if (b != null) {
//                b.putBundle(SAVED_STATE, mSavedState);
//            }
//        }
//    }
//
//    private Bundle saveState() {
//        Bundle state = new Bundle();
//        onSaveState(state);
//        return state;
//    }

    @Override
    public int getResourceColor(@ColorRes int colorId, @Nullable Resources.Theme theme) {
        return isAdded() ? ResourcesCompat.getColor(getResources(), colorId, null) : 0;
    }

    @Override
    public String getResourceString(@StringRes int stringId) {
        return isAdded() ? getResources().getString(stringId) : null;
    }

    @Override
    public Drawable getResourceDrawable(@DrawableRes int id) {
        return isAdded() ? ResourcesCompat.getDrawable(getResources(), id, null) : null;
    }

    /**
     * 隐藏输入法
     */
    public void hideInput() {
        View view = getActivity().getWindow().peekDecorView();
//        CommonUtil.hideSoftInput(getContext(), view);
    }


}
