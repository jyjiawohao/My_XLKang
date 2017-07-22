package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sizu.mingteng.my_xianglekang.App;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.bean.GanIoTypeBean;
import com.sizu.mingteng.my_xianglekang.global.UriUtil;
import com.sizu.mingteng.my_xianglekang.util.MyLogger;
import com.sizu.mingteng.my_xianglekang.util.StringUtils;
import com.sizu.mingteng.my_xianglekang.util.glide.ImageUtils;

/**
 * Created by lenovo on 2017/7/20.
 */

public class GanKIoAdapter extends BaseQuickAdapter<GanIoTypeBean.ResultsBean, BaseViewHolder> {
    public GanKIoAdapter() {
        super(R.layout.item_gank_io, null);
    }

    @Override
    protected void convert(BaseViewHolder holder, final GanIoTypeBean.ResultsBean item) {

        ImageView mItem_image = (ImageView) holder.getView(R.id.item_image);
        //TODO 随机的获取一个范围为200-600直接的高度  可以先用集合存储写死  现在的动态变化的
        int i = (int) (300 + Math.random() * 400);
        ViewGroup.LayoutParams layoutParams = mItem_image.getLayoutParams();
        layoutParams.height = i;
        mItem_image.setLayoutParams(layoutParams);


        int position = holder.getLayoutPosition();

        if (item.getType().equals("福利")) {
            ImageUtils.setImage(App.context, item.getUrl(), R.drawable.img_two_bi_one, mItem_image);
        } else {
            if (position > UriUtil.imagUrl.length - 1) {
                MyLogger.e("GanKIoAdapter", "==" + position + "==imagUrl==" + 18);
                ImageUtils.setImage(mContext, UriUtil.imagUrl[16], R.drawable.img_two_bi_one, mItem_image);
            } else {
                MyLogger.e("GanKIoAdapter", "==" + position + "==imagUrl==" + UriUtil.imagUrl[position]);
                ImageUtils.setImage(mContext, UriUtil.imagUrl[position], R.drawable.img_two_bi_one, mItem_image);
            }
        }

        holder.setText(R.id.item_time, item.getPublishedAt())
                .setText(R.id.item_title, item.getType())
                .setText(R.id.item_cade, StringUtils.decode(item.getDesc()));
    }
}
