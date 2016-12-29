package com.ly.baseproject.draglayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ly
 * @version V1.0
 * @Package com.ly.baseproject.draglayout
 * @Description: 首页顶部
 * @date 2016/12/29 10:16
 */
public class MainTopFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_top,null);
        return view;
    }
}
