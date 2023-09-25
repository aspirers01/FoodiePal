package com.example.foodapp

import androidx.room.Dao
import androidx.room.Query
@Dao
interface Dao {
    @Query("select * from recipe")
     fun getAll():List<Recipe?>

}