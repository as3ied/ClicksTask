package com.example.nagwatask.main.connections



import com.example.clickstask.dataclass.Article
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class ArticleResponse(val articles : List<Article>)
interface Api  {
    @GET("v2/top-headlines?country=eg&apiKey=a89ac22cdfd940148557204764fe6765")
    fun getAllArticles(): Call<ArticleResponse>


    companion object {

        fun createClient() : OkHttpClient{
            return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY)).build()
        }
        var retrofitService: Api? = null

        fun getInstance() : Api {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .client(createClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(Api::class.java)
            }
            return retrofitService!!
        }
    }
}
