package com.qf.day02_xutils.demo03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.qf.day02_xutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value =  R.layout.activity_post)
public class PostActivity extends AppCompatActivity {

    @ViewInject(value =  R.id.my_tv)
    private TextView mTv;

    private  String url = "http://218.244.149.129:9010/api/companylist.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        initData();
    }

    private void initData()
    {
        //声明请求参数
        RequestParams params = new RequestParams(url);

        //?industryid=100
        params.addParameter("industryid","100");

        params.setMultipart(true);//使用Multipart表单上传文件

        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                mTv.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
