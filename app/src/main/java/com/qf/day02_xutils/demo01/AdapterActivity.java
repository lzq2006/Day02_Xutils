package com.qf.day02_xutils.demo01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qf.day02_xutils.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_adapter)
public class AdapterActivity extends AppCompatActivity {

    @ViewInject(value = R.id.my_lv)
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //声明使用xutils的注解反射
        x.view().inject(this);

        //数据源
        List<String> data = new ArrayList<>();
        for (int i=0;i<30;i++){
            data.add("Item ----- > " + i);
        }

        //适配器
        MyAdapter adapter = new MyAdapter(data);

        mLv.setAdapter(adapter);

    }


    public class MyAdapter extends BaseAdapter
    {

        private List<String> data;

        public MyAdapter(List<String> data)
        {
            this.data = data;
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView==null)
            {
                convertView = LayoutInflater.from(AdapterActivity.this).inflate(R.layout.item_lv,parent,false);

                viewHolder = new ViewHolder();

                //指定在ViewHolder中 ,声明convertView页面的控件
                x.view().inject(viewHolder,convertView);

                convertView.setTag(viewHolder);

            }else
            {
                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.tv.setText(data.get(position));


            return convertView;
        }

        class ViewHolder
        {
            @ViewInject(value = R.id.my_tv_item)
            TextView tv;
        }
    }
}
