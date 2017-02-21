package com.qf.day02_xutils.demo06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qf.day02_xutils.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class DatabaseAdapter extends BaseAdapter {

    private Context context;

    private List<Person> data;

    public DatabaseAdapter(Context context,List<Person> data)
    {
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_db,parent,false);

            viewHolder = new ViewHolder();

            x.view().inject(viewHolder,convertView);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = data.get(position);
        viewHolder.tv_id.setText(person.getId()+"");
        viewHolder.tv_name.setText(person.getName());
        viewHolder.tv_age.setText(person.getAge()+"");
        viewHolder.tv_sex.setText(person.getSex());


        return convertView;
    }

    class ViewHolder
    {
        @ViewInject(value = R.id.tv_id)
        TextView tv_id;

        @ViewInject(value = R.id.tv_name)
        TextView tv_name;

        @ViewInject(value = R.id.tv_age)
        TextView tv_age;

        @ViewInject(value = R.id.tv_sex)
        TextView tv_sex;
    }
}
