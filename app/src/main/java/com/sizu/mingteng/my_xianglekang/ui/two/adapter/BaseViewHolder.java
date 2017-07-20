package com.sizu.mingteng.my_xianglekang.ui.two.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lenovo on 2017/7/18.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View mView;
    /**
     * Views indexed with their IDs
     */
    private final SparseArray<View> mSparseArray;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        this.mSparseArray = new SparseArray<View>();
    }

    public View getViewHolder() {
        return mView;
    }

    public View getItemView(int id) {
        return mView.findViewById(id);
    }

    public BaseViewHolder setItemView(int id, CharSequence s) {
        TextView tv = (TextView) mView.findViewById(id);
        tv.setText(s);
        return this;
    }

    public BaseViewHolder setText(int viewId, String strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * 设置背景资源
     *
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public BaseViewHolder setBackgroundRes(int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * 设置图片资源
     *
     * @param viewId
     * @param imageResId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * 设置背景图片
     *
     * @param viewId
     * @param color
     * @return
     */
    public BaseViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置文本颜色
     *
     * @param viewId
     * @param textColor
     * @return
     */
    public BaseViewHolder setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * 设置图片
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置一个图片取消或者隐藏
     *
     * @param viewId
     * @param visible true 显示  false 隐藏
     * @return
     */
    public BaseViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 设置图片bitmap
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mSparseArray.get(viewId);
        if (view == null) {
            view = mView.findViewById(viewId);
            mSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
