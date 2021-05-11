package com.assignment.countrylist.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl","error")
internal fun loadImage(view:ImageView,url:String,error:Drawable)
{
    Picasso.get().load(url).fit().error(error).into(view)
}