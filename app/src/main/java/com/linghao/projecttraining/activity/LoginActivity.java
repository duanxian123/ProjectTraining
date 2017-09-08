package com.linghao.projecttraining.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.linghao.projecttraining.R;
import com.linghao.projecttraining.model.Model;
import com.linghao.projecttraining.model.bean.User;
import com.linghao.projecttraining.utils.CacheUtils;
import com.linghao.projecttraining.utils.Contans;
import com.linghao.projecttraining.utils.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends Activity {

    private Button bt_login;
    private Button bt_register;
    private CheckBox cb_remember;
    private EditText et_name;
    private EditText et_psw;
    private TextView tv_restore_password;
    private String save_username;
    private String save_password;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initView();
        initListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            String username=data.getStringExtra("username");
            et_name.setText(username);
            String password=data.getStringExtra("password");
            et_name.setText(password);
        }
    }

    private void initListener() {
        tv_restore_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RestorePswActivity.class);
                startActivity(intent);
            }
        });
          //注册按钮
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivityForResult(intent,1);
            }
        });
        //登录按钮
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isConnected()){
                    Toast.makeText(LoginActivity.this, "没有网络，请检查您的网络设置", Toast.LENGTH_SHORT).show();
                }
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("number", et_name.getText().toString())
                                .add("password", et_psw.getText().toString())
                                .build();
                        Request request = new Request.Builder()
                                .url(Contans.LoginUrl)
                                .post(requestBody)
                                .build();

                        okHttpClient.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String json = response.body().string();
                                Log.e("success", json);
                                try {
                                    JSONObject jsonObject = new JSONObject(json);
                                    boolean isSuccess = jsonObject.getBoolean("success");
                                    LogUtil.e(isSuccess + "--------------");
                                    int id = jsonObject.getInt("id");
                                    String name = jsonObject.getString("name");
                                    String number = jsonObject.getString("number");
                                    String password = jsonObject.getString("password");
                                    int active_lv = jsonObject.getInt("active_lv");
                                    int credit_lv = jsonObject.getInt("credit_lv");
                                    String place = jsonObject.getString("place");
                                    int age = jsonObject.getInt("age");
                                    String icon = jsonObject.getString("icon");
                                    String sex = jsonObject.getString("sex");
                                    if (isSuccess) {
                                        if (cb_remember.isChecked()) {
                                            save_username = et_name.getText().toString();
                                            save_password = et_psw.getText().toString();
                                            CacheUtils.putString(LoginActivity.this, "username", save_username);
                                            CacheUtils.putString(LoginActivity.this, "password", save_password);
                                        } else {
                                            CacheUtils.putString(LoginActivity.this, "username", "");
                                            CacheUtils.putString(LoginActivity.this, "password", "");
                                        }
                                        //传递user对象
                                        user = new User();
                                        user.setId(id);
                                        user.setName(name);
                                        user.setNumber(number);
                                        user.setPassword(password);
                                        user.setActive_lv(active_lv);
                                        user.setCredit_lv(credit_lv);
                                        user.setPlace(place);
                                        user.setIcon(icon);
                                        user.setSex(sex);
                                        user.setAge(age);
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(LoginActivity.this, "帐号密码错误", Toast.LENGTH_SHORT).show();

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
        });
    }

    //判断是否有网
    private boolean isConnected() {
        boolean connected = false;

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            connected = networkInfo.isConnected();
        }
        return connected;

    }

    private void initView() {
        cb_remember = (CheckBox) findViewById(R.id.checkboxRemember);
        et_name = (EditText) findViewById(R.id.et_username);
        et_psw = (EditText) findViewById(R.id.etPassword);
        bt_login = (Button) findViewById(R.id.btnLogin);
        bt_register = (Button) findViewById(R.id.bt_register);
        tv_restore_password = (TextView) findViewById(R.id.tvRestore);
        String remember_username = CacheUtils.getString(LoginActivity.this, "username");
        LogUtil.e(remember_username + "--------------------");
        String remember_password = CacheUtils.getString(LoginActivity.this, "password");

        if (!TextUtils.isEmpty(remember_username)) {
            if (!TextUtils.isEmpty(remember_password)) {
                et_name.setText(CacheUtils.getString(LoginActivity.this, "username"));
                et_psw.setText(CacheUtils.getString(LoginActivity.this, "password"));
                cb_remember.setChecked(true);
            }
        }
    }
}
