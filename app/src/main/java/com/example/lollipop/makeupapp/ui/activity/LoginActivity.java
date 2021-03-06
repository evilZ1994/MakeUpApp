package com.example.lollipop.makeupapp.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.lollipop.makeupapp.R;
import com.example.lollipop.makeupapp.app.AppManager;
import com.example.lollipop.makeupapp.bean.bmob.User;
import com.example.lollipop.makeupapp.ui.base.BaseActivity;
import com.example.lollipop.makeupapp.ui.listener.InputClearListener;
import com.example.lollipop.makeupapp.util.FileUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity {
    ProgressDialog progressDialog;

    @BindView(R.id.login_username)
    TextInputEditText usernameInput;
    @BindView(R.id.login_pass)
    TextInputEditText passInput;
    @BindView(R.id.login)
    AppCompatButton loginBtn;

    @OnClick(R.id.login_close)
    void close(){
        AppManager.getInstance().AppExit(this);
    }
    @OnClick(R.id.login_register)
    void register(){
        startActivity(new Intent(this, RegisterActivity.class));
    }
    @OnClick(R.id.login_forget)
    void forget(){
        startActivity(new Intent(this, FindPassActivity.class));
    }
    @OnClick(R.id.login)
    void login(){
        progressDialog.show();
        String username = usernameInput.getText().toString().replaceAll(" ", "");//去掉空格
        String password = passInput.getText().toString().replaceAll(" ", "");
        User.loginByAccount(username, password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null){
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    //检查头像
                    FileUtil.getInstance().checkHeadIcon();
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        progressDialog = new ProgressDialog(this, R.style.ProgressDialogTheme);
        progressDialog.setMessage("正在登陆...");

        InputClearListener.addListener(this, usernameInput);
        InputClearListener.addListener(this, passInput);
        usernameInput.addTextChangedListener(new PwdWatcher());
        passInput.addTextChangedListener(new PwdWatcher());
    }

    private class PwdWatcher implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (passInput.length() > 0 && usernameInput.length() > 0){
                loginBtn.setEnabled(true);
            }else {
                loginBtn.setEnabled(false);
            }
        }
    }
}
