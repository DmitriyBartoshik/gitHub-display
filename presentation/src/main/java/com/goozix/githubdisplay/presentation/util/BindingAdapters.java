package com.goozix.githubdisplay.presentation.util;

import android.view.View;
import android.widget.ImageView;
import android.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BindingAdapters {

    @BindingAdapter(value = "avatarUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean isVisible) {
        view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
