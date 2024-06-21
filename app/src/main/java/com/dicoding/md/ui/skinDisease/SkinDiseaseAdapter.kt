package com.dicoding.md.ui.skinDisease

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.md.data.response.DiseasesItem
import com.dicoding.md.databinding.ItemSkinBinding

class SkinDiseaseAdapter(private val onItemClick: (DiseasesItem) ->Unit) : ListAdapter<DiseasesItem , SkinDiseaseAdapter.DiseaseViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiseaseViewHolder {
        val binding = ItemSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiseaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {
        val diseasesItem = getItem(position)
        holder.bind(diseasesItem, onItemClick)
    }

    class DiseaseViewHolder(private val binding: ItemSkinBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(diseasesItem: DiseasesItem, onItemClick: (DiseasesItem) -> Unit) {
            binding.tvItemName.text = diseasesItem.id
            binding.tvItemDescription.text = diseasesItem.description
            Glide.with(binding.ivItemDisease.context)
                .load(diseasesItem.image)
                .into(binding.ivItemDisease)
            itemView.setOnClickListener {
                onItemClick.invoke(diseasesItem)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DiseasesItem>() {
            override fun areItemsTheSame(oldItem: DiseasesItem, newItem: DiseasesItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: DiseasesItem, newItem: DiseasesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}