package com.goozix.githubdisplay.presentation.util;

import android.widget.ImageView;
import android.databinding.BindingAdapter;

import com.goozix.githubdisplay.presentation.util.picasso.CircleTransformation;
import com.squareup.picasso.Picasso;

public class BindingAdapters {

    @BindingAdapter(value = "avatarUrl", requireAll = false)
    public static void setImageUrl(ImageView imageView, String url) {
        Picasso.get().load(url).transform(new CircleTransformation()).into(imageView);
    }
}
