package com.sizu.mingteng.my_xianglekang.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.databinding.FragmentHomeThreeBinding;
import com.sizu.mingteng.my_xianglekang.ui.fragment.adapter.ConstellationAdapter;
import com.sizu.mingteng.my_xianglekang.ui.fragment.adapter.GirdDropDownAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2017/5/24.
 */

public class HomeThreeFragment extends Fragment {

    private FragmentHomeThreeBinding mHomeThreeBinding;
    private String headers[] = {"城市", "年龄", "性别", "星座"};
    private String citys[] = {"不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};
    private String constellations[] = {"不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private int constellationPosition = 0;
    private DropDownMenu mDropDownMenu;
    private GirdDropDownAdapter mAdapter01;
    private GirdDropDownAdapter mAdapter02;
    private GirdDropDownAdapter mAdapter03;
    private ConstellationAdapter mAdapter04;
    private List<View> popupViews = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_home_three, container, false);
        mHomeThreeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_three, container, false);
        return mHomeThreeBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDropDownMenu = mHomeThreeBinding.dropDownMenu;
        initView();
        initData();
    }


    private void initView() {

        RecyclerView recyclerView01 = new RecyclerView(getContext());
        /*LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView01.setLayoutManager(layoutManager);*/

        RecyclerView recyclerView02 = new RecyclerView(getContext());
        RecyclerView recyclerView03 = new RecyclerView(getContext());
        RecyclerView recyclerView04 = new RecyclerView(getContext());
        popupViews.add(recyclerView01);
        popupViews.add(recyclerView02);
        popupViews.add(recyclerView03);
        popupViews.add(recyclerView04);


        recyclerView01.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //recyclerView01.addItemDecoration(new RecycleViewDividerUtils(getContext(), LinearLayoutManager.HORIZONTAL));//设置分割线

        recyclerView02.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //recyclerView02.addItemDecoration(new RecycleViewDividerUtils(getContext(), LinearLayoutManager.HORIZONTAL));

        recyclerView03.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //recyclerView03.addItemDecoration(new RecycleViewDividerUtils(getContext(), LinearLayoutManager.HORIZONTAL));

        recyclerView04.setLayoutManager(new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false));//GridLayoutManager
        //recyclerView04.addItemDecoration(new RecycleViewDividerUtils(getContext(), LinearLayoutManager.HORIZONTAL));


        mAdapter01 = new GirdDropDownAdapter(getContext(), Arrays.asList(citys));
        recyclerView01.setAdapter(mAdapter01);

        // new ListDropDownAdapter(getContext(), Arrays.asList(ages));
        mAdapter02 = new GirdDropDownAdapter(getContext(), Arrays.asList(ages));
        recyclerView02.setAdapter(mAdapter02);

        mAdapter03 = new GirdDropDownAdapter(getContext(), Arrays.asList(sexs));
        recyclerView03.setAdapter(mAdapter03);

        mAdapter04 = new ConstellationAdapter(getContext(), Arrays.asList(constellations));
        recyclerView04.setAdapter(mAdapter04);

        TextView contentView = new TextView(getContext());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        //LayoutInflater.from(getContext()).inflate();
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    private void initData() {
        mAdapter01.setItemOnClickListener(new GirdDropDownAdapter.OnClickListener() {
            @Override
            public void ItemonClick(int position) {
                mAdapter01.notifyDataSetChanged();
                mDropDownMenu.setTabText(position == 0 ? headers[0] : citys[position]);//设置城市文本标签
                mDropDownMenu.closeMenu();//关闭菜单
            }
        });

        mAdapter02.setItemOnClickListener(new GirdDropDownAdapter.OnClickListener() {
            @Override
            public void ItemonClick(int position) {
                mAdapter02.notifyDataSetChanged();
                mDropDownMenu.setTabText(position == 0 ? headers[1] : ages[position]);//设置年龄文本标签
                mDropDownMenu.closeMenu();//关闭菜单
            }
        });

        mAdapter03.setItemOnClickListener(new GirdDropDownAdapter.OnClickListener() {
            @Override
            public void ItemonClick(int position) {
                mAdapter03.notifyDataSetChanged();
                mDropDownMenu.setTabText(position == 0 ? headers[2] : sexs[position]);//设置性别文本标签
                mDropDownMenu.closeMenu();//关闭菜单
            }
        });

        mAdapter04.setItemOnClickListener(new ConstellationAdapter.OnClickListener() {
            @Override
            public void ItemonClick(int position) {
                mAdapter04.notifyDataSetChanged();
                mDropDownMenu.setTabText(position == 0 ? headers[3] : constellations[position]);//设置星座文本标签
                mDropDownMenu.closeMenu();
            }
        });
    }
    @Override
    public void onDestroyView() {
        //退出前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onDestroyView();
        }
    }
}
