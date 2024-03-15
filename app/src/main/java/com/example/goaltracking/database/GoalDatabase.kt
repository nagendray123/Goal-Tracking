package com.example.goaltracking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goaltracking.model.Goal

@Database(entities = [Goal::class], version = 1)
abstract class GoalDatabase : RoomDatabase() {

    abstract fun getGoalDao() : GoalDao
    companion object {
        @Volatile
        private  var INSTANCE : GoalDatabase? = null

        fun getDatabase(context: Context) : GoalDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalDatabase::class.java,
                    "goal_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}