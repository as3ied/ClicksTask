package com.example.clickstask.dataclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Article(
    var source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
):Parcelable
@Parcelize
data class Source(
    val name: String
):Parcelable