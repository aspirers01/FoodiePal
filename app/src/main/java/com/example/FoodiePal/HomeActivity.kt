package com.example.FoodiePal

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.FoodiePal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdaptor: PopularAdaptor
    private lateinit var datalist:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.more.setOnClickListener{
            var dialog=Dialog(this )
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.bottomsheet)

            dialog.show()
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT

            )
//            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.BOTTOM )
        }

        binding.maindish.setOnClickListener{
            var myintent= Intent(this@HomeActivity,CategoryActivity::class.java)
            myintent.putExtra("TITTLE","Main Dish")
            myintent.putExtra("CATEGORY","Dish")
            startActivity(myintent)

        }
        binding.dessert.setOnClickListener{
            var myintent= Intent(this@HomeActivity,CategoryActivity::class.java)
            myintent.putExtra("TITTLE","Dessert")
            myintent.putExtra("CATEGORY","Desserts")
            startActivity(myintent)
        }
        binding.drinks.setOnClickListener{
            var myintent= Intent(this@HomeActivity,CategoryActivity::class.java)
            myintent.putExtra("TITTLE","Drinks")
            myintent.putExtra("CATEGORY","Drinks" )
            startActivity(myintent)

        }
        binding.salad.setOnClickListener{
            var myintent= Intent(this@HomeActivity,CategoryActivity::class.java)
            myintent.putExtra("TITTLE","Salad")
            myintent.putExtra("CATEGORY","Salad")
            startActivity(myintent)

        }
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