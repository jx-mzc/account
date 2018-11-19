package com.example.administrator.account.account;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.account.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //定义图片数组

    GridView gv;

    String []  titles=new String[]{"新增支出", "新增收入", "我的支出", "我的收入", "数据管理",
            "系统设置", "收支便签", "退出", "消费管理" ,"添加消费"};

    // 定义int数组，存储功能对应的图标
    int[] images = new int[] { R.drawable.addoutaccount,
            R.drawable.addinaccount, R.drawable.outaccountinfo,
            R.drawable.inaccountinfo, R.drawable.showinfo, R.drawable.sysset,
            R.drawable.accountflag, R.drawable.exit, R.drawable.sysset,R.drawable.outaccountinfo };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gv=(GridView)findViewById(R.id.gvInfo);
        PictureAdapter adapter = new PictureAdapter(titles, images, this);// 创建pictureAdapter对象
        gv.setAdapter(adapter);// 为GridView设置数据源
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,
                                    long arg3) {
                Intent intent=null;
                switch(arg2){
                    case 0:
                        intent = new Intent(MainActivity.this, AddOutaccount.class);// 使用AddOutaccount窗口初始化Intent
                        startActivity(intent);// 打开AddOutaccount
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, AddInaccount.class);// 使用AddInaccount窗口初始化Intent
                        startActivity(intent);// 打开AddInaccount
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, OutaccountInfo.class);// 使用Outaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Outaccountinfo
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, InaccountInfo.class);// 使用Inaccountinfo窗口初始化Intent
                        startActivity(intent);// 打开Inaccountinfo
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Showinfo.class);// 使用Showinfo窗口初始化Intent
                        startActivity(intent);// 打开Showinfo
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, Sysset.class);// 使用Sysset窗口初始化Intent
                        startActivity(intent);// 打开Sysset
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, Accountflag.class);// 使用Accountflag窗口初始化Intent
                        startActivity(intent);// 打开Accountflag
                        break;
                    case 7:
                        finish();// 关闭当前Activity
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, CumstomManage.class);// 使用Sysset窗口初始化Intent
                        startActivity(intent);// 消费管理
                        break;

                    case 9:
                        intent = new Intent(MainActivity.this,AddCutsom.class);// 使用Sysset窗口初始化Intent
                        startActivity(intent);// 添加消费
                        break;

                }//switch
            }

        });
    }

    //new adapter





    @Override
    protected void onResume() {
        /**
         * 设置为竖屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }
}

//---------------------------------------------


class PictureAdapter extends BaseAdapter {
    private LayoutInflater inflater;// 创建LayoutInflater对象
    private List<Picture> piclist;// 创建List泛型集合

    public PictureAdapter(String[] titles, int[] images, Context context){
        super();
        //
        piclist=new ArrayList<Picture>();  //arraylist
        inflater=LayoutInflater.from(context);

        for(int i=0;i<images.length;i++){
            Picture picture = new Picture(titles[i], images[i]);// 使用标题和图像生成Picture对象
            piclist.add(picture);

        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(piclist!=null)
        {
            return piclist.size();

        }else

            return 0;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return piclist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder vh;
        if(convertView==null)
        {
            //inflate layout

            convertView=inflater.inflate(R.layout.gvitem, null);
            vh=new ViewHolder();
            vh.image=(ImageView)convertView.findViewById(R.id.ItemImage);
            vh.title=(TextView)convertView.findViewById(R.id.ItemTitle);
            convertView.setTag(vh);

        }else{
            vh=(ViewHolder)convertView.getTag();

        }

        vh.title.setText(piclist.get(position).getTitle());// 设置图像标题
        vh.image.setImageResource(piclist.get(position).getImageId());// 设置图像的二进制值
        return convertView;// 返回图像标识


    }

}


class ViewHolder// 创建ViewHolder类
{
    public TextView title;// 创建TextView对象
    public ImageView image;// 创建ImageView对象
}

//-------------------------------------------------


class Picture// 创建Picture类
{
    private String title;// 定义字符串，表示图像标题
    private int imageId;// 定义int变量，表示图像的二进制值

    public Picture()// 默认构造函数
    {
        super();
    }

    public Picture(String title, int imageId)// 定义有参构造函数
    {
        super();
        this.title = title;// 为图像标题赋值
        this.imageId = imageId;// 为图像的二进制值赋值
    }

    public String getTitle() {// 定义图像标题的可读属性
        return title;
    }

    public void setTitle(String title) {// 定义图像标题的可写属性
        this.title = title;
    }

    public int getImageId() {// 定义图像二进制值的可读属性
        return imageId;
    }

    public void setimageId(int imageId) {// 定义图像二进制值的可写属性
        this.imageId = imageId;
    }
}


