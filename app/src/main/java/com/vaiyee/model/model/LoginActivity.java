package com.vaiyee.model.model;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;
    private String a ="";
    private String b ="";
    private String[] userlist = new String[]{"user","admin","guest"};
    private String mima = "123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT>=21)
        {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE); //使背景图与状态栏融合到一起，这里需要在setcontentview前执行
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void onViewClicked() {
        a = account.getText().toString();
        b = password.getText().toString();
        if (!TextUtils.isEmpty(a)&&!TextUtils.isEmpty(b))
        {
            for (int i=0;i<3;i++)
            {
                if (a.equals(userlist[i])&&b.equals(mima))
                {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setMessage("登录成功")//设置对话框的内容
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }).create();
                    dialog.show();
                    return;
                }
                else
                {
                    Toast toast= Toast.makeText(LoginActivity.this,"账号或密码错误！",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
            }
        }
        else
        {
            Toast toast= Toast.makeText(LoginActivity.this,"请输入账号或密码",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER , 0, 0);
            toast.show();
        }
    }
}

