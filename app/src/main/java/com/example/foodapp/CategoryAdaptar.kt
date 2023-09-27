package com.example.foodapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.CategoryRvBinding

class CategoryAdaptar (var datalist:ArrayList<Recipe>,var context:Context):RecyclerView.Adapter<CategoryAdaptar.ViewHolder>() {
    inner class ViewHolder(var binding: CategoryRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = CategoryRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(datalist.get(position).img).into(holder.binding.img)
        holder.binding.titlerv.text=datalist.get(position).tittle
    }
}