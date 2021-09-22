package com.example.clickstask.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clickstask.databinding.ActivityNewsDetailsBinding
import com.example.clickstask.dataclass.Article
import com.google.gson.Gson

class NewsDetailsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent!=null)
        {
            val article:Article? = Gson().fromJson(intent!!.getStringExtra("article"),Article::class.java)
            binding.articleItemDetails=article

        }


    }
}