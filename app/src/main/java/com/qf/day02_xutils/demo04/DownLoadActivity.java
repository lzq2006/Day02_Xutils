package com.qf.day02_xutils.demo04;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.qf.day02_xutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(value = R.layout.activity_down_load)
public class DownLoadActivity extends AppCompatActivity {

    @ViewInject(value =  R.id.progress_bar_id)
    private ProgressBar progressBar;

    private String apkUrl = "http://218.244.149.129:9010/download.php?apkid=12";
    private String apkName = "hello.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

    }

    //如果不写type  , 默认是点击事件
    @Event(value = {R.id.download_but})
    private void onClick(View view)
    {
        //声明请求参数
        RequestParams params = new RequestParams(apkUrl);

        //设置存储的路径
        String filePath = getExternalCacheDir().getAbsolutePath()+ File.separator +  apkName;
        params.setSaveFilePath(filePath);

        x.http().get(params, new Callback.ProgressCallback<File>() {
            /**
             * @param total     总长度
             * @param current   当前的长度
             * @param isDownloading
             */
            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                //加载时的方法  ,  实时刷新数据
                int progress = (int)(current*100/total);

                progressBar.setProgress(progress);

            }
            @Override
            public void onSuccess(File result) {
                //下载成功,  自动安装apk
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                DownLoadActivity.this.startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                //下载失败
            }

            @Override
            public void onCancelled(CancelledException cex) {

                //取消
            }

            @Override
            public void onFinished() {

                //完成
            }

            @Override
            public void onWaiting() {
                //等待

            }

            @Override
            public void onStarted() {

                //开始
            }


        });
    }
}
