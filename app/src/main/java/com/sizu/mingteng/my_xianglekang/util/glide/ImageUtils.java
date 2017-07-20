package com.sizu.mingteng.my_xianglekang.util.glide;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.util.CommonUtil;

/**
 * Created by itheima.
 * 图片处理工具类
 */
public class ImageUtils {

    /**
     * 设置 圆形头像
     *
     * @param context
     * @param uri
     * @param imageView
     */
    public static void setHeadImage(Context context, String uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.img_two_bi_one)
                .error(R.drawable.img_two_bi_one)
                .dontAnimate()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * fitCenter()会缩放图片让两边都相等或小于ImageView的所需求的边框。图片会被完整显示，可能不能完全填充整个ImageView。
     * @param context
     * @param uri
     * @param imageView
     */
    public static void setImagesFitCenter(Context context, String uri, int errorImageId, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .override(600, 200) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                .error(errorImageId)
                .into(imageView);
    }

    /**
     * CenterCrop()会缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分。
     * ImageVIew会被完全填充满，但是图片可能不能完全显示出。
     * @param context
     * @param uri
     * @param imageView
     */
    public static void setImagesCenterCrop(Context context, String uri, int errorImageId, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .override(600, 200) // resizes the image to these dimensions (in pixel)
                .error(errorImageId)
                .centerCrop() // this cropping technique scales the image so that it fills the requested bounds and then crops the extra.
                .into(imageView);
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     * Note: ImageView android:layout_width="match_parent"
     */
    public static void loadIntoUseFitWidth(Context context, final String imageUrl, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {
                                setLayoutParams(resource, imageView);
                                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }
                        });
                        return false;
                    }
                })
                .placeholder(errorImageId)
                .error(errorImageId)
                .into(imageView);
    }

    /**
     * 图片等比加载
     * @param context
     * @param imageUrl
     * @param errorImageId
     * @param imageView
     */
    public static void ImageGeometricLoad(Context context, final String imageUrl, int errorImageId, final ImageView imageView) {
        // 图片等比加载
        Glide.with(context)
                .load(imageUrl)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorImageId)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        int width = resource.getWidth();
                        int height = resource.getHeight();
                        //获取imageView的宽
                        int imageViewWidth = imageView.getWidth();
                        //计算缩放比例
                        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
                        //计算图片等比例放大后的高
                        int imageViewHeight = (int) (height * sy);
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        params.height = imageViewHeight;
                        imageView.setLayoutParams(params);
                    }
                });
    }

    private static void setLayoutParams(GlideDrawable resource, ImageView imageView) {
        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        // 获取容器实际存放图片的宽度
        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
        // 计算出容器实际存放图片宽度与图片资源宽度的比例
        float scale = (float) vw / (float) resource.getIntrinsicWidth();
        // 依据比例算出容器实际存放图片高度值
        int vh = Math.round(resource.getIntrinsicHeight() * scale);
        // 计算容器的高度
        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
        imageView.setLayoutParams(params);
    }

    /**
     * databinding 使用的
     * @param view
     * @param bitmap
     */
  /*  @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }

    @BindingAdapter({"app:imageUrl", "app:placeHolder", "app:error"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable, Drawable errorDrawable) {
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .into(imageView);
    }*/

    /**
     * 设置图片 包含默认
     *
     * @param fragment
     * @param uri
     * @param imageView
     * @param errorImg  包含默认
     */
    public static void setImage(Fragment fragment, String uri, ImageView imageView, int errorImg) {
        Glide
                .with(fragment)
                .load(uri)
                .placeholder(errorImg)
                .error(errorImg)
                .centerCrop()
                .into(imageView);
    }

    public static void setImage(Fragment fragment, Integer resourceId, ImageView imageView) {
        Glide
                .with(fragment)
                .load(resourceId)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    public static void setImage(Fragment fragment, Integer resourceId, ImageView imageView, int errorImg) {
        Glide.with(fragment)
                .load(resourceId)
                .dontAnimate()
                .error(errorImg)
                .centerCrop()
                .into(imageView);
    }


    public static void setImage(Context context, String uri, ImageView imageView) {
        Glide
                .with(context)
                .load(uri)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 设置图片 包含默认
     *
     * @param context
     * @param uri
     * @param imageView
     * @param errorImg  包含默认
     */
    public static void setImage(Context context, String uri, int errorImg, ImageView imageView) {
        Glide
                .with(context)
                .load(uri)
                .placeholder(errorImg)
                .error(errorImg)
                .centerCrop()
                .into(imageView);
    }

    public static void setImage(Context context, Integer resourceId, ImageView imageView) {
        Glide
                .with(context)
                .load(resourceId)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }

    /**
     * 书籍列表图片
     */
    @BindingAdapter("android:showBookImg")
    public static void showBookImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
                .override((int) CommonUtil.getDimens(R.dimen.book_detail_width), (int) CommonUtil.getDimens(R.dimen.book_detail_height))
                .placeholder(getDefaultPic(2))
                .error(getDefaultPic(2))
                .into(imageView);
    }

    /**
     * 电影详情页显示电影图片(等待被替换)（测试的还在，已可以弃用）
     * 没有加载中的图
     */
    @BindingAdapter("android:showImg")
    public static void showImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .crossFade(500)
                .error(getDefaultPic(0))
                .into(imageView);
    }

    /**
     * 电影详情页显示高斯背景图  云阅 电影详情页 状态栏颜色就是用的模糊图片
     */
    @BindingAdapter("android:showImgBg")
    public static void showImgBg(ImageView imageView, String url) {
        displayGaussian(imageView.getContext(), url, imageView);
    }

    private static int getDefaultPic(int type) {
        switch (type) {
            case 0:// 电影
                return R.drawable.img_default_movie;
            case 1:// 妹子
                return R.drawable.img_default_meizi;
            case 2:// 书籍
                return R.drawable.img_default_book;
        }
        return R.drawable.img_default_meizi;
    }

    /**
     * 显示高斯模糊效果（电影详情页）
     */
    private static void displayGaussian(Context context, String url, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
       /* Glide.with(context)
                .load(url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);*/
    }
}
