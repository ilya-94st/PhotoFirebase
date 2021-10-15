package com.example.photofirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photofirebase.databinding.ItemsPictureBinding
import com.example.photofirebase.date.entinities.Pictures

class PictureAdapter(val urls: List<String>) : RecyclerView.Adapter<PictureAdapter.RunViewHolder>() {

    inner class RunViewHolder(var binding: ItemsPictureBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RunViewHolder {
        val binding = ItemsPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RunViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: RunViewHolder, position: Int) {
        val picture = urls[position]
        holder.itemView.apply {
            Glide.with(this).load(picture).into(holder.binding.imageView)
        }
    }
}
