package com.example.home.presentation.News.recView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.ItemArticleBinding
import com.example.home.presentation.News.data.ArticlesDataUi

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val news = mutableListOf<ArticlesDataUi>()


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        p1: Int
    ): RecyclerView.ViewHolder {
        val item = ItemArticleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return NewsViewHolder(item)
    }

    override fun onBindViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (viewHolder as? NewsViewHolder)?.bind(news[position])
    }

    fun updateAdapter(updatedList: List<ArticlesDataUi>){
        news.clear()
        news.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesDataUi) {
            with(binding) {
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishAt.text = article.publishedAt
                tvAuthorName.text = article.author
                tvSourceName.text = article.source.name
            }
        }
    }

    override fun getItemCount(): Int = news.size
}