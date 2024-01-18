package com.example.FoodiePal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.FoodiePal.databinding.ActivitySearchBinding

import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdaptor: SearchAdapter
    private lateinit var datalist:ArrayList<Recipe>
    private lateinit var  recipes: List<Recipe?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSearch.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        var db= Room.databaseBuilder(this@SearchActivity,Appdatabase::class.java,"db_name ")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        var daoObject=db.getDao()

        recipes = daoObject.getAll()


        binding.searchbar.requestFocus()
        setuprecyclerview()
        binding.searchbar.addTextChangedListener (object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                 if(s.toString()!=" "){
                     filterdata(s.toString()){
                         
                     }
                 }
            }

           

            override fun afterTextChanged(p0: Editable?) {

            }

        })

       binding.goBack.setOnClickListener{
            finish()
       }


    }

    private fun filterdata(toString: String, function: () -> Unit) {
              var filterdata=ArrayList<Recipe>()
        for( i in recipes.indices){
            if(recipes[i]!!.tittle.lowercase().contains(toString.lowercase()))
            {
                 filterdata.add(recipes[i]!!)
                rvAdaptor.filterlist(filterdata)
            }
        }
    }

    private fun setuprecyclerview( ) {
        datalist=ArrayList()

        for(i in recipes!!.indices ){

                datalist.add(recipes[i]!!)

            rvAdaptor=SearchAdapter(datalist,this)
            binding.rvSearch.adapter=rvAdaptor
        }
    }
}