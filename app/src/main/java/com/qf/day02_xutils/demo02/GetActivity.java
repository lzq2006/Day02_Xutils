package com.qf.day02_xutils.demo02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.qf.day02_xutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value =  R.layout.activity_get)
public class GetActivity extends AppCompatActivity {

    @ViewInject(value =  R.id.my_tv)
    private TextView mTv;

    private String url = "http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=0&p=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        initData();
    }

    private void initData()
    {
        //声明请求的参数
        RequestParams params = new RequestParams(url);

        Log.e("tag", "---"+  params );

        //执行网络请求, 加载数据
        Callback.Cancelable cancelable = x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //成功的回调方法
                mTv.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                //失败的回调方法
            }

            @Override
            public void onCancelled(CancelledException cex) {

                //取消时的回调方法
            }

            @Override
            public void onFinished() {

                //完成时的回调方法
            }
        });

        //取消请求
       // cancelable.cancel();
    }
}
