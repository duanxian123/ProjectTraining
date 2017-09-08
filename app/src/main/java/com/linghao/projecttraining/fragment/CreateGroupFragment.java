package com.linghao.projecttraining.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.linghao.projecttraining.R;
import com.linghao.projecttraining.activity.CreateGroupActivity;
import com.linghao.projecttraining.adapter.CreatGroupAdapter;
import com.linghao.projecttraining.base.BaseFragment;
import com.linghao.projecttraining.model.Model;
import com.linghao.projecttraining.model.bean.User;
import com.linghao.projecttraining.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by linghao on 2017/9/6.
 * QQ：782695971
 * 作用：
 */

public class CreateGroupFragment extends BaseFragment {
   private RecyclerView rvCreatGroup;
    private CreatGroupAdapter creatGroupAdapter;
    private FloatingActionButton fab_add;
    private User user;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_creat_group, null);
        rvCreatGroup= (RecyclerView) view.findViewById(R.id.rv_creat_group);
        fab_add= (FloatingActionButton) view.findViewById(R.id.fab_add);
        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.fresh_group);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getData();
        setCreateGroupAdapter();
        initListener();
    }

    private void getData() {
        //获得窗体传递来的数据
        Bundle bundle = CreateGroupFragment.this.getArguments();
        user = new User();
        user = (User) bundle.getSerializable("user");
        //显示传递来的数据
        LogUtil.e(user.toString()+"*****");
    }

    private void setCreateGroupAdapter() {
        creatGroupAdapter = new CreatGroupAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        rvCreatGroup.setLayoutManager(linearLayoutManager);
        rvCreatGroup.setAdapter(creatGroupAdapter);
    }

    private void initListener() {
        //悬浮按钮
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CreateGroupActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        creatGroupAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

}
