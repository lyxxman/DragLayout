package com.ly.baseproject.draglayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.ly.baseproject.draglayout.customView.DragLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.main_drag_layout)
    DragLayout mMainDragLayout;

    private MainTopFragment mMainTopFragment;//
    private MainBottomFragment mMainBottomFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
    }
    private void initEvent(){
        //设置拖拽动作监听事件
        DragLayout.ShowNextPageNotifier showNextPageNotifier = new DragLayout.ShowNextPageNotifier() {
            @Override
            public void onDragNext() {

            }

            @Override
            public void onDragTop() {

            }
        };
        mMainDragLayout.setNextPageListener(showNextPageNotifier);

        //添加fragment到activity
        mMainTopFragment = new MainTopFragment();
        mMainBottomFragment = new MainBottomFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_first,mMainTopFragment).add(R.id.frag_second, mMainBottomFragment).commit();
    }
}
