package com.mvp.view.adapter.item;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Asion on 2018/1/31.
 */

public class MyItemAnimator extends DefaultItemAnimator {


    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return super.animateMove(holder, fromX, fromY, toX, toY);
    }
}
