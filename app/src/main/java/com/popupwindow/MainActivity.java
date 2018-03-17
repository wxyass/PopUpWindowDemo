package com.popupwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        Button button5 = (Button) findViewById(R.id.btn5);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1://相对于父控件的位置 弹出popupwindow
                Intent popshowatlocation = new Intent(this,PopshowAtLocation.class);
                startActivity(popshowatlocation);
                break;
            case R.id.btn2:// 相对某个控件的位置 弹出popupwindow
                Intent popupshowasdropdown = new Intent(this,PopupShowAsDropDown.class);
                startActivity(popupshowasdropdown);
                break;
            case R.id.btn3:// 为菜单添加阴影
                Intent popdropdownbg = new Intent(this,PopDropDownBg.class);
                startActivity(popdropdownbg);
                break;
            case R.id.btn4:// 添加动画
                Intent popupanim = new Intent(this,PopupAnim.class);
                startActivity(popupanim);
                break;
            case R.id.btn5:// 添加动画
                Intent popselectnum = new Intent(this,PopSelectNum.class);
                startActivity(popselectnum);
                break;
            default:
                break;
        }
    }
}
