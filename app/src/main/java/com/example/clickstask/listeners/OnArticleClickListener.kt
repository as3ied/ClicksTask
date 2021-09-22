package com.example.clickstask.adapters

import com.example.clickstask.dataclass.Article

interface OnArticleClickListener {
    fun onArticleClicked(article:Article)
}