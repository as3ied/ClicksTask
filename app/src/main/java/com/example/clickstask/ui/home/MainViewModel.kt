package com.example.clickstask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clickstask.dataclass.Article
import com.example.nagwatask.main.connections.Api
import com.example.nagwatask.main.connections.ArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

 class MainViewModel(private val repository: Api) : ViewModel() {
    var articles = MutableLiveData<List<Article>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllArticles() {

        val response = repository.getAllArticles()
        response.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                val articleResponse  = response.body()
                if (articleResponse != null)
                articles.postValue(articleResponse.articles)
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}
