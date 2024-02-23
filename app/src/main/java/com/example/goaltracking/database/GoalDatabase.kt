package com.example.goaltracking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goaltracking.model.Goal
import com.example.goaltracking.ui.DATABASE_NAME

@Database(entities = arrayOf(Goal::class), version = 1, exportSchema = false)
abstract class GoalDatabase : RoomDatabase() {

    abstract fun getGoalDao() : GoalDao

    companion object {

        @Volatile
        private  var INSTANCE : GoalDatabase? = null

        fun getDatabase(context: Context) : GoalDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}