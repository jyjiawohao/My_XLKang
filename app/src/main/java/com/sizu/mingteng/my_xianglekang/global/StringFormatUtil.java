package com.sizu.mingteng.my_xianglekang.global;

import com.sizu.mingteng.my_xianglekang.bean.MovieDetailBean;
import com.sizu.mingteng.my_xianglekang.bean.PersonBean;

import java.util.List;

/**
 * Created by lenovo on 2017/6/5.
 */

public class StringFormatUtil {
    /**
     * 格式化导演、主演名字
     */
    public static String formatName(List<PersonBean> casts) {
        if (casts != null && casts.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < casts.size(); i++) {
                if (i < casts.size() - 1) {
                    stringBuilder.append(casts.get(i).getName()).append(" / ");
                } else {
                    stringBuilder.append(casts.get(i).getName());
                }
            }
            return stringBuilder.toString();

        } else {
            return "佚名";
        }
    }

    /**
     * 格式化电影类型
     */
    public static String formatGenres(List<String> genres) {
        if (genres != null && genres.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < genres.size(); i++) {
                if (i < genres.size() - 1) {
                    stringBuilder.append(genres.get(i)).append(" / ");
                } else {
                    stringBuilder.append(genres.get(i));
                }
            }
            return stringBuilder.toString();

        } else {
            return "不知名类型";
        }
    }

    public static String Average(int i, MovieDetailBean bean) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bean == null)
            return stringBuffer.toString();
        switch (i) {
            case 1:
                stringBuffer.append("评分:\t" + bean.getRating().getAverage());
                break;
            case 2:
                stringBuffer.append(bean.getDirectors().get(0).getName());
                break;
            case 3:
                //stringBuffer.append("主演:\t");
                for (MovieDetailBean.CastsBean castsBean : bean.getCasts()) {
                    stringBuffer.append(castsBean.getName() + "/");
                }
                break;
            case 4:
                stringBuffer.append("类型:\t");
                for (String s : bean.getGenres()) {
                    stringBuffer.append(s + "/");
                }
                break;
            case 5:
                stringBuffer.append("上映日期:\t" + bean.getYear());
                break;

            case 6:
                stringBuffer.append("制片国家/地区:\t" + bean.getCountries());
                break;

            case 7:
                stringBuffer.append(bean.getCollect_count() + "评分");
                break;
        }

        return stringBuffer.toString();
    }

    public static String container(MovieDetailBean bean) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bean == null)
            return stringBuffer.toString();

        for (String s : bean.getAka()) {
            stringBuffer.append(s + "/");
        }
        return stringBuffer.toString();
    }
}
