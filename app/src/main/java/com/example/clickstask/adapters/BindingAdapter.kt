package com.example.clickstask.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.clickstask.R

@BindingAdapter("setImgUrl")
fun setImageUrl(img:ImageView,url:String?){
    Glide.with(img.context).load(url).placeholder(R.drawable.no_image_avilable).into(img)
}