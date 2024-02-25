package com.example.goaltracking.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "goals_table")
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val deadline: String?,
    val categories: String?,
    val description: String?,
    val date : String?
): Serializable
