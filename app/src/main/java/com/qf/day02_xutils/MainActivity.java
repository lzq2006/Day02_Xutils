package com.qf.day02_xutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qf.day02_xutils.demo01.AdapterActivity;
import com.qf.day02_xutils.demo02.GetActivity;
import com.qf.day02_xutils.demo03.PostActivity;
import com.qf.day02_xutils.demo04.DownLoadActivity;
import com.qf.day02_xutils.demo05.ImageActivity;
import com.qf.day02_xutils.demo06.DatabaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 注解的功能
 * @ContentView(value = 布局)   完成activity和xml的关联
 * @ViewInject(value = id)      作用是找到控件的id,必须要写在控件声明之上
 * @Event(value = {R.id.but_01},type = View.OnClickListener.class)  value是一个数组 代表的是控件的id; type 指定了事件的监听
 */

@ContentView(value = R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(value = R.id.but_01)
    private Button but01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //声明需要使用xutils的注解反射
        x.view().inject(this);

    }

    /**
     * 1, 方法必须是私有的限定
     * 2, 方法的参数必须要和type对应的Listener接口一致
     * 3, 注解参数value必须是一个数组
     */
    @Event(value = {R.id.but_01,R.id.but_adapter,R.id.but_get,R.id.but_post,R.id.but_download,R.id.but_image,R.id.but_db},type = View.OnClickListener.class)
    private void onClick(View view)
    {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case  R.id.but_01:

                Toast.makeText(MainActivity.this,"你点击了按钮",Toast.LENGTH_SHORT).show();

                break;

            case  R.id.but_adapter:

                intent.setClass(MainActivity.this,AdapterActivity.class);

                break;

            case  R.id.but_get:

                intent.setClass(MainActivity.this, GetActivity.class);

                break;

            case  R.id.but_post:

                intent.setClass(MainActivity.this, PostActivity.class);

                break;

            case R.id.but_download:

                intent.setClass(MainActivity.this,DownLoadActivity.class);

                break;

            case R.id.but_image:

                intent.setClass(MainActivity.this,ImageActivity.class);

                break;

            case R.id.but_db:

                intent.setClass(MainActivity.this,DatabaseActivity.class);

                break;
        }

        startActivity(intent);
    }
}
