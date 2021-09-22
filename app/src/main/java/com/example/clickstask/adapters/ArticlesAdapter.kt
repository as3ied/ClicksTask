package com.example.clickstask.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.clickstask.databinding.ArticleItemBinding
import com.example.clickstask.dataclass.Article
import java.util.*

class ArticlesAdapter(private val listener: OnArticleClickListener) :
    RecyclerView.Adapter<MainViewHolder>(), Filterable {
    var articleFilterList = ArrayList<Article>()

    var articles = ArrayList<Article>()
    fun setArticleList(articles: List<Article>) {
        this.articles = ArrayList(articles)
        articleFilterList = ArrayList(articles)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ArticleItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article = articleFilterList[position]
        holder.bind(article)


        holder.binding.root.setOnClickListener {
            listener.onArticleClicked(article)
        }
    }

    override fun getItemCount(): Int {
        return articleFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.length == 0) {
                    articleFilterList = articles
                } else {
                    val resultList = ArrayList<Article>()
                    for (row in articles) {
                        if (row.title.contains(constraint.toString())
                            || row.author.contains(constraint.toString())
                            || row.publishedAt.contains(constraint.toString())
                            || row.source.name.contains(constraint.toString())
                        )
                            resultList.add(row)

                    }
                    articleFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = articleFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                articleFilterList = results?.values as ArrayList<Article>
                notifyDataSetChanged()
            }

        }
    }
}

class MainViewHolder(val binding: ArticleItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        articleItem: Article
    ) {
        binding.articleItem = articleItem
    }
}
