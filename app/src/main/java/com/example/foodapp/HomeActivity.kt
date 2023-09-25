package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.Room
import com.example.foodapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdaptor: PopularAdaptor
    private lateinit var datalist:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setuprecyclerview()

        binding.search.setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }
    }

    private fun setuprecyclerview() {
        datalist=java.util.ArrayList()
         binding.Recycleview.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db=Room.databaseBuilder(this@HomeActivity,Appdatabase::class.java,"db_name ")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()


        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for(i in recipes!!.indices ){
            if(recipes[i]!!.category.contains("Popular")){
                datalist.add(recipes[i]!!)
            }
            rvAdaptor=PopularAdaptor(datalist,this)
            binding.Recycleview.adapter=rvAdaptor
        }
    }
}