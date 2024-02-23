package com.example.goaltracking.database

import androidx.lifecycle.LiveData
import com.example.goaltracking.model.Goal

class GoalRepository(private val goalDao: GoalDao) {

    val allGoals : LiveData<List<Goal>> = goalDao.getAllGoals()

    suspend fun insert(goal: Goal){
        goalDao.insert(goal)
    }
    suspend fun delete(goal: Goal){
        goalDao.delete(goal)
    }
    suspend fun update(goal: Goal){
        goalDao.update(goal.id, goal.title, goal.deadline, goal.categories, goal.description)
    }
}