package com.example.foodapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.PoupularRvItemsBinding

class PopularAdaptor(var datalist:ArrayList<Recipe>,var context:Context):RecyclerView.Adapter<PopularAdaptor.ViewHolder>() {
    inner class ViewHolder(var binding:PoupularRvItemsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      var binding=PoupularRvItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

       return    datalist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.popularImg
        Glide.with(context).load(datalist.get(position).img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text=datalist.get(position).tittle

        var time =datalist.get(position).ing.split("\n".toRegex()).dropLastWhile {
             it.isEmpty()
        }.toTypedArray()
        holder.binding.populalTime.text=time.get(0)
    }
}