package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ActivityRecipeDetailsBinding
import com.example.foodapp.databinding.PoupularRvItemsBinding
import com.example.foodapp.databinding.SearchRvBinding

class SearchAdapter(var datalist:ArrayList<Recipe>,var context:Context):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=SearchRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return    datalist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun filterlist(fliterlist:ArrayList<Recipe>){
     datalist=fliterlist

        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.img
        holder.binding.searchText.text=datalist.get(position).tittle
        Glide.with(context).load(datalist.get(position).img).into(holder.binding.img)

        holder.itemView.setOnClickListener{
            val intent=Intent(context,RecipeDetailsActivity::class.java)
            intent.putExtra("img",datalist.get(position).img)
            intent.putExtra("tittle",datalist.get(position).tittle)
            intent.putExtra("des",datalist.get(position).des)
            intent.putExtra("ing",datalist.get(position).ing)

            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }
}