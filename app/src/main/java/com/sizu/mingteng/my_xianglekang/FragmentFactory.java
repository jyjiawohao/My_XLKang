package com.sizu.mingteng.my_xianglekang;

import android.support.v4.app.Fragment;

import com.sizu.mingteng.my_xianglekang.ui.fragment.HomeFiveFragment;
import com.sizu.mingteng.my_xianglekang.ui.fragment.HomeFourFragment;
import com.sizu.mingteng.my_xianglekang.ui.fragment.HomeOneFragment;
import com.sizu.mingteng.my_xianglekang.ui.fragment.HomeThreeFragment;
import com.sizu.mingteng.my_xianglekang.ui.fragment.HomeTwoFragment;

/**
 * Created by lenovo on 2017/5/24.
 */

public class FragmentFactory {
    private static HomeOneFragment sHomeOneFragment;
    private static HomeTwoFragment sHomeTwoFragment;
    private static HomeThreeFragment sHomeThreeFragment;
    private static HomeFourFragment sHomeFourFragment;
    private static HomeFiveFragment sHomeFiveFragment;

    public static Fragment getFragment(int position) {
        Fragment baseFragment = null;
        switch (position) {
            case 0:
                if (sHomeOneFragment == null) {
                    sHomeOneFragment = new HomeOneFragment();
                }
                baseFragment = sHomeOneFragment;
                break;
            case 1:
                if (sHomeTwoFragment == null) {
                    sHomeTwoFragment = new HomeTwoFragment();
                }
                baseFragment = sHomeTwoFragment;
                break;
            case 2:
                if (sHomeThreeFragment == null) {
                    sHomeThreeFragment = new HomeThreeFragment();
                }
                baseFragment = sHomeThreeFragment;
                break;
            case 3:
                if (sHomeFourFragment == null) {
                    sHomeFourFragment = new HomeFourFragment();
                }
                baseFragment = sHomeFourFragment;
                break;
            case 4:
                if (sHomeFiveFragment == null) {
                    sHomeFiveFragment = new HomeFiveFragment();
                }
                baseFragment = sHomeFiveFragment;
                break;
        }
        return baseFragment;

    }

}
