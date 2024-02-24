package com.example.goaltracking.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.goaltracking.database.GoalDatabase
import com.example.goaltracking.database.GoalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoalViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GoalRepository

    val allgoals : LiveData<List<Goal>>

    init {
        val dao = GoalDatabase.getDatabase(application).getGoalDao()
        repository = GoalRepository(dao)
        allgoals = repository.allGoals
    }
    fun insertGoal(goal: Goal) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(goal)
    }

    fun updateGoal(goal: Goal) = viewModelScope.launch(Dispatchers.IO){
        repository.update(goal)
    }
    fun deleteGoal(goal: Goal) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(goal)
    }


}