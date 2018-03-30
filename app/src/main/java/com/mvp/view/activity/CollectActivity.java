package com.mvp.view.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mvp.view.R;
import com.mvp.view.activity.base.ToolbarBaseActivity;
import com.mvp.view.adapter.MyAdapter;
import com.mvp.view.adapter.item.MyItemDecoration;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Asion on 2018/1/23.
 */

public class CollectActivity extends ToolbarBaseActivity {

    @BindView(R.id.rv_collect)
    SwipeMenuRecyclerView mRvCollect;

    private RecyclerView.LayoutManager mlayoutManager;
    private GridLayoutManager gridLayoutManager;
    private DividerItemDecoration mDividerItemDecoration;
    MyAdapter mRecyclerAdapter;
//    private CollectPresenter mCollectPresenter;//电影收藏的业务逻辑处理层
//    private CollectAdapter mCollectAdapter;

    /**
     * 引入布局
     */
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_collect);
    }

    /**
     * 初始化组件
     */
    @Override
    protected void initView() {
        getToolbar().setTitle(getResourceString(R.string.my_collect));
    }

    @Override
    protected void initData() {
        mlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        gridLayoutManager= new GridLayoutManager(this,3,GridLayoutManager.VERTICAL, false);
        mRecyclerAdapter = new MyAdapter(getData());

        //设置布局管理器
        mRvCollect.setLayoutManager(mlayoutManager);
        // 设置Item添加和移除的动画
//        mRvCollect.setItemAnimator(new DefaultItemAnimator());
//        // 设置Item之间间隔样式
        mRvCollect.addItemDecoration(new MyItemDecoration(this));
//        DefaultItemDecoration itemDecoration=new DefaultItemDecoration(Color.RED);

        //item的点击监听
//        mRvCollect.setSwipeItemClickListener(new SwipeItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                // TODO，搞事情...
//            }
//        });

        //侧滑菜单
        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
           int width = getResources().getDimensionPixelSize(R.dimen.recyclerview_item_swipebutton_wigth);
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext())
                .setBackground( R.drawable.item_no)
//                        .setText("删除")
//                        .setTextSize(20)
//                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                // 各种文字和图标属性设置。
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

                SwipeMenuItem deleteItem1 = new SwipeMenuItem(getContext())
                        .setBackground( R.drawable.item_ok)
//                        .setText("好的")
//                        .setTextSize(20).
//                        .setTextColor(Color.RED)
                        .setWidth(width)
                        .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                // 各种文字和图标属性设置。
                rightMenu.addMenuItem(deleteItem1); // 在Item右侧添加一个菜单。

                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };
//        // 设置监听器。
        mRvCollect.setSwipeMenuCreator(mSwipeMenuCreator);
//
        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            }
        };
       // 菜单点击监听。
        mRvCollect.setSwipeMenuItemClickListener(mMenuItemClickListener);
// 设置adapter
        mRvCollect.setAdapter(mRecyclerAdapter);
//        mRvCollect.setLongPressDragEnabled(true); // 拖拽排序，默认关闭。
//        mRvCollect.setItemViewSwipeEnabled(true); // 策划删除，默认关闭。

    }
    @Override
    protected void initEvent() {

    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }


//    public void deleteCollect(MovieCollect movieCollect) {
//        //从“电影收藏”表中删除该电影
//        mCollectPresenter.deleteFromMyCollect(movieCollect);
//        //通知MovieActivity刷新ToolBar右侧的收藏数量
//        mCollectPresenter.updateToobarCount();
//        //如果全部收藏的电影都被用户删除了，则设置页面为“无数据”状态
//        if (mCollectPresenter.getCollectCount() == 0) {
////            getLoadLayout().setLayoutState(State.NO_DATA);
////        }
//        }
}
