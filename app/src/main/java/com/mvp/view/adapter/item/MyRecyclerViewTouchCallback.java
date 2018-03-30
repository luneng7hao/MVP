package com.mvp.view.adapter.item;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Asion on 2018/2/1.
 */

public class MyRecyclerViewTouchCallback extends ItemTouchHelper.Callback{

    public interface TouchCallbackListener {
        /**
         * 长按拖拽时的回调
         * @param fromPosition 拖拽前的位置
         * @param toPosition 拖拽后的位置
         */
        void onItemMove(int fromPosition, int toPosition);
        /**
         * 滑动时的回调
         * @param position 滑动的位置
         */
        void onItemSwipe(int position);
    }
    /**
     * 自定义的监听接口
     */
    private TouchCallbackListener mListener;
    public MyRecyclerViewTouchCallback(TouchCallbackListener listener) {
        this.mListener = listener;
    }
    /**
     * 定义列表可以怎么滑动（上下左右）
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //上下滑动
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //左右滑动
        int swipeFlag = ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT;
        //使用此方法生成标志返回
        return makeMovementFlags(dragFlag, swipeFlag);
    }


    /**
     * 拖拽移动时调用的方法
     * @param recyclerView 控件
     * @param viewHolder 移动之前的条目
     * @param target 移动之后的条目
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }
    /**
     * 滑动时调用的方法
     * @param viewHolder 滑动的条目
     * @param direction 方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mListener.onItemSwipe(viewHolder.getAdapterPosition());
    }
    /**
     * 是否允许长按拖拽
     * @return true or false
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     * 是否允许滑动
     * @return true or false
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }


}
