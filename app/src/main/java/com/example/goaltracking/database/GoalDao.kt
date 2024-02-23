package com.example.goaltracking.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.goaltracking.model.Goal

@Dao
interface GoalDao {

    @Insert
    suspend fun insert(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)

    @Query("SELECT * from goals_table order by id ASC")
    fun getAllGoals() : LiveData<List<Goal>>


    @Query("UPDATE goals_table Set title = :title, deadline = :deadline, categories = :categories, description = :description WHERE id = :id")
    suspend fun update(id: Int, title: String, deadline: String, categories: String, description: String)
}