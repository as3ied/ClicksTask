package com.example.clickstask.ui.home


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clickstask.adapters.ArticlesAdapter
import com.example.clickstask.adapters.OnArticleClickListener
import com.example.clickstask.databinding.ActivityMainBinding
import com.example.clickstask.dataclass.Article
import com.example.clickstask.ui.details.NewsDetailsActivity
import com.example.nagwatask.main.connections.Api
import com.google.gson.Gson


class MainActivity : AppCompatActivity(), OnArticleClickListener {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels(factoryProducer = {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(retrofitService) as T
            }

        }
        factory
    })
    private val retrofitService = Api.getInstance()
    val adapter = ArticlesAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter
        binding.articleSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                adapter.notifyDataSetChanged()
                return false
            }

        })
        viewModel.articles.observe(this, Observer {
            if (it != null)
                adapter.setArticleList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllArticles()
    }

    override fun onArticleClicked(article: Article) {
        val intent = Intent(this, NewsDetailsActivity::class.java)
        intent.putExtra("article", Gson().toJson(article))
        startActivity(intent)
    }


}