package com.vaiyee.model.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Adapter.MyPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText7;
    private ImageView img;
    private Double a,b,c,d,e,f,sum;
    private FrameLayout frameLayout;
    private Button open,start,count,showresult,showimg,open2,start2,count2,showresult2,showimg2;
    private EditText editText6;
    private Button sure,sure2;
    private TextView result,result2;
    private List<String> stringList = new ArrayList<>();
    private PopupWindow popupWindow,inputdata;
    private ImageView p1,p2,p3,p4,p11,p22,p33,p44;
    private static int index;
    private String[] types = new String[]{"一类海水", "二类海水", "三类海水", "四类海水"};
    private String[] cacutype = new String[]{"模型一", "模型二", "模型三", "模型四"};
    private List<String> CalculationModel = new ArrayList<String>();
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private boolean isOne=false,isTwo=false,isThree=false,isfour=false;
    private static ProgressDialog progressDialog;
    private Timer timer;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 4; i++) {
            stringList.add(types[i]);
            CalculationModel.add(cacutype[i]);
        }
        View page1 = LayoutInflater.from(this).inflate(R.layout.pager1, null);
        start = page1.findViewById(R.id.start);
        start.setOnClickListener(this);
        sure = page1.findViewById(R.id.sure);
        sure.setOnClickListener(this);
        count = page1.findViewById(R.id.start_Calculation);
        count.setOnClickListener(this);
        showresult = page1.findViewById(R.id.show_result);
        showimg = page1.findViewById(R.id.show_img);
        showimg.setOnClickListener(this);
        showresult.setOnClickListener(this);
        result = page1.findViewById(R.id.result);
        p1 = page1.findViewById(R.id.p1);
        p2 = page1.findViewById(R.id.p2);
        p3 = page1.findViewById(R.id.p3);
        p4 = page1.findViewById(R.id.p4);
        View page2 = LayoutInflater.from(this).inflate(R.layout.pager2, null);
        start2 = page2.findViewById(R.id.start2);
        start2.setOnClickListener(this);
        sure2 = page2.findViewById(R.id.sure2);
        sure2.setOnClickListener(this);
        count2 = page2.findViewById(R.id.start_Calculation2);
        count2.setOnClickListener(this);
        showresult2 = page2.findViewById(R.id.show_result2);
        showresult2.setOnClickListener(this);
        showimg2 = page2.findViewById(R.id.show_img2);
        showimg2.setOnClickListener(this);
        result2 = page2.findViewById(R.id.result2);
        p11 = page2.findViewById(R.id.p11);
        p22 = page2.findViewById(R.id.p22);
        p33 = page2.findViewById(R.id.p33);
        p44 = page2.findViewById(R.id.p44);
        mTitleList.add("COD容量在线分析");
        mTitleList.add("石油类容量在线分析");
        mViewList.add(page1);
        mViewList.add(page2);
        tabs.addTab(tabs.newTab().setText("COD容量在线分析"));
        tabs.addTab(tabs.newTab().setText("石油类容量在线分析"));
        viewpager.setAdapter(new MyPagerAdapter(mViewList, mTitleList));
        tabs.setupWithViewPager(viewpager); //将TabLayout和ViewPager关联起来。
        //tabs.setTabsFromPagerAdapter(new MyPagerAdapter(mViewList)); //给Tabs设置适配器
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
/*
    @OnClick({R.id.img, R.id.open, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img:
                img.setImageResource(R.drawable.up);
                showMunu();
                break;
            case R.id.open:
                img.setImageResource(R.drawable.up);
                showMunu();
                break;
            case R.id.sure:

                String s = "";
                if (!TextUtils.isEmpty(editText.getText().toString()) && !TextUtils.isEmpty(editText2.getText().toString()) && !TextUtils.isEmpty(editText3.getText().toString())) {
                    s += editText.getText().toString() + "\n" + editText2.getText().toString() + "\n" + editText3.getText().toString();
                } else {
                    Toast.makeText(this, "请输入完整信息", Toast.LENGTH_LONG).show();
                    return;
                }
                if (open.getText().equals("点击选择模型")) {
                    Toast.makeText(this, "请选择一个模型", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    s += "\n" + open.getText();
                    Intent intent = new Intent(this, Main2Activity.class);
                    intent.putExtra("i", index);
                    intent.putExtra("s", s);
                    startActivity(intent);
                }

                break;
        }
    }
    */

    private void showMunu(int type) {
        View view = LayoutInflater.from(this).inflate(R.layout.list_item, null);
        initview(view,type,sure);
        popupWindow = new PopupWindow(view, 350, 500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popmenu_animation);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                img.setImageResource(R.drawable.down);
            }
        });
        popupWindow.showAtLocation(getWindow().getDecorView(),Gravity.CENTER,220,480);



    }
    private void showCacuMode(int type,Button v)
    {
        View view = LayoutInflater.from(this).inflate(R.layout.list_item, null);
        initview(view,type, v);
        popupWindow = new PopupWindow(view, 350, 500);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.popmenu_animation);
        popupWindow.showAsDropDown(v);
    }
    private void showInputWindow()
    {
        View contentview = LayoutInflater.from(this).inflate(R.layout.getdata,null);
        initInputView(contentview);
        inputdata = new PopupWindow(contentview,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        inputdata.setOutsideTouchable(false);
        inputdata.setTouchable(true);
        inputdata.setFocusable(true);
        inputdata.setAnimationStyle(R.style.shangxia);
        inputdata.showAtLocation(getWindow().getDecorView(),Gravity.CENTER,0,0);
    }
   private void initInputView(View view)
    {
        editText = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);
        editText4 = view.findViewById(R.id.editText4);
        editText6 = view.findViewById(R.id.editText6);
        editText7 = view.findViewById(R.id.editText7);
        frameLayout =view.findViewById(R.id.cardView);
        open = view.findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.up);
                showMunu(0); //0表示弹出海水类别下拉列表
            }
        });
        img = view.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.up);
                showMunu(0);//0表示弹出海水类别下拉列表
            }
        });
        Button get = view.findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText().toString()))
                {
                    a = Double.parseDouble(editText.getText().toString()); //COD实测浓度
                }
                b =  Double.parseDouble(editText2.getText().toString());// COD浓度阈值
                c =  Double.parseDouble(editText3.getText().toString());//COD背景值
                d =  Double.parseDouble(editText4.getText().toString());//COD容许增量
                e =  Double.parseDouble(editText6.getText().toString());//每潮周净交换量
                f =  Double.parseDouble(editText7.getText().toString());//潮   周   期   值
                p1.setImageResource(R.drawable.jiantou_lv);
                start.setText("已输入数据");
                isOne = true;
                inputdata.dismiss();
            }
        });
    }
    private void showInputView2()
    {
        View contentview = LayoutInflater.from(this).inflate(R.layout.getdata2,null);
        initInputView2(contentview);
        inputdata = new PopupWindow(contentview,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        inputdata.setOutsideTouchable(false);
        inputdata.setTouchable(true);
        inputdata.setFocusable(true);
        inputdata.setAnimationStyle(R.style.shangxia);
        inputdata.showAtLocation(getWindow().getDecorView(),Gravity.CENTER,0,0);
    }

    private void initInputView2(View view)
    {
        editText = view.findViewById(R.id.editText);
        editText2 = view.findViewById(R.id.editText2);
        editText3 = view.findViewById(R.id.editText3);
        editText4 = view.findViewById(R.id.editText4);
        editText6 = view.findViewById(R.id.editText6);
        editText7 = view.findViewById(R.id.editText7);
        open = view.findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.up);
                showMunu(0); //0表示弹出海水类别下拉列表
            }
        });
        img = view.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img.setImageResource(R.drawable.up);
                showMunu(0);//0表示弹出海水类别下拉列表
            }
        });
        Button get = view.findViewById(R.id.get2);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = Double.parseDouble(editText.getText().toString()); //每潮周净交换量
                d =  Double.parseDouble(editText4.getText().toString());//石油类容许增量容许增量
                e =  Double.parseDouble(editText6.getText().toString());//石油类浓度阈值
                f =  Double.parseDouble(editText7.getText().toString());//潮   周   期   值
                p11.setImageResource(R.drawable.jiantou_lv);
                isOne = true;
                start2.setText("已输入数据");
                inputdata.dismiss();
            }
        });
    }

    private void initview(View view, final int type, final Button vg) {
        ListView listView = view.findViewById(R.id.list_view);
        switch (type)
        {
            case 0:
                listView.setAdapter(new Myadpter(stringList));
                break;
            case 1:
                listView.setAdapter(new Myadpter(CalculationModel));
                break;
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*
                if (i < 11) {
                    index = i;
                } else {
                    index = getRandom();
                }
                */
                switch (type)
                {
                    case 0:
                        open.setText(types[i]);
                        break;
                    case 1:
                        vg.setText(cacutype[i]);
                        p22.setImageResource(R.drawable.jiantou_lv);
                        p2.setImageResource(R.drawable.jiantou_lv);
                        isTwo = true;
                        index = i;
                        break;
                }
                popupWindow.dismiss();
            }
        });
    }

    private int getRandom() {
        int size = 10;
        int randomshu = (int) (Math.random() * size);
        return randomshu;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sure:
                if (isOne) {
                    showCacuMode(1,sure);//1表示弹出计算模型下拉列表
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"请输入数据",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.start:
                showInputWindow();
                break;
            case R.id.start_Calculation:
                if (isTwo) {
                    if (task!=null)
                    {
                        task.cancel();
                        timer.cancel();
                    }
                    ShowProgress();
                    timer = new Timer();
                    timer.schedule(new Task(),2000);
                    sum = e * 0.124 * f * d + 0.5 * e * b * 0.06 * 3600;
                    p3.setImageResource(R.drawable.jiantou_lv);
                    isThree = true;
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"请选择计算模型",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.show_result:
                if (isThree) {
                    isOne = false;
                    isTwo = false;
                    isThree = false;
                    isfour = true;
                    p4.setImageResource(R.drawable.jiantou_lv);
                    View view1 = LayoutInflater.from(this).inflate(R.layout.result_view, null);
                    Button close = view1.findViewById(R.id.close);
                    TextView result = view1.findViewById(R.id.result);
                    DecimalFormat df = new DecimalFormat("#.00");
                    result.setText("环境容量估算结果： " + String.valueOf(df.format(sum)) + "(t/a)");
                    final PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, 300);
                    popupWindow.setOutsideTouchable(false);
                    popupWindow.setFocusable(true);  //上下这两行设置点击外部无效
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            p1.setImageResource(R.drawable.jiantou);
                            p2.setImageResource(R.drawable.jiantou);
                            p22.setImageResource(R.drawable.jiantou);
                            p3.setImageResource(R.drawable.jiantou);
                            start.setText("请输入数据");
                        }
                    });
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            p1.setImageResource(R.drawable.jiantou);
                            p2.setImageResource(R.drawable.jiantou);
                            p3.setImageResource(R.drawable.jiantou);
                            p22.setImageResource(R.drawable.jiantou);
                            start.setText("请输入数据");
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.setAnimationStyle(R.style.popuAnim);
                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"请运行计算",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.start2:
                showInputView2();
                break;
            case R.id.sure2:
                if (isOne) {
                    showCacuMode(1, sure2);
                }
                else
                {
                        Toast toast= Toast.makeText(MainActivity.this,"请输入数据",Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER , 0, 0);
                        toast.show();
                }
                break;
            case R.id.start_Calculation2:
                if (isTwo) {
                    if (task!=null)
                    {
                        task.cancel();
                        timer.cancel();
                    }
                    ShowProgress();
                    timer = new Timer();
                    timer.schedule(new Task(),2000);
                    sum = a*d*f+0.33*a*e*0.071*3600;
                    p33.setImageResource(R.drawable.jiantou_lv);
                    isThree = true;
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"请选择计算模型",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.show_result2:
                if (isThree) {
                    isOne = false;
                    isTwo = false;
                    isThree = false;
                    isfour = true;
                    p44.setImageResource(R.drawable.jiantou_lv);
                    View view1 = LayoutInflater.from(this).inflate(R.layout.result_view, null);
                    Button close = view1.findViewById(R.id.close);
                    TextView result = view1.findViewById(R.id.result);
                    DecimalFormat df = new DecimalFormat("#.00");
                    result.setText("石油类容量估算结果： " + String.valueOf(df.format(sum)) + "(t/a)");
                    final PopupWindow popupWindow = new PopupWindow(view1, ViewGroup.LayoutParams.MATCH_PARENT, 300);
                    popupWindow.setOutsideTouchable(false);
                    popupWindow.setFocusable(true);  //上下这两行设置点击外部无效
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            p11.setImageResource(R.drawable.jiantou);
                            p22.setImageResource(R.drawable.jiantou);
                            p2.setImageResource(R.drawable.jiantou);
                            p33.setImageResource(R.drawable.jiantou);
                            start2.setText("请输入数据");

                        }
                    });
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            p11.setImageResource(R.drawable.jiantou);
                            p2.setImageResource(R.drawable.jiantou);
                            p22.setImageResource(R.drawable.jiantou);
                            p33.setImageResource(R.drawable.jiantou);
                            start2.setText("请输入数据");
                            popupWindow.dismiss();
                        }
                    });
                    popupWindow.setAnimationStyle(R.style.popuAnim);
                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"请运行计算",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.show_img:
                if (isfour) {
                    isfour = false;
                    p4.setImageResource(R.drawable.jiantou);
                    Intent intent = new Intent(this, Main2Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"未进行计算，暂无成果图",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;
            case R.id.show_img2:
                if (isfour) {
                    isfour = false;
                    p44.setImageResource(R.drawable.jiantou);
                    Intent intent1 = new Intent(this, Main2Activity.class);
                    startActivity(intent1);
                }
                else
                {
                    Toast toast= Toast.makeText(MainActivity.this,"未进行计算，暂无成果图",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
                break;

        }
    }
    private class Task extends TimerTask
    {

        @Override
        public void run() {
            CloseProgress();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast toast= Toast.makeText(MainActivity.this,"计算成功！",Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER , 0, 0);
                    toast.show();
                }
            });
        }
    }

    //显示正在加载数据对话框
    private void ShowProgress()
    {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("正在计算...请稍后");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
    //关闭正在加载对话框
    private static void CloseProgress()
    {
        if (progressDialog!=null)
        {
            progressDialog.dismiss();
        }
    }

    class Myadpter extends BaseAdapter {
        List<String> stringList;

        public Myadpter(List<String> stringList) {
            this.stringList = stringList;
        }

        @Override
        public int getCount() {
            return stringList.size();
        }

        @Override
        public Object getItem(int i) {
            return stringList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            String s = stringList.get(i);
            ViewHolder viewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item, null);
                viewHolder = new ViewHolder();
                viewHolder.name = view.findViewById(R.id.name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.name.setText(s);
            return view;
        }

        class ViewHolder {
            TextView name;
        }
    }
}
