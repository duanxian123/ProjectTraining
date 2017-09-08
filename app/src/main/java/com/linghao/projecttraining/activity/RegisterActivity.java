package com.linghao.projecttraining.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linghao.projecttraining.R;
import com.linghao.projecttraining.model.Model;
import com.linghao.projecttraining.utils.Contans;
import com.linghao.projecttraining.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.linghao.projecttraining.R.id.iv_city;
import static com.linghao.projecttraining.utils.Contans.RegisterUrl;

public class RegisterActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;
    public TextView tvCity;
    @InjectView(R.id.etPassword)
    EditText etPassword;
    @InjectView(R.id.et_confirm_Password)
    EditText etConfirmPassword;
    @InjectView(R.id.et_nick)
    EditText etNick;
    @InjectView(R.id.iv_city)
    ImageView ivCity;
    @InjectView(R.id.btnRegister)
    Button btnRegister;
    @InjectView(R.id.et_username)
    EditText etUsername;
    private String username;
    private String psw;
    private String confirm_psw;
    private String nickname;
    private String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉标题栏
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        tvCity = (TextView) findViewById(R.id.tv_city);
        ivCity = (ImageView) findViewById(iv_city);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    }


    private void getData() {
        username = etUsername.getText().toString();
        psw = etPassword.getText().toString();
        confirm_psw = etConfirmPassword.getText().toString();
        nickname = etNick.getText().toString();
        city = tvCity.getText().toString();
        if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(psw)&&TextUtils.isEmpty(nickname)&&TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!psw.equals(confirm_psw)){
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void register() {
        LogUtil.e(username + psw + confirm_psw + nickname + city);
        registerToServlet();
    }

    private void registerToServlet() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("number",username)
                        .add("password",psw)
                        .add("name",nickname)
                        .add("place",city)
                        .build();
                Request request=new Request.Builder()
                        .url(Contans.RegisterUrl)
                        .post(requestBody)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.e("注册失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json=response.body().string();
                        try {
                            Log.e("json",json);
                            JSONObject jsonObject=new JSONObject(json);
                            boolean isSuccess = jsonObject.getBoolean("success");

                            if (isSuccess){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent();
                                        intent.putExtra("username",username);
                                        intent.putExtra("password",psw);
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }
                                });
                            }
                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @OnClick({R.id.iv_city, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_city:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.btnRegister:
                getData();
                register();
                break;
        }
    }
}
