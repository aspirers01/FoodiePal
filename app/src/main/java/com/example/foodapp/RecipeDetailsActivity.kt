package com.example.foodapp

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.core.view.marginTop
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ActivityRecipeDetailsBinding

class RecipeDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRecipeDetailsBinding
    var img_crop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //set image
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)
        binding.fullScreen.setOnClickListener{
            if( img_crop){
                binding.itemimg.scaleType=ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)

                binding.shadow.visibility= View.GONE

                img_crop=!img_crop
            }else{
                binding.itemimg.scaleType=ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemimg)
                binding.shadow.visibility= View.VISIBLE
                img_crop=!img_crop
            }
        }
          var tie = intent.getStringExtra("ing")?.split("\n".toRegex())!!.dropLastWhile {
            it.isEmpty()
        }.toTypedArray()
        binding.time.text=tie.get(0)




        binding.tittle.text=intent.getStringExtra("tittle")
        binding.ingText.text=intent.getStringExtra("ing")
        binding.stepsText.text=intent.getStringExtra("des")

         binding.steps.background=null
        binding.steps.setTextColor(getColor(R.color.black))

        binding.steps.setOnClickListener{
            binding.scrolling.visibility=View.GONE
            binding.scrolstep.visibility=View.VISIBLE
            binding.ingbtn.background=null
            binding.ingbtn.setTextColor(getColor(R.color.black))
            binding.steps.setTextColor(getColor(R.color.background))
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
        }

        binding.ingbtn.setOnClickListener{
            binding.scrolling.visibility=View.VISIBLE
            binding.scrolstep.visibility=View.GONE
            binding.steps.background=null
            binding.steps.setTextColor(getColor(R.color.black))
            binding.ingbtn.setTextColor(getColor(R.color.background))
            binding.ingbtn.setBackgroundResource(R.drawable.btn_ing)
        }


        binding.backIMG.setOnClickListener{
            finish()
        }

    }
}