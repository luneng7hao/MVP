package com.mvp.view.adapter.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mvp.view.R;

/**
 * Created by Asion on 2018/1/30.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;//定义线条高度
    private Paint dividerPaint;//定义线条背景
    private Context context;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public MyItemDecoration(Context context) {
        // 获取默认主题的属性
        dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.recyclerview_item_height);
        this.context = context;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        // 画线条
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = child.getBottom() + dividerHeight;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //该方法，空缺处画线条的高度
//        outRect.bottom = dividerHeight;
        outRect.set(0, 0, 0, 5);
    }
}