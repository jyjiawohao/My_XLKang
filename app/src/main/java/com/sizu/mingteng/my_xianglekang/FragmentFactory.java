package com.sizu.mingteng.my_xianglekang;

import android.support.v4.app.Fragment;

import com.sizu.mingteng.my_xianglekang.ui.five.HomeFiveFragment;
import com.sizu.mingteng.my_xianglekang.ui.four.HomeFourFragment;
import com.sizu.mingteng.my_xianglekang.ui.one.HomeOneFragment;
import com.sizu.mingteng.my_xianglekang.ui.three.HomeThreeFragment;
import com.sizu.mingteng.my_xianglekang.ui.two.HomeTwoFragment;

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
