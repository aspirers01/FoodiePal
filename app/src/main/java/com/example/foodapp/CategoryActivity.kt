package com.example.foodapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.foodapp.databinding.ActivityCategoryBinding
import com.example.foodapp.databinding.ActivityHomeBinding
import com.example.foodapp.databinding.CategoryRvBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var rvAdaptor: CategoryAdaptar
    private lateinit var datalist:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbtn.setOnClickListener{
            finish()
        }
        binding.title.text=intent.getStringExtra("TITTLE")


        setuprecyclerview()
    }






    private fun setuprecyclerview() {
        datalist=java.util.ArrayList()
        binding.recyclerViewC.layoutManager=
           LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        var db= Room.databaseBuilder(this@CategoryActivity,Appdatabase::class.java,"db_name ")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()


        var daoObject=db.getDao()
        var recipes=daoObject.getAll()
        for(i in recipes!!.indices ){
            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!)){
                datalist.add(recipes[i]!!)
            }
            rvAdaptor=CategoryAdaptar(datalist,this)
            binding.recyclerViewC.adapter=rvAdaptor
        }
    }
}