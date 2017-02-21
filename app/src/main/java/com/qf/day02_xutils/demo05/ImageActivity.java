package com.qf.day02_xutils.demo05;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.qf.day02_xutils.R;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(value =  R.layout.activity_image)
public class ImageActivity extends AppCompatActivity {

    @ViewInject(value = R.id.my_iv)
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
    }


    //按钮的点击事件
    @Event(value = {R.id.but_assets,R.id.but_load,R.id.but_network})
    private void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.but_assets:

                //图片的选项
                ImageOptions options = new ImageOptions.Builder()
                        .setLoadingDrawableId(R.mipmap.ic_launcher)//设置加载过程中显示的图片
                        .setFailureDrawableId(R.mipmap.ic_launcher)//设置加载失败时显示的图片
                        .setUseMemCache(true)//设置是否需要使用缓存
                        .setIgnoreGif(false)//设置是否忽略gif格式的图片
                        .build();

                 x.image().bind(mIv,"assets://test.gif",options);

                break;

            case  R.id.but_load:

                 options = new ImageOptions.Builder()
                           .setLoadingDrawableId(R.mipmap.ic_launcher)
                           .setFailureDrawableId(R.mipmap.ic_launcher)
                           .setUseMemCache(true)
                          .build();

                x.image().bind(mIv,"/sdcard/abc.jpg",options);
                break;


            case  R.id.but_network:

                String imgPath = "http://cs.vmoiver.com/Uploads/cover/2016-11-22/58341f270a239_cut.jpeg";

                options = new ImageOptions.Builder()
                        .setUseMemCache(true)
                        .build();

                x.image().bind(mIv, imgPath, options, new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {

                        //Drawable转Bitmap
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) result;
                        Bitmap bitmap = bitmapDrawable.getBitmap();

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

                break;
        }
    }
}
