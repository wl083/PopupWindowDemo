package com.wl.popunwindowdemo;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * # 1、修复返回监听时报异常；
 *   2、enter中的动画效果为：从中间向外扩展
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnShowWindow,btnHideWindow,btnChooseOne,btnChooseTwo;
    private PopupWindow mPopupWindow;
    private View popView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initView();

    }

    private void initView() {
        btnShowWindow = (Button) findViewById(R.id.showpopupwindow);
        btnShowWindow.setOnClickListener(this);
        btnHideWindow = (Button) findViewById(R.id.hidepopupwindow);
        btnHideWindow.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showpopupwindow:
                // * show
                showPopupWindow(view);
                break;
            case R.id.hidepopupwindow:
                //* hide
                hidePopupWindow();
                break;

            case R.id.choose_one:
                String str1 = btnChooseOne.getText().toString();
                Toast.makeText(this,"您选择了" + str1,Toast.LENGTH_SHORT).show();
                break;
            case R.id.choose_two:
                String str2 = btnChooseOne.getText().toString();
                Toast.makeText(this,"您选择了" + str2,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void hidePopupWindow() {
        if (mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }

    private void showPopupWindow(View view) {
        popView = LayoutInflater.from(this).inflate(R.layout.window_pop,null);
        int WindowWidth = getResources().getDisplayMetrics().widthPixels;
        int wide = WindowWidth/2;
        mPopupWindow = new PopupWindow(popView,WindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        /**
         * those methed must be set
         */
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        //* setAnimation
        mPopupWindow.setAnimationStyle(R.style.popAnimation);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM,wide,0);



        //* button of popupwindow
        btnChooseOne = (Button) popView.findViewById(R.id.choose_one);
        btnChooseOne.setOnClickListener(this);
        btnChooseTwo = (Button) popView.findViewById(R.id.choose_two);
        btnChooseTwo.setOnClickListener(this);


        // 设置页面为半透明状态
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);


        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //* 还原页面透明状态
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mPopupWindow != null && mPopupWindow.isShowing()) {
//            # 如果写成 keyCode == KeyEvent.KEYCODE_BACK && mPopupWindow.isShowing() && mPopupWindow != null 会报nullpointer
            mPopupWindow.dismiss();
            return true;

        }
        return super.onKeyDown(keyCode, event);

    }
}
