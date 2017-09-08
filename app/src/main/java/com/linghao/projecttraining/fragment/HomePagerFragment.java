package com.linghao.projecttraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linghao.projecttraining.base.BaseFragment;
import com.linghao.projecttraining.utils.DensityUtil;

/**
 * Created by linghao on 2017/9/6.
 * QQ：782695971
 * 作用：
 */

public class HomePagerFragment extends BaseFragment {

    @Override
    public View initView() {
        TextView textView=new TextView(getActivity());
        textView.setText("首页");
        textView.setTextSize(100);
        return textView;

    }
}
