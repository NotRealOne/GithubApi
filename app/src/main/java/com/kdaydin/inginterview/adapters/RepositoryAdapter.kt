package com.kdaydin.inginterview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kdaydin.inginterview.data.Repository
import com.kdaydin.inginterview.databinding.ItemRepositoryBinding

/**
 * Created by kubilaay on 2019-08-26.
 */
class RepositoryAdapter(val list: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Repository)
    }

    var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    inner class ViewHolder(val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            binding.repoNameTV.text = item.name
            binding.starIV.visibility = if (item.isFavorite) View.VISIBLE else View.GONE
            binding.root.setOnClickListener {
                listener?.onItemClick(item)
            }
        }

    }
}