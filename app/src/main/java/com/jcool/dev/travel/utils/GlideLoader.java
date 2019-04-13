package com.jcool.dev.travel.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseApplication;
import com.lcw.library.imagepicker.utils.ImageLoader;

/**
 * 实现自定义图片加载
 * Create by: chenWei.li
 * Date: 2018/8/30
 * Time: 下午11:10
 * Email: lichenwei.me@foxmail.com
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        //小图加载
        Glide.with(imageView.getContext())
                .load(imagePath)
                .placeholder(R.mipmap.goods_icon).error(R.mipmap.goods_icon).into(imageView);
    }

    @Override
    public void loadPreImage(final ImageView imageView, String imagePath) {
        //大图加载
        Glide.with(imageView.getContext())
                .load(imagePath)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);
                    }
                });
    }

    @Override
    public void clearMemoryCache() {
        //清理缓存
        Glide.get(BaseApplication.getContext()).clearMemory();
    }

}
