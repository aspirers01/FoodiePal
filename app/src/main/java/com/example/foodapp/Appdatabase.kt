package com.example.foodapp

import androidx.room.Database
import androidx.room.RoomDatabase
  @Database(entities = [Recipe::class], exportSchema = false , version = 1)
  abstract class Appdatabase:RoomDatabase() {
    abstract fun getDao():Dao
}