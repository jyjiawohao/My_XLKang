package com.sizu.mingteng.my_xianglekang.ui.five;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.databinding.FragmentHomeFiveBinding;
import com.sizu.mingteng.my_xianglekang.ui.activity.TypeMainActivity;

/**
 * Created by lenovo on 2017/5/24.
 */

public class HomeFiveFragment extends Fragment {

    private FragmentHomeFiveBinding mFragmentHomeFiveBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentHomeFiveBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_five, container, false);
        return mFragmentHomeFiveBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeFiveBinding.tv01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TypeMainActivity.class));
            }
        });
    }
}
