package com.linghao.projecttraining.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.linghao.projecttraining.R;
import com.linghao.projecttraining.fragment.CreateGroupFragment;
import com.linghao.projecttraining.model.Model;
import com.linghao.projecttraining.model.bean.User;
import com.linghao.projecttraining.utils.Contans;
import com.linghao.projecttraining.utils.LogUtil;
import com.linghao.projecttraining.utils.customView.DateTimePickDialogUtil;

import java.io.IOException;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CreateGroupActivity extends AppCompatActivity {

    @InjectView(R.id.et_groupname)
    EditText etGroupname;
    @InjectView(R.id.et_group_introduction)
    EditText etGroupIntroduction;
    @InjectView(R.id.et_group_type)
    EditText etGroupType;
    @InjectView(R.id.et_group_starttime)
    EditText etGroupStarttime;
    @InjectView(R.id.finish_create_group)
    Button finishCreateGroup;
    private String groupName;
    private String groupIntroduction;
    private String groupType;
    private String groupStartTime;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        ButterKnife.inject(this);
        getData();//从CreateGroup获取到数据
        initView();
        initListener();
    }

    private void initListener() {
        finishCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creatGrouptoServlet();
            }
        });
        etGroupStarttime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
    }

    private void showDatePickDlg() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
//        new DatePickerDialog(CreateGroupActivity.this, new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
//                etGroupStarttime.setText(String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
//            }
//
//        },year,month,day).show();
         String initStartDateTime = year+"年"+month+"月"+day+"日"+" "+hour+":"+minute; // 初始化开始时间
        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                CreateGroupActivity.this, initStartDateTime);
        dateTimePicKDialog.dateTimePicKDialog(etGroupStarttime);
    }

    private void getData() {
        user = (User) getIntent().getSerializableExtra("user");
        LogUtil.e(user.toString());
    }

    private void initView() {
        groupName = etGroupname.getText().toString();
        groupIntroduction = etGroupIntroduction.getText().toString();
        groupType = etGroupType.getText().toString();
        groupStartTime = etGroupStarttime.getText().toString();
    }

    private void creatGrouptoServlet() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("name", groupName)
                        .add("Introduction", groupIntroduction)
                        .add("Type_id", groupType)
                        .add("start", groupStartTime)
                        .build();
                final Request request = new Request.Builder()
                        .url(Contans.CreateGroupUtl)
                        .post(requestBody)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.e(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result=response.body().string();
                        LogUtil.e(result);

                    }
                });


            }
        });

    }
}
