package com.sizu.mingteng.my_xianglekang.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/6/5.
 */

public class PersonBean extends BaseObservable implements Serializable {

    /**
     * alt : https://movie.douban.com/celebrity/1050059/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1691.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1691.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1691.jpg"}
     * name : 范冰冰  or
     * name : 冯小刚
     * id : 1050059
     * type: 导演 或 演员
     */
    public boolean getName() {
        return true;
    }
}
