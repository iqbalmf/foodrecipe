package net.iqbalfauzan.foodrecipe.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Iqbalmf on 2019-08-11
 */
@BindingAdapter("app:imageUrl")
fun setImageUrl(view: AppCompatImageView, url: String) {
    Glide.with(view.context).load(url).into(view)
}