package com.c201801020211.hujizhao.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.c201801020211.hujizhao.MyApplication;
import com.c201801020211.hujizhao.commmon.utils.MySp;
import com.c201801020211.hujizhao.commmon.utils.UIHelper;
import com.c201801020211.hujizhao.ui.BaseActivity;
import com.c201801020211.hujizhao.ui.permission.PermissionHelper;
import com.c201801020211.hujizhao.ui.permission.PermissionListener;
import com.c201801020211.hujizhao.ui.weight.CountDownProgressView;
import com.gyf.immersionbar.ImmersionBar;
import com.c201801020211.hujizhao.R;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {


    // private final int SPLASH_DISPLAY_LENGHT = 2000; // 两秒后进入系统
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏


        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    sleep(5000);//使程序休眠五秒
                    Intent it = new Intent(getApplicationContext(), LoginActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();//关闭当前活动
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程

    }
}