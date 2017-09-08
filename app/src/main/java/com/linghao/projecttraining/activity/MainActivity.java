package com.linghao.projecttraining.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.linghao.projecttraining.R;
import com.linghao.projecttraining.fragment.CreateGroupFragment;
import com.linghao.projecttraining.fragment.FindGroupFragment;
import com.linghao.projecttraining.fragment.HomePagerFragment;
import com.linghao.projecttraining.fragment.MeFragment;
import com.linghao.projecttraining.model.bean.User;
import com.linghao.projecttraining.utils.LogUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends FragmentActivity {
    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.homepage)
    RadioButton homepage;
    @InjectView(R.id.create_group)
    RadioButton createGroup;
    @InjectView(R.id.find_group)
    RadioButton findGroup;
    @InjectView(R.id.me)
    RadioButton me;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;
    private Button bt;
    private Handler handler;
   private CreateGroupFragment createGroupFragment;
    private FindGroupFragment findGroupFragment;
    private HomePagerFragment homePagerFragment;
    private MeFragment meFragment;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        user = (User) getIntent().getSerializableExtra("user");
        LogUtil.e(user.toString());
        initData();
    }



        public void switchGroupButton(){
            rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    Fragment fragment=null;
                    switch (checkedId)
                    {
                        case R.id.homepage:
                            fragment=homePagerFragment;
                            break;
                        case R.id.create_group:
                            fragment=createGroupFragment;
                            break;
                        case R.id.find_group:
                            fragment=findGroupFragment;
                            break;
                        case R.id.me:
                            fragment=meFragment;
                            break;
                    }
                    switchFragment(fragment);
                }
            });
        }

    private void switchFragment(Fragment fragment) {
        Bundle bundle=new Bundle();
             bundle.putSerializable("user",user);
               fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();

    }


    private void initData() {
        createGroupFragment=new CreateGroupFragment();
        findGroupFragment=new FindGroupFragment();
        homePagerFragment=new HomePagerFragment();
        meFragment=new MeFragment();
        handler = new Handler();
        switchGroupButton();
        rgMain.check(R.id.homepage);

    }
    private boolean isExit = true;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isExit) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            isExit = false;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = true;
                }
            }, 2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
