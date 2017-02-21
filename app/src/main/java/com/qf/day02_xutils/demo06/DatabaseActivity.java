package com.qf.day02_xutils.demo06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.qf.day02_xutils.R;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(value = R.layout.activity_database)
public class DatabaseActivity extends AppCompatActivity {

    @ViewInject(value =  R.id.my_lv)
    private ListView mLv;

    private List<Person> data;

    private DatabaseAdapter adapter;

    //DaoConfig
    private DbManager.DaoConfig config;

    //得到数据库访问接口
    private DbManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);

        config = DbUtils.getDaoConfig(this);

        //实例化数据库的管理对象
        manager = x.getDb(config);

        //查询数据
        data  = new ArrayList<>();

        //已进入就开始查询一边数据
        if (queryAll()!=null)
        {
            data.addAll(queryAll());
        }

        adapter = new DatabaseAdapter(this,data);

        mLv.setAdapter(adapter);

    }


    @Event(value = {R.id.insert_but,R.id.update_but,R.id.delete_but})
    private void onClick(View view)
    {
        switch (view.getId())
        {
            case  R.id.insert_but:

                insertPerson(new Person(1,"张三",23,"男"));
                insertPerson(new Person(1,"李四",25,"男"));
                insertPerson(new Person(1,"王五",32,"男"));
                insertPerson(new Person(1,"赵六",28,"男"));
                insertPerson(new Person(1,"田七",29,"男"));
                insertPerson(new Person(1,"王八",40,"男"));

                //插入数据后, 再次查询数据
                data.clear();
                List<Person> list = queryAll();
                if (list!=null)
                {
                    data.addAll(list);
                }

                adapter.notifyDataSetChanged();

                break;
        }

    }

    /**
     * 插入数据
     */
    public void insertPerson(Person person)
    {
        try {
            manager.save(person);

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询数据
     */
    public List<Person> queryAll()
    {
        try {
            List<Person> list = manager.findAll(Person.class);
            return list;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
