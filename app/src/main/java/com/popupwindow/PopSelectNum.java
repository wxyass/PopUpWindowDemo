package com.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PopSelectNum extends AppCompatActivity implements View.OnClickListener{

    private EditText etNum;
    private List<String> numbers;
    private PopupWindow popupWindow;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popselectnum);

        initViews();
    }

    private void initViews() {
        etNum = (EditText) findViewById(R.id.et_num);
        findViewById(R.id.ib_select_num).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 弹出一个号码列表

        initListView();

        popupWindow = new PopupWindow(listView, etNum.getWidth(), 300);

        // 设置外部可点击消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 设置可获取焦点
        popupWindow.setFocusable(true);

        popupWindow.showAsDropDown(etNum, 0, -5);

    }

    /**
     * 创建一个号码列表弹窗
     */
    private void initListView() {

        listView = new ListView(this);
        listView.setDividerHeight(0);
        listView.setBackgroundResource(R.drawable.listview_background);

        numbers = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            numbers.add((10000 + i) + "");
        }

        // 给ListView设置数据
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                System.out.println("onItemClick: " + position);

                //点击列表条目, 把数据设置给etNum

                String num = numbers.get(position);
                etNum.setText(num);

                popupWindow.dismiss();

            }
        });


    }
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return numbers.size();
        }

        @Override
        public Object getItem(int position) {
            return numbers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = View.inflate(PopSelectNum.this, R.layout.item_number, null);
            }else {
                view = convertView;
            }

            TextView tvNumber = (TextView) view.findViewById(R.id.tv_number);
            tvNumber.setText(numbers.get(position));
            ImageButton ibDelete = (ImageButton) view.findViewById(R.id.ib_delete_number);

            ibDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    System.out.println("position: " + position);

                    // 删除当前位置的数据
                    numbers.remove(position);

                    // 通知更新
                    MyAdapter.this.notifyDataSetChanged();

                    // 如果最后一条被删除了, 让窗体消失
                    if(numbers.size() == 0){
                        popupWindow.dismiss();
                    }
                }
            });

            return view;
        }

    }
}
