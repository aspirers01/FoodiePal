package com.example.FoodiePal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.FoodiePal.databinding.CategoryRvBinding

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
        var time =datalist.get(position).ing.split("\n".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        holder.binding.timetx.text=time.get(0)

        holder.binding.next.setOnClickListener{
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