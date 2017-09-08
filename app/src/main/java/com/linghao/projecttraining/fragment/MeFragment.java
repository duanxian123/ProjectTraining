package com.linghao.projecttraining.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linghao.projecttraining.model.bean.User;
import com.linghao.projecttraining.utils.LogUtil;

/**
 * Created by linghao on 2017/9/6.
 * QQ：782695971
 * 作用：
 */

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("我");
        return textView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获得窗体传递来的数据
        Bundle bundle = MeFragment.this.getArguments();
        User user=new User();
        user= (User) bundle.getSerializable("user");
        //显示传递来的数据
        LogUtil.e(user.toString()+"*****");
    }
}
